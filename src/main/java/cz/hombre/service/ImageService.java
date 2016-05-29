package cz.hombre.service;

import cz.hombre.data.Image;
import cz.hombre.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ondrej.dlabola
 */
@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getImages() {
        return StreamSupport.stream( imageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Image image) { imageRepository.save(image);
    }

    public Image getImage(UUID id) {
        return imageRepository.findOne(id);
    }

    public void saveOrUpdate(Image image) {
        imageRepository.save(image);
    }

    public void delete(UUID id) {
        imageRepository.delete(id);
    }

    public void deleteImages() {
        imageRepository.deleteAll();
    }
}
