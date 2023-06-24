package sopt.org.SecondSeminar.controller.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)  // 클래스 내 모든 멤버변수를 매개변수로 가지는 생성자 생성
public class RegisterRequestDto {

    private String gender;
    private String name;
    private int age;
    private String contact;

}
