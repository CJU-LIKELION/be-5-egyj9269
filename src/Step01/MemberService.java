package Step01;

import role.Member;

import java.util.List;

public class MemberService {
    // Step 1: Repository 직접 생성 (강한 결합)
    private MemberRepository memberRepository = new MemberRepository();

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