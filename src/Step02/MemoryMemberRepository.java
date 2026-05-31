package Step02;

import role.Member;

import java.util.ArrayList;
import java.util.List;

public class MemoryMemberRepository implements MemberRepository {
    private List<Member> memberList = new ArrayList<>();

    @Override
    public void save(Member member) {
        memberList.add(member);
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