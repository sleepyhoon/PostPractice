package practice.postpractice.domain.member.service;

import practice.postpractice.domain.member.dto.CreateMemberDto;
import practice.postpractice.domain.member.dto.SignInRequestDto;
import practice.postpractice.global.auth.jwt.domain.JwtToken;

/**
 * <br>package name   : practice.postpractice.domain.member.service
 * <br>file name      : MemberService
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
public interface MemberService {
    Long register(CreateMemberDto dto);
}
