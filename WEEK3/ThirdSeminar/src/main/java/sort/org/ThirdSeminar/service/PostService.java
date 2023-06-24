package sort.org.ThirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sort.org.ThirdSeminar.common.advice.BaseException;
import sort.org.ThirdSeminar.controller.dto.request.PostCreateRequestDto;
import sort.org.ThirdSeminar.controller.dto.response.PostResponseDto;
import sort.org.ThirdSeminar.domain.Category;
import sort.org.ThirdSeminar.domain.Post;
import sort.org.ThirdSeminar.domain.User;
import sort.org.ThirdSeminar.exception.ErrorStatus;
import sort.org.ThirdSeminar.infrastructure.PostRepository;
import sort.org.ThirdSeminar.infrastructure.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public PostResponseDto create(PostCreateRequestDto request) throws BaseException {
        User writer = userRepository.findById(request.getWriterId()).orElseThrow(
                () -> new BaseException(ErrorStatus.NO_EXISTS_USER)
        );
        Post post = Post.builder()
                .title(request.getTitle())
                .writer(writer)
                .content(request.getContent())
                .category(Category.nameOf(request.getCategory()))
                .build();

        post.setWriter(writer);
        return PostResponseDto.of(post);
    }

    /**
     * 특정 User의 게시글 목록 조회하기
     */
    public List<PostResponseDto> getPostListByUser(Long userId) throws BaseException {
        User writer = userRepository.findById(userId).orElseThrow(
                () -> new BaseException(ErrorStatus.NO_EXISTS_USER)
        );
        List<PostResponseDto> result = new ArrayList<>();
        for(Post post : writer.getPostList()) {
            result.add(PostResponseDto.of(post));
        };
        return result;
    }

    /**
     * ID로 Post 조회하기
     */
    public PostResponseDto getPostById(Long postId) throws BaseException {
        Post findPost = postRepository.findById(postId).orElseThrow(
                () -> new BaseException(ErrorStatus.NO_EXISTS_POST)
        );
        return PostResponseDto.of(findPost);
    }

}
