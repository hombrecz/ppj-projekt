package cz.hombre.repositories;

import cz.hombre.data.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface TagRepository extends CrudRepository<Tag, UUID> {

    public List<Tag> findByValue(String value);
}
