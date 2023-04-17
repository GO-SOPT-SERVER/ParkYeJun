package sopt.org.SecondSeminar.controller.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondSeminar.controller.board.dto.request.RegisterBoardRequestDto;
import sopt.org.SecondSeminar.controller.board.dto.request.UpdateBoardRequestDto;
import sopt.org.SecondSeminar.controller.board.dto.response.BoardResponseDto;
import sopt.org.SecondSeminar.domain.board.Board;
import sopt.org.SecondSeminar.service.board.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public String register(@RequestBody final RegisterBoardRequestDto request) {
        System.out.println("제목: " + request.getTitle());
        System.out.println("내용: " + request.getContent());
        System.out.println("작성자: " + request.getWriter());

        Long boardId = boardService.register(request);

        return boardId + "번 게시글이 등록되었습니다.";
    }

    @GetMapping("/board/{boardId}")
    public BoardResponseDto getBoardById(@PathVariable final Long boardId) {

        return boardService.getBoardById(boardId);
    }

    @GetMapping("/board/search")
    public BoardResponseDto getBoardByTitle(@RequestParam final String title) {

        return boardService.getBoardByTitle(title);
    }

    @PatchMapping("/board/{boardId}")
    public BoardResponseDto updateBoard(@PathVariable final Long boardId, @RequestBody UpdateBoardRequestDto request) {
        return boardService.updateBoard(boardId, request);
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoardList() {
        return boardService.getBoardList();
    }

    @DeleteMapping("/board/{boardId}")
    public String deleteBoard(@PathVariable final Long boardId) {
        boardService.deleteBoard(boardId);
        return boardId + "번 게시글이 삭제되었습니다.";
    }

}
