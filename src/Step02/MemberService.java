package Step02;

import role.Member;

import java.util.List;

public class MemberService {
    // Step 2: 인터페이스에 의존 + final로 변경 불가
    private final MemberRepository memberRepository;

    // 생성자를 통해 외부에서 주입받음 (DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(Member member) {
        if (memberRepository.existsByName(member.getName())) {
            System.out.println(" 등록 실패: 이미 존재하는 이름입니다.");
            return;
        }
        memberRepository.save(member);
        System.out.println(" 등록 완료: " + member.getName());
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}