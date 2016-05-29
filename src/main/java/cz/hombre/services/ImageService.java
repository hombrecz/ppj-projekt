package cz.hombre.services;

import cz.hombre.data.Image;
import cz.hombre.repositories.ImageRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ondrej.dlabola
 */
@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getImagesByName(String name){
        if (name == null) {
            return null;
        }

        List<Image> images = imageRepository.findByName(name);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }

    public List<Image> getImagesByAuthor(String author){
        if (author == null) {
            return null;
        }

        List<Image> images = imageRepository.findByAuthorName(author);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }

    public List<Image> getImagesByTag(String tag){
        if (tag == null) {
            return null;
        }

        List<Image> images = imageRepository.findByTagSetIsIn(tag);
        Hibernate.initialize(images);

        if (images.size() == 0) {
            return null;
        }

        return images;
    }
}
