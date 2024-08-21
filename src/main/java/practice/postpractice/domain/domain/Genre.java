package practice.postpractice.domain.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * <br>package name   : practice.postpractice.domain
 * <br>file name      : Genre
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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<MovieGenre> movieGenres = new HashSet<>();
}

