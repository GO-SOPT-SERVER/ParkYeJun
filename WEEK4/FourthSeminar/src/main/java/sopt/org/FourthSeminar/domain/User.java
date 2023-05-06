package sopt.org.FourthSeminar.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity   // 해당 Class를 Entity로 사용하겠다! -> Id 를 반드시 갖도록 함(없다면 빨간줄 발생)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // protected -> Proxy 에 의해 함께 묶이게 됨
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public static User newInstance(String nickname, String email, String password) {
        return new User(nickname, email, password);
    }

}
