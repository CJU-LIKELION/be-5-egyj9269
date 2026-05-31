package Step02;

import role.Lion;
import role.Member;
import role.Staff;

import java.util.Arrays;
import java.util.List;

public class MockMemberRepository implements MemberRepository {

    private List<Member> memberList = Arrays.asList(
        new Lion("더미사자1", "컴퓨터공학과", 14, "백엔드", "202020201"),
        new Lion("더미사자2", "전자공학과", 14, "프론트엔드", "202020202"),
        new Staff("더미운영진", "소프트웨어학과", 12, "기획", "회장")
    );

    @Override
    public void save(Member member) {
        // 실제 저장 안 함
        System.out.println("[Mock] save() 호출 - 실제 저장 안 함: " + member.getName());
    }

    @Override
    public Member findByName(String name) {
        for (Member member : memberList) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public boolean existsByName(String name) {
        return findByName(name) != null;
    }
}