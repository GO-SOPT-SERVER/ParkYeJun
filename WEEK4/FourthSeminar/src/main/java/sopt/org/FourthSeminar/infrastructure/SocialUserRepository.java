package sopt.org.FourthSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.FourthSeminar.domain.SocialUser;

public interface SocialUserRepository extends Repository<SocialUser, Long> {

    // CREATE
    void save(SocialUser socialUser);
}
