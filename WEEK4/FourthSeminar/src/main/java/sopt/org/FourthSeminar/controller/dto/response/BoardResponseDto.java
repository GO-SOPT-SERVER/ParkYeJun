package sopt.org.FourthSeminar.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long postId;
    private String title;
    private UserResponseDto writer;
    private String content;
    private String category;
    private String createdAt;
    private String updatedAt;

}
