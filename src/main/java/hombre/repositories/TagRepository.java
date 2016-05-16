package hombre.repositories;

import hombre.data.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
