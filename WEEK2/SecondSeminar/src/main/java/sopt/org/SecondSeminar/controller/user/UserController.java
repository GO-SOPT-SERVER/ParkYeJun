package sopt.org.SecondSeminar.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondSeminar.controller.user.dto.request.RegisterRequestDto;
import sopt.org.SecondSeminar.service.user.UserService;

import static sopt.org.SecondSeminar.SecondSeminarApplication.userList;

@RestController
@RequiredArgsConstructor  // final 키워드가 붙은 멤버에 한해서 이를 파라미터로 가지는 생성자 생성
public class UserController {

    private final UserService userService;

    /**
     * Request Body 로 클라이언트의 요청을 받는 경우 -> @RequestBody 어노테이션 사용
     */
    @PostMapping("/user")
    public String register(@RequestBody final RegisterRequestDto request) {
        System.out.println("성별: " + request.getGender());
        System.out.println("이름: " + request.getName());
        System.out.println("전화번호: " + request.getContact());
        System.out.println("나이: " + request.getAge());

        // 서비스 계층에 유저를 등록하는 메서드를 호출
        Long userId = userService.register(request);
        System.out.println(userList.get(userId.intValue() - 1).toString());

        return userId + "번 유저 등록이 완료되었습니다.";
    }

    /**
     * Path Variable 로 클라이언트의 요청을 받는 경우 -> @PathVariable 어노테이션 사용
     * Postman에서는 /user/:userId와 같이 적어주면 Path Variable 방식으로 인식함
     */
    @GetMapping("/user/{userId}")
    public String getOne(@PathVariable final Long userId) {
        System.out.println("요청 유저 아이디: " + userId);

        // 서비스 계층에서 유저 아이디로 유저를 찾는 메서드 호출

        return "유저 조회 성공";
    }

    /**
     * Query Parameter 로 클라이언트의 요청을 받는 경우 -> @RequestParam 어노테이션 사용
     * URL 뒤에  ?name=""&age="" 등의 형식으로 붙어서 넘어감
     * 데이터 조회 시 조건이 필요한 경우에 주로 사용 ex. 검색에 대한 조회 결과
     */
    @GetMapping("/user/search")
    public String search(@RequestParam final String name) {
        System.out.println("유저 이름 검색 인자: " + name);

        // 서비스 계층에서 유저 닉네임으로 유저를 찾는 메서드 호출

        return "유저 검색 성공";
    }
}
