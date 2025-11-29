package practice.postpractice.domain.movie.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import practice.postpractice.domain.movie.domain.Movie;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m join fetch m.movieGenreList mgl join fetch mgl.genre")
    List<Movie> fetchAllMovies();
}
