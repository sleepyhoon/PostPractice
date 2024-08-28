package practice.postpractice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import practice.postpractice.domain.member.dao.MemberRepository;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.dao.LikeRepository;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Like;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.MovieResponseDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.LikeManagementException;
import practice.postpractice.global.exception.exception.MemberManageException;
import practice.postpractice.global.exception.exception.MovieManageException;

import java.util.List;

/**
 * <br>package name   : practice.postpractice.domain.movie.service
 * <br>file name      : LikeServiceImpl
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
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Override
    public Long createLike(Long movieId, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new MemberManageException(ErrorCode.NOT_EXIST_MEMBER));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        if(likeRepository.existsByMemberAndMovie(member, movie)) {
            throw new LikeManagementException(ErrorCode.DUPLICATE_LIKE);
        }
        Like like = Like.create(member,movie);
        return likeRepository.save(like).getId();
    }

    @Override
    public Page<MovieResponseDto> getMembersLikeMovies(String username, Pageable pageable) {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new MemberManageException(ErrorCode.NOT_EXIST_MEMBER));
        Long memberId = member.getId();

        if(!likeRepository.existsByMemberId(memberId)) {
            throw new LikeManagementException(ErrorCode.NOT_EXIST_LIKE);
        }
        Page<Like> likes = likeRepository.findByMemberId(memberId,pageable);
        Page<Movie> movies = likes.map(Like::getMovie);
        return movies.map(MovieResponseDto::from);
    }

    @Override
    public void deleteLike(Long movieId, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(()->
                new MemberManageException(ErrorCode.NOT_EXIST_MEMBER));
        Like like = likeRepository.findByMovieIdAndMemberId(movieId, member.getId()).orElseThrow(()->
                new LikeManagementException(ErrorCode.NOT_EXIST_LIKE));
        likeRepository.delete(like);
    }

    @Override
    public int countLikes(Long movieId) {
        return likeRepository.countByMovieId(movieId);
    }
}
