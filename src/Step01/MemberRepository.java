package Step01;

import role.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private List<Member> memberList = new ArrayList<>();
    private Map<String, List<Member>> partMap = new HashMap<>();

    public void save(Member member) {
        memberList.add(member);

        String part = member.getPart();
        if (!partMap.containsKey(part)) {
            List<Member> newPartList = new ArrayList<>();
            newPartList.add(member);
            partMap.put(part, newPartList);
        } else {
            partMap.get(part).add(member);
        }
    }

    public Member findByName(String name) {
        for (Member member : memberList) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> findAll() {
        return memberList;
    }

    public boolean existsByName(String name) {
        return findByName(name) != null;
    }
}