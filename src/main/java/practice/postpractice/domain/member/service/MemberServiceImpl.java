package practice.postpractice.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import practice.postpractice.domain.member.dao.MemberRepository;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.member.dto.CreateMemberDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.MemberManageException;

/**
 * <br>package name   : practice.postpractice.domain.member.service
 * <br>file name      : MemberService
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
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long register(CreateMemberDto dto) {
        Member member = Member.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .nickname(dto.nickname())
                .build();
        try {
            Member newMember = memberRepository.save(member);
            return newMember.getId();
        } catch(DataIntegrityViolationException e) {
            throw new MemberManageException(ErrorCode.DUPLICATE_USER);
        }
    }
}
