package sopt.org.FourthSeminar.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 모든 엔티티에서 공통적으로 사용되는 컬럼: 생성일시, 수정일시
 * -> 이를 하나의 클래스로 만들어 어노테이션으로 간단하게 사용할 수 있도록 한다.
 *
 * 왜 추상 클래스로?
 * 혼자서만은 크게 의미 없는 클래스 -> 이를 상속받아서 사용하는 경우가 많으므로 객체 사용 시점을 위해 추상 클래스로 만들어둠 // TODO 보완
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  // AuditingEntityListener.class: 값의 변경을 인지하여 자동으로 반영해주는 기능
public abstract class AuditingTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
