package practice.postpractice.domain.movie.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import practice.postpractice.domain.movie.domain.*;
import practice.postpractice.domain.movie.dto.MovieQueryOption;

import java.util.List;

import static practice.postpractice.domain.movie.domain.QGenre.*;
import static practice.postpractice.domain.movie.domain.QMovie.*;
import static practice.postpractice.domain.movie.domain.QMovieGenre.*;

/**
 * <br>package name   : practice.postpractice.domain.movie.dao
 * <br>file name      : CustomMovieRepositoryImpl
 * <br>date           : 2024-08-23
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * =======================================================
 * DATE           AUTHOR               NOTE
 * -------------------------------------------------------
 * 2024-08-23        SeungHoon              init create
 * </pre>
 */
@RequiredArgsConstructor
public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Movie> searchMovies(MovieQueryOption queryOption, Pageable pageable) {
        // 필터링과 페이징을 적용하여 쿼리 실행
        List<Movie> movies = queryFactory
                .selectFrom(movie)
                .leftJoin(movieGenre).on(movie.id.eq(movieGenre.movie.id))
                .leftJoin(genre).on(movieGenre.genre.id.eq(genre.id))
                .where(matchesQueryOption(queryOption))
                .offset(pageable.getOffset())  // 페이징: 시작 지점
                .limit(pageable.getPageSize()) // 페이징: 가져올 개수
                .fetch();

        // 전체 결과 개수를 얻기 위한 쿼리
        JPAQuery<Long> countQuery = queryFactory
                .select(movie.count())
                .from(movie)
                .leftJoin(movieGenre).on(movie.id.eq(movieGenre.movie.id))
                .leftJoin(genre).on(movieGenre.genre.id.eq(genre.id))
                .where(matchesQueryOption(queryOption));

        // Page 객체로 변환하여 반환
        return PageableExecutionUtils.getPage(movies, pageable, countQuery::fetchOne);

    }

    private BooleanExpression genreNameEq(String genreName) {
        return genreName != null ? genre.name.eq(genreName) : null;
    }

    private BooleanExpression containsTitle(String title) {
        return title != null ? movie.title.containsIgnoreCase(title) : null;
    }

    private BooleanBuilder matchesQueryOption(MovieQueryOption queryOption) {
        return new BooleanBuilder()
                .and(containsTitle(queryOption.title()))
                .and(genreNameEq(queryOption.genreName()));
    }
}
