package sopt.org.SecondSeminar.domain.user;

import lombok.Getter;

@Getter
public class User {

    private Long id;   // User 데이터의 식별자로 이용됨
    private String name;
    private String gender;
    private int age;
    private String contact;

    public User(String name, String gender, int age, String contact) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n" +
                "gender: " + this.gender + "\n" +
                "name: " + this.name + "\n" +
                "contact: " + this.contact + "\n" +
                "age: " + this.age;
    }
}
