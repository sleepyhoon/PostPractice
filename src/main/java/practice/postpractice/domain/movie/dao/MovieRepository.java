package practice.postpractice.domain.movie.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import practice.postpractice.domain.movie.domain.Movie;

import java.util.Optional;

/**
 * <br>package name   : practice.postpractice.domain.movie.dao
 * <br>file name      : MovieRepository
 * <br>date           : 2024-08-22
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
 * 2024-08-22        SeungHoon              init create
 * </pre>
 */
public interface MovieRepository extends JpaRepository<Movie, Integer>,CustomMovieRepository {
    Optional<Movie> findById(Long id);
    Page<Movie> findAll(Pageable pageable);
}
