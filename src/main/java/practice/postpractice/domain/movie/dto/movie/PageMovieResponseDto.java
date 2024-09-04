package practice.postpractice.domain.movie.dto.movie;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <br>package name   : practice.postpractice.domain.movie.dto
 * <br>file name      : PageMovieResponseDto
 * <br>date           : 2024-08-28
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
 * 2024-08-28        SeungHoon              init create
 * </pre>
 */
public record PageMovieResponseDto(
        List<MovieResponseDto> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
    public static PageMovieResponseDto from(Page<MovieResponseDto> page) {
        return new PageMovieResponseDto(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
