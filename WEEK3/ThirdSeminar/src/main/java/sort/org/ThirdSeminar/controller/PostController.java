package sort.org.ThirdSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sort.org.ThirdSeminar.common.advice.BaseException;
import sort.org.ThirdSeminar.common.dto.ApiResponseDto;
import sort.org.ThirdSeminar.controller.dto.request.PostCreateRequestDto;
import sort.org.ThirdSeminar.controller.dto.response.PostResponseDto;
import sort.org.ThirdSeminar.exception.SuccessStatus;
import sort.org.ThirdSeminar.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ApiResponseDto<PostResponseDto> createPost(@RequestBody @Valid final PostCreateRequestDto request) {
        try {
            return ApiResponseDto.success(SuccessStatus.POST_CREATE_SUCCESS, postService.create(request));
        } catch (BaseException e) {
            return ApiResponseDto.error(e.getStatus());
        }
    }

    @GetMapping("/post/user/{userId}")
    public ApiResponseDto<List<PostResponseDto>> getPostListByUser(@PathVariable("userId") final Long userId) {
        try {
            return ApiResponseDto.success(SuccessStatus.GET_POST_SUCCESS, postService.getPostListByUser(userId));
        } catch (BaseException e) {
            return ApiResponseDto.error(e.getStatus());
        }
    }

    @GetMapping("/post/{postId}")
    public ApiResponseDto<PostResponseDto> getPostById(@PathVariable("postId") final Long postId) {
        try {
            return ApiResponseDto.success(SuccessStatus.GET_POST_SUCCESS, postService.getPostById(postId));
        } catch (BaseException e) {
            return ApiResponseDto.error(e.getStatus());
        }
    }
}
