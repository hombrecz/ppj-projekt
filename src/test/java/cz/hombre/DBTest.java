package cz.hombre;

import cz.hombre.data.Author;
import cz.hombre.data.Comment;
import cz.hombre.data.Image;
import cz.hombre.data.Tag;
import cz.hombre.repositories.AuthorRepository;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import cz.hombre.repositories.TagRepository;
import cz.hombre.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author ondrej.dlabola
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@Transactional
public class DBTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagRepository tagRepository;

    private UUID author1ID = UUID.randomUUID();
    private UUID author2ID = UUID.randomUUID();
    private UUID author3ID = UUID.randomUUID();

    private UUID comment1ID = UUID.randomUUID();
    private UUID comment2ID = UUID.randomUUID();
    private UUID comment3ID = UUID.randomUUID();

    private UUID image1ID = UUID.randomUUID();
    private UUID image2ID = UUID.randomUUID();
    private UUID image3ID = UUID.randomUUID();

    private UUID tag1ID = UUID.randomUUID();
    private UUID tag2ID = UUID.randomUUID();
    private UUID tag3ID = UUID.randomUUID();

    private Author author1;
    private Author author2;
    private Author author3;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    private Image image1;
    private Image image2;
    private Image image3;

    @Before
    public void init() {
        imageRepository.deleteAll();
        tagRepository.deleteAll();
        commentRepository.deleteAll();
        authorRepository.deleteAll();
        initDBData();
    }

    private void initDBData() {
        saveAuthors();
        saveTags();
        saveImages();
        saveComments();
    }

    private void saveAuthors() {
        author1 = new Author(author1ID, "adam");
        author2 = new Author(author2ID, "bozena");
        author3 = new Author(author3ID, "cyril");


        HashSet<Author> authors = new HashSet<>();
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authorRepository.save(authors);
    }

    private void saveComments() {
        comment1 = new Comment(comment1ID, "Test1", new Author(author1ID), new Image(image1ID), 0, 0);
        comment2 = new Comment(comment2ID, "Test2", new Author(author2ID), new Image(image2ID), 0, 0);
        comment3 = new Comment(comment3ID, "Test3", new Author(author3ID), new Image(image3ID), 0, 0);

        HashSet<Comment> comments = new HashSet<>();
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);

        commentRepository.save(comments);
    }

    private void saveImages() {
        HashSet<Comment> commentSet1 = new HashSet<>();
        commentSet1.add(new Comment(comment1ID));

        HashSet<Tag> tagsSet1 = new HashSet<>();
        tagsSet1.add(tag1);
        tagsSet1.add(tag2);
        image1 = new Image(image1ID, "image1", "url://something/1", new Author(author1ID), commentSet1, tagsSet1, 0, 0);

        HashSet<Comment> commentSet2 = new HashSet<>();
        commentSet2.add(new Comment(comment2ID));
        image2 = new Image(image2ID, "image2", "url://something/2", new Author(author2ID), commentSet2, tagsSet1, 0, 0);

        HashSet<Tag> tagsSet2 = new HashSet<>();
        tagsSet2.add(tag3);

        HashSet<Comment> commentSet3 = new HashSet<>();
        commentSet3.add(new Comment(comment3ID));
        image3 = new Image( image3ID, "image3", "url://something/3", new Author(author3ID), commentSet3, tagsSet2, 0, 0);

        HashSet<Image> images = new HashSet<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        imageRepository.save(images);
    }

    private void saveTags() {
        HashSet<Image> imageSet1 = new HashSet<>();
        imageSet1.add(new Image("image1"));
        imageSet1.add(new Image("image2"));
        tag1 = new Tag(tag1ID, "tag1", imageSet1);
        tag2 = new Tag(tag2ID, "tag2", imageSet1);

        HashSet<Image> imageSet2 = new HashSet<>();
        imageSet1.add(new Image("image¨3"));
        tag3 = new Tag(tag3ID, "tag3", imageSet2);

        HashSet<Tag> tags = new HashSet<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tagRepository.save(tags);
    }

    @Test
    public void testGetByName() {
        List<Image> foundImages1 = imageService.getImagesByName("image1");
        assertArrayEquals("Results of first search should match", new Image[]{image1},
                foundImages1.toArray(new Image[foundImages1.size()]));

        List<Image> foundImages2 = imageService.getImagesByName("image2");
        assertNotEquals("Results of second search should not match", new Image[]{image1},
                foundImages2.toArray(new Image[foundImages2.size()]));
    }

    @Test
    public void testGetByTag() {
        List<Image> foundImages1 = imageService.getImagesByTag("tag3");
        assertArrayEquals("Results of first search should match", new Image[]{image3},
                foundImages1.toArray(new Image[foundImages1.size()]));

        List<Image> foundImages2 = imageService.getImagesByTag("tag3");
        assertNotEquals("Results of second search should not match", new Image[]{image1, image2},
                foundImages2.toArray(new Image[foundImages2.size()]));
    }

    @Test
    public void testGetByAuthor() {
        List<Image> foundImages1 = imageService.getImagesByAuthor("adam");
        assertArrayEquals("Results of first search should match", new Image[]{image1},
                foundImages1.toArray(new Image[foundImages1.size()]));

        List<Image> foundImages2 = imageService.getImagesByAuthor("bozena");
        assertNotEquals("Results of second search should not match", new Image[]{image1},
                foundImages2.toArray(new Image[foundImages2.size()]));
    }
}
