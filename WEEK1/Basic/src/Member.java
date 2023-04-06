public class Member {
    private String name;
    private int age;
    private String dept;
    private ActivityType activityType;
    private double alcohol;

    // Constructor 정의 (매개변수 O) => 디폴트 생성자 사용 불가
    public Member(String name, int age, String dept, String activityType, double alcohol) {
        this.name = name;   // 그냥 name=name 해도 같지만 this로 파라미터와 명확히 구분해주자
        this.age = age;
        this.dept = dept;
        this.activityType = ActivityType.valueOf(activityType);
        this.alcohol = alcohol;
    }

    // 멤버의 주량을 반환하는 메서드
    public double getAlcohol() {
        return alcohol;
    }

    // 멤버의 주량을 변경하기 위한 메서드 -> Leader 클래스에서 접근하기 위함
    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getName() {
        return name;
    }

    public void printMemberInfo() {
        System.out.println();
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        System.out.println("학과: " + dept);
        System.out.println("활동기수: 32기 " + activityType.toString());
        System.out.println("주량: " + alcohol);
    }

}

enum ActivityType {
    YB, OB;
}