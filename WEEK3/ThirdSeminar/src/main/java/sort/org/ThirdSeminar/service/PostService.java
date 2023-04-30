package sort.org.ThirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sort.org.ThirdSeminar.controller.dto.request.PostCreateRequestDto;
import sort.org.ThirdSeminar.controller.dto.response.PostResponseDto;
import sort.org.ThirdSeminar.domain.Category;
import sort.org.ThirdSeminar.domain.Post;
import sort.org.ThirdSeminar.domain.User;
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
    public PostResponseDto create(PostCreateRequestDto request) {
        User writer = userRepository.findById(request.getWriterId());
        Post post = Post.builder()
                .title(request.getTitle())
                .writer(writer)
                .content(request.getContent())
                .category(Category.nameOf(request.getCategory()))
                .build();
        writer.addPost(post);
        return PostResponseDto.of(post);
    }

    /**
     * 특정 User의 게시글 목록 조회하기
     */
    public List<PostResponseDto> getPostListByUser(Long userId) {
        User writer = userRepository.findById(userId);
        List<PostResponseDto> result = new ArrayList<>();
        for(Post post : writer.getPostList()) {
            result.add(PostResponseDto.of(post));
        };
        return result;
    }

    /**
     * ID로 Post 조회하기
     */
    public PostResponseDto getPostById(Long postId) {
        Post findPost = postRepository.findById(postId);
        return PostResponseDto.of(findPost);
    }

}
