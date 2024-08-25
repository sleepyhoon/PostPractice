package practice.postpractice.domain.movie.service;

import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.ResponseMovieDto;

import java.util.List;

/**
 * <br>package name   : practice.postpractice.domain.movie.service
 * <br>file name      : LikeService
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
public interface LikeService {
    Long createLike(Long movieId, String username);
    List<ResponseMovieDto> getMembersLikeMovies(String username);
    void deleteLike(Long likeId);
}
