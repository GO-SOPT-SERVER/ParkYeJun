package sopt.org.SecondSeminar.controller.board.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterBoardRequestDto {

    private String title;
    private String content;
    private String writer;

}
