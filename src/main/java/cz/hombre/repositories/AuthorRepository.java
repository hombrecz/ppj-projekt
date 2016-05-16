package cz.hombre.repositories;

import cz.hombre.data.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer>  {

}
