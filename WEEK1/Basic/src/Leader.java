public class Leader extends Member {

    private String role;

    public Leader(String name, int age, String dept, String activityType, double alcohol) {
        super(name, age, dept, activityType, alcohol);  // 부모의 생성자와 동일하게
        role = "서팟장";
    }

    // 얜 아마 사용을 안 할 듯 ..?
    public Leader(String name, int age, String dept, String activityType, String role, double alcohol) {
        super(name, age, dept, activityType, alcohol);  // 부모의 생성자와 동일하게
        this.role = role;
    }

    // 3병 이하면 무조건 바뀌는 매직
    @Override
    public double getAlcohol() {
        if (super.getAlcohol() <= 3) {  // 파트장님은 3병 이하는 취급하지 않아요..
            System.out.println("팟짱님의 주량은 " + super.getAlcohol() + "...은 Fake");
            System.out.println("난 " + role + ". 죽지 않아요 ~~~💪");

            double realAlcohol = 4.5;
            setAlcohol(realAlcohol);

            return realAlcohol;
        }
        return super.getAlcohol();
    }

    // 임의로 바꾸고 싶을 때도 있으니까..
    @Override
    public void setAlcohol(double alcohol) {
        super.setAlcohol(alcohol);  // super을 붙여도 안 붙여도 결과는 동일하다.
    }

    @Override
    public void printMemberInfo() {
        super.printMemberInfo();
        System.out.println("역할: " + role);
    }
}
