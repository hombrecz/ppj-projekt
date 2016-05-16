package cz.hombre.service;

import cz.hombre.data.Image;
import cz.hombre.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ondrej.dlabola
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getImages() {
        return StreamSupport.stream( imageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Image image) { imageRepository.save(image);
    }

    public Image getImage(Integer id) {
        return imageRepository.findOne(id);
    }

    public void saveOrUpdate(Image image) {
        imageRepository.save(image);
    }

    public void delete(int id) {
        imageRepository.delete(id);
    }

    public void deleteImages() {
        imageRepository.deleteAll();
    }
}
