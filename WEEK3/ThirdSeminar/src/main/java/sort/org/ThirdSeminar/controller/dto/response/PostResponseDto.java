package sort.org.ThirdSeminar.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sort.org.ThirdSeminar.domain.Post;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {

    private Long postId;
    private String title;
    private UserResponseDto writer;
    private String content;
    private String category;
    private String createdAt;
    private String updatedAt;

    public static PostResponseDto of(Post post) {
        return new PostResponseDto(post.getId(), post.getTitle(), UserResponseDto.of(post.getWriter()), post.getContent(), post.getCategory().getName(), post.getCreatedAt(), post.getModifiedAt());
    }
}
