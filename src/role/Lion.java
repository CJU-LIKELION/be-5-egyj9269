package role;

import policy.LionSubmissionPolicy;
import policy.SubmissionPolicy;

public class Lion extends Member {
    private String studentId;

    public Lion(String name, String major, int gen, String part, String studentId) {
        super(name, major, gen, part);
        this.studentId = studentId;
    }

    @Override
    public SubmissionPolicy getSubmissionPolicy() {
        return new LionSubmissionPolicy();
    }

    @Override
    public String getInfo() {
        return "[아기사자] 이름: " + getName()
                + " | 전공: " + getMajor()
                + " | 기수: " + getGen()
                + " | 파트: " + getPart()
                + " | 학번: " + studentId;
    }
}