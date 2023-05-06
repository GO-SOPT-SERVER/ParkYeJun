package sopt.org.FourthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.FourthSeminar.common.dto.ApiResponse;
import sopt.org.FourthSeminar.controller.dto.request.UserRequestDto;
import sopt.org.FourthSeminar.controller.dto.response.UserResponseDto;
import sopt.org.FourthSeminar.exception.Success;
import sopt.org.FourthSeminar.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")   // api의 공통적인 부분을 매핑해주기 위한 어노테이션
public class UserController {

    private final UserService userService;

    // @RequestBody에 의해 기본 생성자가 있어도 데이터 바인딩이 가능!
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponse.success(Success.SIGNUP_SUCCESS, userService.create(request));
    }

}
