package cz.hombre.provisioner;

import cz.hombre.data.Author;
import cz.hombre.data.Comment;
import cz.hombre.data.Image;
import cz.hombre.data.Tag;
import cz.hombre.repositories.AuthorRepository;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import cz.hombre.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@Transactional
@Component
@Profile({"mongo", "postgre"})
public class DBProvisioner implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DBProvisioner.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void afterPropertiesSet() throws Exception {


        log.debug("Loading data to database");
        initAuthors();
        initImages();
        initTags();
        initComments();
        log.debug("Loading finished");
    }

    private boolean initAuthors() throws IOException {
        boolean isEmpty = authorRepository.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/provision/author.txt")))) {
                List<Author> authorsList = read.lines().map(s -> s.split("; "))
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
                List<Comment> commentsList = read.lines().map(s -> s.split("; "))
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
                List<Image> imagesList = read.lines().map(s -> s.split("; "))
                        .map(i -> new Image(UUID.fromString(i[0]), i[1], i[2], new Author(UUID.fromString(i[3])),
                                getCommentSetFromString(i[4]), getTagSetFromString(i[5]), Integer.parseInt(i[6]),
                                Integer.parseInt(i[7]))).collect(Collectors.toList());
                imageRepository.save(imagesList);
            }
        }
        return isEmpty;
    }

    private boolean initTags() throws IOException {
        boolean isEmpty = tagRepository.count() == 0;
        if (isEmpty) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/provision/tag.txt")))) {
                List<Tag> tagsList = read.lines().map(s -> s.split("; "))
                        .map(t -> new Tag(UUID.fromString(t[0]), t[1], (getImageSetFromString(t[2])))).collect(Collectors.toList());

                tagRepository.save(tagsList);
            }
        }
        return isEmpty;
    }

    private Set<Comment> getCommentSetFromString(String arrayInString) {
        HashSet<Comment> result = new HashSet<>();
        for (String s : arrayInString.split(",, ")) {
            if (!s.isEmpty()) {
                result.add(new Comment(UUID.fromString(s)));
            }
        }
        return result;
    }

    private Set<Tag> getTagSetFromString(String arrayInString) {
        HashSet<Tag> result = new HashSet<>();
        for (String s : arrayInString.split(",, ")) {
            if (!s.isEmpty()) {
                result.addAll(tagRepository.findByValue(s));
            }
        }
        return result;
    }

    private Set<Image> getImageSetFromString(String arrayInString) {
        HashSet<Image> result = new HashSet<>();
        String[] strings = arrayInString.split(",, ");
        for (int i = 0; i < strings.length / 2; i++) {
            if (!strings[i * 2].isEmpty() && !strings[i * 2 + 1].isEmpty()) {
                result.add(new Image(UUID.fromString(strings[i * 2 + 1]), strings[i * 2]));
            }
        }
        return result;
    }
}
