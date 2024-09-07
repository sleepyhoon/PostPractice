package practice.postpractice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.postpractice.domain.member.dao.MemberRepository;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.dao.CommentRepository;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Comment;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.comment.CreateCommentDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.MemberManageException;
import practice.postpractice.global.exception.exception.MovieManageException;

/**
 * <br>package name   : practice.postpractice.domain.movie.service
 * <br>file name      : CommentServiceImpl
 * <br>date           : 2024-09-04
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
 * 2024-09-04        SeungHoon              init create
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final MovieRepository movieRepository;

    @Override
    public Long createComment(CreateCommentDto dto) {
        Member member = memberRepository.findByUsername(dto.username())
                .orElseThrow(() -> new MemberManageException(ErrorCode.NOT_EXIST_MEMBER));
        Movie movie = movieRepository.findById(dto.movie_id())
                        .orElseThrow(() -> new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        Comment comment = Comment.create(movie,member,dto.content());
        commentRepository.save(comment);
        return comment.getId();
    }
}
