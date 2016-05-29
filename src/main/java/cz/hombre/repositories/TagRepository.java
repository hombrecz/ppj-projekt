package cz.hombre.repositories;

import cz.hombre.data.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, UUID> {

    public List<Tag> findByValue(String value);
}
