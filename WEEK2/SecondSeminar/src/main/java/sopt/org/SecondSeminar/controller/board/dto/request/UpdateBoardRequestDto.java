package sopt.org.SecondSeminar.controller.board.dto.request;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {

    private Long id;
    private String title;
    private String content;

}
