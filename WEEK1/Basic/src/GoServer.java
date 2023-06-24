import java.util.ArrayList;
import java.util.List;

/**
 * Main 함수가 있는 클래스 -> 프로그램의 시작
 */
public class GoServer {
    public static void main(String[] args) {

        // MemberList를 먼저 만들어볼게요 (test 멤버는 우리 조로~)
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("박예준", 22, "IT공학전공", "YB", 0.25));
        memberList.add(new Member("최윤한", 20, "sopt명예오비", "OB", 2.9));
        memberList.add(new Member("장유진", 22, "컴공", "YB", 1));
        memberList.add(new Member("김성은", 22, "컴공", "YB", 1));

        // Server Class의 객체는 아래 두 가지 형태로 생성 가능
        Server server1 = new Server();
        Server server = new Server(memberList);
        Plan plan = new Plan();
        Plan plan1 = new Plan("this() test를 위한 Plan 객체");

        // 같은 Server 클래스 타입이라도 메모리 주소가 다르겠쥬?
        System.out.println("📍new 키워드를 사용하여 생성한 객체는 서로 다른 힙 메모리 공간에 할당되어 각각의 메모리 주소를 가진다.");
        System.out.println("server1 변수의 메모리주소: " + System.identityHashCode(server1));
        System.out.println("server 변수의 메모리주소: " + System.identityHashCode(server));
        System.out.println();

        System.out.println("📍name 파라미터를 넘겨서 생성한 Plan 클래스의 객체는 this()로 default constructor를 호출하고 있다.");
        plan.printPlanInfo();
        plan1.printPlanInfo();

        // Part 인터페이스를 구현한 Server 클래스와 Part 클래스는 각각에서 구현한 메서드를 호출한다.
        System.out.println("📍같은 Part Interface의 메서드이지만, 서로 다른 메서드를 호출하여 다른 결과가 출력된다.");
        System.out.println("서버에서는 ... 👇");
        server.participate();
        server.communicate();
        server.study();
        System.out.println("기획에서는 ... 👇");
        plan.participate();
        plan.communicate();
        plan.study();
        System.out.println();

        // Member, List 클래스로 구성된 Server 파트의 정보와 drink() 호출 전후로 변화되는 정보에 주목하자.
        System.out.println("📍Member에 소속된 Leader가 상속을 받으며, super/this 등의 키워드를 통해 원하는 메서드가 호출되는 것을 명시적으로 확인할 수 있다.");
        server.printServerInfo();
        server.drink();
        server.printServerInfo();
    }
}
