package practice.postpractice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.postpractice.domain.member.dao.MemberRepository;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.dao.LikeRepository;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Like;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.LikeManagementException;
import practice.postpractice.global.exception.exception.MemberManageException;
import practice.postpractice.global.exception.exception.MovieManageException;

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
    public Long createLike(Long movieId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberManageException(ErrorCode.NOT_EXIST_MEMBER));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        if(likeRepository.existsByMemberAndMovie(member, movie)) {
            throw new LikeManagementException(ErrorCode.DUPLICATE_LIKE);
        }
        Like like = Like.create(member,movie);
        return likeRepository.save(like).getId();
    }
}
