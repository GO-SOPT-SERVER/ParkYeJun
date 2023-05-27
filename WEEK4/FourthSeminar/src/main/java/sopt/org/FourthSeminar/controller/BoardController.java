package sopt.org.FourthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.FourthSeminar.common.dto.ApiResponse;
import sopt.org.FourthSeminar.config.jwt.JwtService;
import sopt.org.FourthSeminar.config.resolver.UserId;
import sopt.org.FourthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.FourthSeminar.exception.Error;
import sopt.org.FourthSeminar.exception.Success;
import sopt.org.FourthSeminar.exception.model.UnauthorizedException;
import sopt.org.FourthSeminar.service.BoardService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final JwtService jwtService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
            @UserId Long userId,
//            @RequestHeader("Authorization") String accessToken,  // @RequestHeader: HTTP Header의 key값으로 value를 가져올 수 있다.
            @RequestBody @Valid final BoardRequestDto request) {

        /*if (accessToken == null || accessToken.equals("")) {
            throw new UnauthorizedException(Error.NO_EXISTS_AUTHORIZATION_TOKEN, Error.NO_EXISTS_AUTHORIZATION_TOKEN.getMessage());
        }

        boardService.create(Long.parseLong(jwtService.getJwtContents(accessToken)), request); */

        boardService.create(userId, request);
        return ApiResponse.success(Success.CREATE_POST_SUCCESS);
    }
}
