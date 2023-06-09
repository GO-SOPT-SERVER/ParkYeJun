package sopt.org.FourthSeminar.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;

//    @Column  // nullable = false 설정 X -> 이미 DB에 존재하는 테이블에 NOTNULL 컬럼을 추가하려면 무결성이 보장되지 않을 수 있어 디폴트값을 지정해주거나 NUll을 허용해줘야 한다.
//    private String thumbnail;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isPublic;

    public Board(User user, String title, String content, Boolean isPublic) {
        this.user = user;
//        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
    }

    public static Board newInstance(User user, String title, String content, Boolean isPublic) {
        return new Board(user, title, content, isPublic);
    }
}
