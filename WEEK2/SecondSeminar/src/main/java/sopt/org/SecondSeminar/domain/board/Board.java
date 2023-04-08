package sopt.org.SecondSeminar.domain.board;

import lombok.Getter;

@Getter
public class Board {

    private Long id;
    private String title;
    private String content;
    private String writer;

    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "title: " + title + "\n" +
                "content: " + content + "\n" +
                "writer: " + writer;
    }
}
