package role;

import policy.SubmissionPolicy;

public abstract class Member {
    private String name;
    private String major;
    private int gen;
    private String part;

    public Member(String name, String major, int gen, String part) {
        this.name = name;
        this.major = major;
        this.gen = gen;
        this.part = part;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGen() { return gen; }
    public String getPart() { return part; }

    public abstract SubmissionPolicy getSubmissionPolicy();
    public abstract String getInfo();

    public boolean canSubmit() {
        return getSubmissionPolicy().canSubmit();
    }
}