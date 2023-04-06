import java.util.ArrayList;
import java.util.List;

public class Plan implements Part {  // 서버 파트 외 기획으로 선정한 이유는.. 이번 주 뒷풀이 같이 한대서 ㅋㅋ..
    private String name;
    private List<Member> memberList;
    private Leader partLeader;

    // 여기는 Default Constructor 만!
    public Plan() {
        name = Part.PREFIX + " " + "Plan-part";
        memberList = new ArrayList<Member>();
        partLeader = new Leader("기팟장", 20, "컴공", "OB", 1);
    }

    @Override
    public void participate() {
        System.out.println("모두 " + name + " 세미나에서 만나요!");
    }

    @Override
    public void study() {
        System.out.println("기팟 스터디요");
    }

    @Override
    public void communicate() {
        System.out.println("집착기팟");
    }
}
