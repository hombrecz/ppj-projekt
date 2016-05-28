package cz.hombre.provisioner;

import cz.hombre.data.Author;
import cz.hombre.data.Comment;
import cz.hombre.data.Image;
import cz.hombre.data.Tag;
import cz.hombre.repositories.AuthorRepository;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import cz.hombre.repositories.TagRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author ondrej.dlabola
 */
@Component
@Transactional
public class DBProvisioner implements InitializingBean {

    @Qualifier("authorRepository")
    @Autowired
    private AuthorRepository authorRepository;

    @Qualifier("commentRepository")
    @Autowired
    private CommentRepository commentRepository;

    @Qualifier("imageRepository")
    @Autowired
    private ImageRepository imageRepository;

    @Qualifier("tagRepository")
    @Autowired
    private TagRepository tagRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        initAuthors();
        initComments();
        initImages();
        initTags();
    }

    private boolean initAuthors() throws IOException {
        boolean isEmpty = authorRepository.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/provision/author.txt")))) {
                List<Author> authorsList = read.lines().map(s -> s.split("\\s"))
                        .map(a -> new Author(UUID.fromString(a[0]), a[1])).collect(Collectors.toList());
                authorRepository.save(authorsList);
            }
        }
        return isEmpty;
    }

    private boolean initComments() throws IOException {
        boolean isEmpty = commentRepository.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/provision/comment.txt")))) {
                List<Comment> commentsList = read.lines().map(s -> s.split("\\s"))
                        .map(c -> new Comment(UUID.fromString(c[0]), c[1], new Author(UUID.fromString(c[2])),
                                new Image(UUID.fromString(c[3])), Integer.parseInt(c[4]), Integer.parseInt(c[5]))).collect(Collectors.toList());

                commentRepository.save(commentsList);
            }
        }
        return isEmpty;
    }

    private boolean initImages() throws IOException {
        boolean isEmpty = imageRepository.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/provision/image.txt")))) {
                List<Image> imagesList = read.lines().map(s -> s.split("\\s"))
                        .map(i -> new Image(UUID.fromString(i[0]), i[1], i[2],  new Author(UUID.fromString(i[3])),
                                getCommentSetFromString(i[4]), getTagSetFromString(i[5]), Integer.parseInt(i[6]),
                                Integer.parseInt(i[7]))).collect(Collectors.toList());
                imageRepository.save(imagesList);
            }
        }
        return isEmpty;
    }

    private Set<Comment> getCommentSetFromString(String arrayInString) {
        HashSet<Comment> result = new HashSet<>();
        for (String s:arrayInString.split(",")) {
            result.add(new Comment(UUID.fromString(s)));
        }
        return result;
    }

    private Set<Tag> getTagSetFromString(String arrayInString) {
        HashSet<Tag> result = new HashSet<>();
        for (String s:arrayInString.split(",")) {
            result.add(new Tag(UUID.fromString(s)));
        }
        return result;
    }

    private boolean initTags() throws IOException {
        boolean isEmpty = tagRepository.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/provision/tag.txt")))) {
                List<Tag> tagsList = read.lines().map(s -> s.split("\\s"))
                        .map(t -> new Tag(UUID.fromString(t[0]), t[1], (getImageSetFromString(t[2])))).collect(Collectors.toList());

                tagRepository.save(tagsList);
            }
        }
        return isEmpty;
    }

    private Set<Image> getImageSetFromString(String arrayInString) {
        HashSet<Image> result = new HashSet<>();
        for (String s:arrayInString.split(",")) {
            result.add(new Image(UUID.fromString(s)));
        }
        return result;
    }
}
