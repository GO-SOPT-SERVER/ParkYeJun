package sort.org.ThirdSeminar.infrastructure;


import org.springframework.data.repository.Repository;
import sort.org.ThirdSeminar.domain.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {  // JpaRepository 의 상위 인터페이스를 상속받는다.
    void save(User user);
    Optional<User> findById(Long userId);
}
