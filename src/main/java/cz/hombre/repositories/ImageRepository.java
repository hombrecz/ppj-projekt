package cz.hombre.repositories;

import cz.hombre.data.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {
}
