package Step01;

import role.Lion;
import role.Member;
import role.Staff;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static MemberService memberService = new MemberService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("선택: ");

            if (choice == 1) {
                registerMember();
            } else if (choice == 2) {
                printAllMembers();
            } else if (choice == 3) {
                searchMemberByName();
            } else if (choice == 4) {
                System.out.println("프로그램을 종료합니다.");
                running = false;
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("========  멤버 관리 시스템 (Step 1) ========");
        System.out.println("1.   멤버 등록");
        System.out.println("2.   전체 멤버 조회");
        System.out.println("3.   이름으로 검색");
        System.out.println("4.   종료");
    }

    private static void registerMember() {
        System.out.println("—  멤버 등록 —");
        int roleChoice = readInt("역할 선택 (1: 아기사자, 2: 운영진): ");

        if (roleChoice != 1 && roleChoice != 2) {
            System.out.println("올바른 역할을 선택해주세요.");
            return;
        }

        System.out.print(" 이름: ");
        String name = scanner.nextLine().trim();

        System.out.print(" 전공: ");
        String major = scanner.nextLine().trim();

        System.out.print(" 기수: ");
        int gen = Integer.parseInt(scanner.nextLine().trim());

        System.out.print(" 파트 (백엔드/프론트엔드/기획/디자인): ");
        String part = scanner.nextLine().trim();

        Member newMember;
        if (roleChoice == 1) {
            System.out.print(" 학번: ");
            String studentId = scanner.nextLine().trim();
            newMember = new Lion(name, major, gen, part, studentId);
        } else {
            System.out.print(" 직책 (대표/부대표/파트장/멘토): ");
            String staffPos = scanner.nextLine().trim();
            newMember = new Staff(name, major, gen, part, staffPos);
        }

        memberService.save(newMember);
    }

    private static void printAllMembers() {
        System.out.println("——  전체 멤버 목록 ——");
        List<Member> memberList = memberService.findAll();

        if (memberList.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        for (int i = 0; i < memberList.size(); i++) {
            Member member = memberList.get(i);
            System.out.println((i + 1) + ". [" + (member instanceof Lion ? "아기사자" : "운영진") + "] "
                    + member.getName() + " - " + member.getGen() + "기");
        }

        System.out.println(" 총 " + memberList.size() + "명");
    }

    private static void searchMemberByName() {
        System.out.println("—— 🔍 이름으로 검색 ——");
        System.out.print("검색할 이름: ");
        String name = scanner.nextLine().trim();

        Member member = memberService.findByName(name);
        if (member != null) {
            System.out.println();
            System.out.println(" [검색 결과]");
            System.out.println(member.getInfo());
            System.out.println(" 과제 제출 가능 여부: " + (member.canSubmit() ? " 가능" : " 불가능"));
        } else {
            System.out.println("해당 이름의 멤버를 찾을 수 없습니다.");
        }
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}