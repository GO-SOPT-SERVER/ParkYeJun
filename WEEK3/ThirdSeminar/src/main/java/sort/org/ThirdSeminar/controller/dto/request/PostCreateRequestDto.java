package sort.org.ThirdSeminar.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateRequestDto {

    @NotBlank
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String category;

    @NotNull
    private Long writerId;
}
