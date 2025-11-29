package practice.postpractice.domain.movie.service;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.postpractice.domain.movie.domain.Genre;

public interface GenreService extends JpaRepository<Genre, Long> {
}
