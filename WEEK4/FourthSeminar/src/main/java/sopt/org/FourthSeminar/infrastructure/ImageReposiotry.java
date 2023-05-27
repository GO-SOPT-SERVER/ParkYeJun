package sopt.org.FourthSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.FourthSeminar.domain.Image;

public interface ImageReposiotry extends Repository<Image, Long> {

    // CREATE
    void save(Image image);
}
