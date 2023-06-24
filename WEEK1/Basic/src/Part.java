public interface Part {

    /**
     * "인터페이스는 추상 메서드와 상수로만 이루어진다."
     *
     * 인터페이스의 변수는 public static final 로만 지정 가능(생략 OK) -> 상수만 선언 가능
     */
    public static final String PREFIX = "GO SOPT";

    /**
     * 인터페이스의 메소드는 public abstract final 로만 선언 가능(생략 OK)
     * *생략이 가능해 아래 3가지 모두 가능한 표현이다.
     */
    public abstract void participate();
    public void study();
    void communicate();

}
