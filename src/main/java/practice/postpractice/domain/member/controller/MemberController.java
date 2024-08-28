package practice.postpractice.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.postpractice.domain.member.dto.CreateMemberDto;
import practice.postpractice.domain.member.dto.RegisterResponseDto;
import practice.postpractice.domain.member.dto.SignInRequestDto;
import practice.postpractice.domain.member.service.MemberService;
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
@Tag(name = "Member Management",description = "Member API")
public class MemberController {
    private final SignInService signInService;
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @Operation(summary = "멤버 회원가입", description = "회원 가입할 때 사용하는 API")
    @ApiResponse(responseCode = "1000",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<RegisterResponseDto> register(@RequestBody CreateMemberDto dto) {
        Long id = memberService.register(dto);
        RegisterResponseDto response = RegisterResponseDto.of(id,"회원 가입에 성공했습니다");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "멤버 로그인", description = "로그인할 때 사용하는 API")
    @ApiResponse(responseCode = "1000",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<JwtToken> signIn(@RequestBody SignInRequestDto dto) {
        JwtToken jwtToken = signInService.SignIn(dto);
        RefreshToken refreshToken = new RefreshToken(dto.username(), jwtToken.getRefreshToken());
        refreshTokenService.saveRefreshToken(refreshToken);
        return ResponseEntity.ok(jwtToken);
    }
}
