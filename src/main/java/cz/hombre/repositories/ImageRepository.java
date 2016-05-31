package cz.hombre.repositories;

import cz.hombre.data.Image;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImageRepository extends CrudRepository<Image, UUID> {

    public List<Image> findByName(String name);

    @Query("select i from Image as i where i.author.name=:author")
    public List<Image> findByAuthorName(@Param("author") String author);

    @Query("select i from Image as i inner join i.tagSet tags where tags.value = :tag")
    public List<Image> findByTagSetIsIn(@Param("tag")String tag);

    @Transactional
    @Modifying
    @Query("update Image as i set i.likesCount = i.likesCount + 1 where i.id = :id")
    public void updateLikesCount(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query("update Image as i set i.dislikesCount = i.dislikesCount + 1 where i.id = :id")
    public void updateDislikesCount(@Param("id") UUID id);
}
