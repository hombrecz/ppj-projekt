package cz.hombre.repositories;

import cz.hombre.data.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentRepository extends CrudRepository<Comment, UUID> {

    @Query("update Comment as c set c.likesCount = c.likesCount + 1 where c.id = :id")
    public void incrementLikes(@Param("id") UUID id);

    @Query("update Comment as c set c.dislikesCount = c.dislikesCount + 1 where c.id = :id")
    public void incrementDisikes(@Param("id") UUID id);

}
