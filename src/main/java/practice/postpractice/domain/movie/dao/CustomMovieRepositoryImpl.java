package practice.postpractice.domain.movie.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public List<Movie> searchMovies(MovieQueryOption queryOption) {
        return queryFactory
                .selectFrom(movie)
                .leftJoin(movieGenre).on(movie.id.eq(movieGenre.id))
                .leftJoin(genre).on(movie.id.eq(genre.id))
                .where(matchesQueryOption(queryOption))
                .fetch();
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
