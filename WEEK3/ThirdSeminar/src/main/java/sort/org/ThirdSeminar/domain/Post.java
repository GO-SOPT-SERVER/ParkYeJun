package sort.org.ThirdSeminar.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Builder
    public Post(Long id, String title, User writer, String content, Category category) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.category = category;
    }

    // 연관관계의 주인만이 외래키를 관리할 수 있고, 실제 DB에 반영이 가능하게 한다. 따라서 양방향 연관관계를 설정하는 부분은 관계의 주인인 Post가 가지고 있어야 한다.
    public void setWriter(User user) {

        // 기존의 관계를 제거 *사실 작성자가 변경되는 경우는 거의 없으나, JPA 연관관계 매핑 시 변경될 수 있는 경우를 고려하여 기존의 관계를 끊어주는 처리도 필요하다. (양방향 모두 끊어지도록)
        if (this.writer != null) {
            this.writer.getPostList().remove(this);
        }

        this.writer = user;  // Post -> Member 방향으로 연관관계 설정 (연관관계의 주인
        writer.getPostList().add(this);  // Member -> Post 방향 : DB에서는 무시하는 로직이지만, 순수한 객체의 관점에서 동작하려면 꼭 양방향으로 연결해주는 것이 필요하다.
    }
}
