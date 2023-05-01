package sort.org.ThirdSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sort.org.ThirdSeminar.common.dto.ApiResponseDto;
import sort.org.ThirdSeminar.controller.dto.request.UserRequestDto;
import sort.org.ThirdSeminar.controller.dto.response.UserResponseDto;
import sort.org.ThirdSeminar.exception.SuccessStatus;
import sort.org.ThirdSeminar.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ResponseStatus(HttpStatus.CREATED)   // 응답 코드 설정하기 -> 이를 동적으로 변경하고 싶다면 ResponseEntity를 사용!
    public ApiResponseDto<UserResponseDto> create(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponseDto.success(SuccessStatus.SIGNUP_SUCCESS, userService.create(request));
    }
}
