package cz.hombre.services;

import cz.hombre.data.Author;
import cz.hombre.data.Comment;
import cz.hombre.data.Image;
import cz.hombre.repositories.AuthorRepository;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Image> getImagesByName(String name) {
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

    public List<Image> getImagesByAuthor(String author) {
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

    public List<Image> getImagesByTag(String tag) {
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

    public Image getImageById(UUID id) {
        if (id == null) {
            return null;
        }
        return imageRepository.findOne(id);
    }

    public Image getFirstImage() {
        return imageRepository.findFirstByOrderById();
    }

    public Image getNextImageById(UUID prevId) {
        return imageRepository.findFirstByIdGreaterThanOrderById(prevId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void incrementLikesCount(UUID id) {
        Image image = imageRepository.findOne(id);
        image.setLikesCount(image.getLikesCount()+1);
        imageRepository.save(image);
    }

    public void incrementDislikesCount(UUID id) {
        Image image = imageRepository.findOne(id);
        image.setDislikesCount(image.getDislikesCount()+1);
        imageRepository.save(image);
    }

    public void addComment(Image image, String comment, UUID authorId) {
        Author author = authorRepository.findOne(authorId);
        commentRepository.save(new Comment(UUID.randomUUID(), comment, author, image, 0, 0));
    }
}
