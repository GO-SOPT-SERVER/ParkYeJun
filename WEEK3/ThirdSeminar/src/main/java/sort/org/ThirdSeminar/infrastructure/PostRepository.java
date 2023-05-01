package sort.org.ThirdSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sort.org.ThirdSeminar.domain.Post;

import java.util.Optional;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);
    Optional<Post> findById(Long postId);
}
