package practice.postpractice.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.postpractice.domain.member.dto.CreateMemberDto;
import practice.postpractice.domain.member.dto.SignInRequestDto;
import practice.postpractice.domain.member.service.MemberService;
import practice.postpractice.domain.member.service.MemberServiceImpl;
import practice.postpractice.domain.member.service.SignInService;
import practice.postpractice.global.auth.jwt.domain.JwtToken;
import practice.postpractice.global.auth.jwt.domain.RefreshToken;
import practice.postpractice.global.auth.jwt.service.RefreshTokenService;

/**
 * <br>package name   : practice.postpractice.domain.member.controller
 * <br>file name      : MemberController
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
@RequestMapping("/member")
public class MemberController {
    private final SignInService signInService;
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody CreateMemberDto dto) {
        Long id = memberService.register(dto);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody SignInRequestDto dto) {
        JwtToken jwtToken = signInService.SignIn(dto);
        RefreshToken refreshToken = new RefreshToken(dto.username(), jwtToken.getRefreshToken());
        refreshTokenService.saveRefreshToken(refreshToken);
        return ResponseEntity.ok(jwtToken);
    }
}
