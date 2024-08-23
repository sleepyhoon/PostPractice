package practice.postpractice.domain.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.postpractice.domain.movie.dto.ResponseMovieDto;
import practice.postpractice.domain.movie.service.MovieService;

import java.util.List;

/**
 * <br>package name   : practice.postpractice.domain.controller
 * <br>file name      : MovieController
 * <br>date           : 2024-08-21
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
 * 2024-08-21        SeungHoon              init create
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<ResponseMovieDto>> findAllMovies() {
        List<ResponseMovieDto> response = movieService.getAllMovies();
        return ResponseEntity.ok(response);
    }
}

