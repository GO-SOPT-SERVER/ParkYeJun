import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server implements Part {

    private String name;
    private List<Member> memberList;
    protected Leader partLeader;  // main에서 Leader의 메서드 호출하고 싶어서 protected로 지정
    private String techStack;

    // Default Constructor
    public Server() {
        name = Part.PREFIX + " " + "Server-part";
        memberList = new ArrayList<Member>();
        partLeader = new Leader("승헌님", 20, "컴공", "OB", 1);
        memberList.add(partLeader);
        techStack = "Spring";
    }

    public Server(List<Member> memberList) {
        name = Part.PREFIX + " " + "Server-part";
        this.memberList = memberList;
        partLeader = new Leader("승헌님", 20, "컴공", "OB",1);
        memberList.add(partLeader);
        techStack = "Spring";
    }

    @Override
    public void participate() {
        System.out.println("모두 " + name + " 세미나에서 만나요!");
    }

    @Override
    public void study() {
        System.out.println("스터디 합시다~");
    }

    @Override
    public void communicate() {
        System.out.println("서버하면 소통, 소통하면 서버");
    }

    // memberList 에 있는 멤버 수 반환하는 메서드
    public int memberCnt() {
        return memberList.size();
    }

    // 서버파트의 최강자는 ?!
    public void drink() {
        System.out.println("[ 서버파트 🍻최강자전 ]");
        HashMap<String, Double> result = new HashMap<>();
        for (Member member : memberList) {
            if (member.getAlcohol() > 3.0) {         // getAlcohol()을 호출하면 Member 클래스의 메서드가 아닌, Leader 클래스에서 오버라이딩한 메서드가 호출된다. by 다형성
                result.put(member.getName(), member.getAlcohol());
            }
        }

        if (result.containsKey("승헌님")) {
            System.out.println("역시 팟짱님 체고 ~~~");
        }

        // 뒷풀이에서 살아남는 리스트 출력
        if (result.size() > 0) {
            System.out.println("\n========== 최강자 목록 ===========");
            result.forEach((key, value) -> {
                System.out.printf("%s : %.1f잔%n", key, value);
            });
            System.out.println("-> 살아남은 인원 : " + result.size());

        }
    }

    public void printServerInfo() {
        System.out.println("\n우리는? " + name);
        System.out.println("우리의 기술스택: " + techStack + " 🌸");
        System.out.print("[ 파트장 정보 ]");
        partLeader.printMemberInfo();

        System.out.println("\n[ 서버파트 부원들 ]");
        for (Member member : memberList) {
            member.printMemberInfo();
        }
        System.out.println();
        System.out.println("=========================");
    }
}
