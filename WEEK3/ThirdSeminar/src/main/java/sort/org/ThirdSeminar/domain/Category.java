package sort.org.ThirdSeminar.domain;

public enum Category {

    DAILY("일상글"),
    DEVELOPMENT("자기계발"),
    SPORT("운동"),
    ECONOMY("경제경영"),
    COMPUTER("컴퓨터"),
    LITERATURE("문학"),
    ETC("기타");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category nameOf(String name) {
        for (Category category : Category.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return ETC;
    }
}
