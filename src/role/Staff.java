package role;

import policy.StaffSubmissionPolicy;
import policy.SubmissionPolicy;

public class Staff extends Member {
    private String staffPos;

    public Staff(String name, String major, int gen, String part, String staffPos) {
        super(name, major, gen, part);
        this.staffPos = staffPos;
    }

    @Override
    public SubmissionPolicy getSubmissionPolicy() {
        return new StaffSubmissionPolicy();
    }

    @Override
    public String getInfo() {
        return "[운영진] 이름: " + getName()
                + " | 전공: " + getMajor()
                + " | 기수: " + getGen()
                + " | 파트: " + getPart()
                + " | 직책: " + staffPos;
    }
}