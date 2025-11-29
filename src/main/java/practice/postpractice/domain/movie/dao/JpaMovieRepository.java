package practice.postpractice.domain.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.postpractice.domain.movie.domain.Movie;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {
}
