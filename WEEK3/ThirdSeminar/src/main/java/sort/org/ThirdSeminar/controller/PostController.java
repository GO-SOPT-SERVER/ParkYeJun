package sort.org.ThirdSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sort.org.ThirdSeminar.common.dto.ApiResponseDto;
import sort.org.ThirdSeminar.controller.dto.request.PostCreateRequestDto;
import sort.org.ThirdSeminar.controller.dto.response.PostResponseDto;
import sort.org.ThirdSeminar.exception.SuccessStatus;
import sort.org.ThirdSeminar.service.PostService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ApiResponseDto<PostResponseDto> createPost(@RequestBody @Valid final PostCreateRequestDto request) {
        return ApiResponseDto.success(SuccessStatus.POST_CREATE_SUCCESS, postService.create(request));
    }
}
