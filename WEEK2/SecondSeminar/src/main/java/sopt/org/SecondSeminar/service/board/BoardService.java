package sopt.org.SecondSeminar.service.board;

import org.springframework.stereotype.Service;
import sopt.org.SecondSeminar.controller.board.dto.request.RegisterBoardRequestDto;
import sopt.org.SecondSeminar.controller.board.dto.response.BoardResponseDto;
import sopt.org.SecondSeminar.domain.board.Board;

import static sopt.org.SecondSeminar.SecondSeminarApplication.boardList;

@Service
public class BoardService {

    public Long register(RegisterBoardRequestDto request) {
        Board newBoard = new Board(
                request.getTitle(),
                request.getContent(),
                request.getWriter()
        );

        boardList.add(newBoard);
        newBoard.setId((long)boardList.size());

        return newBoard.getId();
    }

    public BoardResponseDto getBoardById(Long boardId) {
        Board findBoard = boardList.get(Math.toIntExact(boardId)-1);
        return new BoardResponseDto(findBoard);
    }

    public BoardResponseDto getBoardByTitle(String title) {
        for (Board board : boardList) {
            if (board.getTitle().equals(title)) {
                return new BoardResponseDto(board);
            }
        }
        return null;
    }
}
