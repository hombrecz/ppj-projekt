package cz.hombre.repositories;

import cz.hombre.data.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentRepository extends CrudRepository<Comment, UUID> {
}
