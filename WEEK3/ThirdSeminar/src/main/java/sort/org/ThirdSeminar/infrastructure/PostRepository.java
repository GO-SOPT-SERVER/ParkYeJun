package sort.org.ThirdSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sort.org.ThirdSeminar.domain.Post;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);
    Post findById(Long postId);
}
