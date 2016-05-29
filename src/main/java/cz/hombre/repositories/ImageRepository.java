package cz.hombre.repositories;

import cz.hombre.data.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {

    public List<Image> findByName(String name);

    @Query("select i from Image as i where i.author.name=:author")
    public List<Image> findByAuthorName(@Param("author") String author);

    @Query("select i from Image as i where :tag in (select value from i.tagSet)")
//    @Query("SELECT i from Image as i where :tag member of i.tagSet.value")
    public List<Image> findByTagSetIsIn(@Param("tag")String tag);
}
