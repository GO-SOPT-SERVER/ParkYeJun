package sopt.org.SecondSeminar.service.board;

import org.springframework.stereotype.Service;
import sopt.org.SecondSeminar.controller.board.dto.request.RegisterBoardRequestDto;
import sopt.org.SecondSeminar.controller.board.dto.request.UpdateBoardRequestDto;
import sopt.org.SecondSeminar.controller.board.dto.response.BoardResponseDto;
import sopt.org.SecondSeminar.domain.board.Board;

import java.util.ArrayList;
import java.util.List;

import static sopt.org.SecondSeminar.SecondSeminarApplication.boardList;

@Service
public class BoardService {

    /**
     * 게시글 등록
     */
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

    /**
     * ID로 게시글 조회
     */
    public BoardResponseDto getBoardById(Long boardId) {
        Board findBoard = boardList.get(Math.toIntExact(boardId)-1);
        return new BoardResponseDto(findBoard);
    }

    /**
     * title로 게시글 조회
     */
    public BoardResponseDto getBoardByTitle(String title) {
        for (Board board : boardList) {
            if (board.getTitle().equals(title)) {
                return new BoardResponseDto(board);
            }
        }
        return null;
    }

    /**
     * 게시글 수정
     */
    public BoardResponseDto updateBoard(Long boardId, UpdateBoardRequestDto boardRequestDto) {
        Board findBoard = boardList.get(Math.toIntExact(boardId)-1);
        findBoard.update(boardRequestDto);
        return new BoardResponseDto(findBoard);
    }
    public BoardResponseDto updateBoard(UpdateBoardRequestDto boardRequestDto) {
        Board findBoard = boardList.get(Math.toIntExact(boardRequestDto.getId())-1);
        findBoard.update(boardRequestDto);
        return new BoardResponseDto(findBoard);
    }

    /**
     * 게시글 삭제
     */
    public void deleteBoard(Long boardId) {
        Board findBoard = boardList.get(Math.toIntExact(boardId)-1);
        boardList.remove(findBoard);
    }

    /**
     * 전체 게시글 조회
     */
    public List<BoardResponseDto> getBoardList() {
        List<BoardResponseDto> result = new ArrayList<>();
        for (Board board : boardList) {
            result.add(new BoardResponseDto(board));
        }
        return result;
    }
}
