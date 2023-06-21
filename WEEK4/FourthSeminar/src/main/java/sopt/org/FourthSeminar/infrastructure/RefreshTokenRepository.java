package sopt.org.FourthSeminar.infrastructure;

import org.springframework.data.repository.CrudRepository;
import sopt.org.FourthSeminar.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
