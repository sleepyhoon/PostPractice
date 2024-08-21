package practice.postpractice.domain.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * <br>package name   : practice.postpractice.domain
 * <br>file name      : Movie
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
@Entity
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moive_id")
    private long id;

    private String title;
    private long tId;

    @OneToMany(mappedBy = "movie")
    private Set<MovieGenre> movieGenres = new HashSet<>();
}
