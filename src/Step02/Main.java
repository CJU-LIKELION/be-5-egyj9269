package Step02;

import role.Lion;
import role.Member;
import role.Staff;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static MemberService memberService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 시작 시 저장소 선택 → MemberService에 주입 (DI)
        selectRepository();

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

    // 저장소 선택 후 MemberService에 주입
    private static void selectRepository() {
        System.out.println(" 저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository   (더미 데이터)");
        int choice = readInt("선택: ");

        MemberRepository memberRepository;
        if (choice == 1) {
            memberRepository = new MemoryMemberRepository();
        } else if (choice == 2) {
            memberRepository = new MockMemberRepository();
        } else {
            System.out.println("잘못된 선택입니다. 기본값(Memory)으로 실행합니다.");
            memberRepository = new MemoryMemberRepository();
        }

        // MemberService 코드 수정 없이 구현체만 교체해서 주입
        memberService = new MemberService(memberRepository);
    }

    private static void printMenu() {
        System.out.println(" ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) ===== ");
        System.out.println("1.   멤버 등록");
        System.out.println("2.   전체 멤버 조회");
        System.out.println("3.   이름으로 검색");
        System.out.println("4.   종료");
    }

    private static void registerMember() {
        int roleChoice = readInt(" 역할 선택 (1: 아기사자, 2: 운영진): ");

        if (roleChoice != 1 && roleChoice != 2) {
            System.out.println("올바른 역할을 선택해주세요.");
            return;
        }

        System.out.println();
        System.out.println(" 정보 입력");
        System.out.print("이름: ");
        String name = scanner.nextLine().trim();

        System.out.print("전공: ");
        String major = scanner.nextLine().trim();

        System.out.print("기수: ");
        int gen = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("파트: ");
        String part = scanner.nextLine().trim();

        Member newMember;
        if (roleChoice == 1) {
            System.out.print("학번: ");
            String studentId = scanner.nextLine().trim();
            newMember = new Lion(name, major, gen, part, studentId);
        } else {
            System.out.print("직책 (대표/부대표/파트장/멘토): ");
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
        System.out.print(" 검색할 이름: ");
        String name = scanner.nextLine().trim();

        Member member = memberService.findByName(name);
        if (member != null) {
            System.out.println();
            System.out.println(" ===== 검색 결과 =====");
            System.out.println(" 역할: " + (member instanceof Lion ? "아기사자" : "운영진"));
            System.out.println(member.getInfo());
            System.out.println(" 과제 제출 가능: " + (member.canSubmit() ? " 가능" : " 불가능"));
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