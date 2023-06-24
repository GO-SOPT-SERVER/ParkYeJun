package sopt.org.FourthSeminar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sopt.org.FourthSeminar.common.dto.ApiResponse;
import sopt.org.FourthSeminar.config.jwt.JwtService;
import sopt.org.FourthSeminar.config.resolver.UserId;
import sopt.org.FourthSeminar.controller.dto.request.BoardImageListRequestDto;
import sopt.org.FourthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.FourthSeminar.exception.Error;
import sopt.org.FourthSeminar.exception.Success;
import sopt.org.FourthSeminar.exception.model.UnauthorizedException;
import sopt.org.FourthSeminar.external.client.aws.S3Service;
import sopt.org.FourthSeminar.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Board", description = "게시판 API Document")  // swagger
public class BoardController {

    private final BoardService boardService;
    private final JwtService jwtService;
    private final S3Service s3Service;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "게시글 생성 API", description = "게시글을 서버에 등록합니다.")  // swagger
    public ApiResponse create(
            @UserId Long userId,
//            @RequestHeader("Authorization") String accessToken,  // @RequestHeader: HTTP Header의 key값으로 value를 가져올 수 있다.
            @ModelAttribute @Valid final BoardImageListRequestDto request) {

        /*
        @ModelAttribute를 사용하는 이유: @RequestPart로 파일을 다른 형식으로 받는다면 클라이언트 측에서도 Json과 multipart를 일일이 지정해줘야 하는 번거로움이 있다.
        @ModelAttribute는 일단 기본 생성자로 깡통 객체를 만들어 놓고 이후에 데이터 바인딩을 하는데, 이는 setter를 이용할 것만 같아 나빠보인다.
        하지만 알아서 가장 적절한 타입으로 지정해주는 특성에 의해 꼭 setter를 사용하는 것은 아니다.
        이렇게 안 하려면 모든 값을 항상 받도록 하여(즉, @AllArgsConstructor 가 있어야 함) 자동으로 데이터가 바인딩되게 하는 방법이 있다.
        자동적으로 이루어져서 그닥 좋지는 않지만 편리하다!
        -> @ModelAttribute, @RequestPart 에 관한 공식문서 꼭 보기!!
        */

        /*if (accessToken == null || accessToken.equals("")) {
            throw new UnauthorizedException(Error.NO_EXISTS_AUTHORIZATION_TOKEN, Error.NO_EXISTS_AUTHORIZATION_TOKEN.getMessage());
        }

        boardService.create(Long.parseLong(jwtService.getJwtContents(accessToken)), request); */

//        String boardThumbnailImageUrl = s3Service.uploadImage(request.getThumbnail(), "board");
        List<String> boardThumbnailImageUrlList = s3Service.uploadImages(request.getBoardImages(), "board");
        boardService.create(userId, boardThumbnailImageUrlList, request);
        return ApiResponse.success(Success.CREATE_POST_SUCCESS);
    }
}
