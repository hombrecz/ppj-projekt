package hombre.repositories;

import hombre.data.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
