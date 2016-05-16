package hombre.repositories;

import hombre.data.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
}
