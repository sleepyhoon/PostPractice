package practice.postpractice.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.postpractice.domain.member.domain.Member;

import java.util.Optional;

/**
 * <br>package name   : practice.postpractice.domain.member.dao
 * <br>file name      : MemberRepository
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
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
}
