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

    private Author author1 = new Author(
            author1ID,
            "adam"
    );
    private Author author2 = new Author(
            author2ID,
            "bozena"
    );
    private Author author3 = new Author(
            author3ID,
            "cyril"
    );

    private Comment comment1 = new Comment(
            comment1ID,
            "Test1",
            new Author(author1ID),
            new Image(image1ID),
            0,
            0
    );
    private Comment comment2 = new Comment(
            comment2ID,
            "Test2",
            new Author(author2ID),
            new Image(image2ID),
            0,
            0
    );
    private Comment comment3 = new Comment(
            comment3ID,
            "Test3",
            new Author(author3ID),
            new Image(image3ID),
            0,
            0
    );

    private Tag tag1 = new Tag(
            tag1ID,
            "tag1",
            new HashSet<Image>() {{
                add(new Image("image1"));
                add(new Image("image2"));
            }}
    );
    private Tag tag2 = new Tag(
            tag2ID,
            "tag2",
            new HashSet<Image>() {{
                add(new Image("image1"));
                add(new Image("image2"));
            }}
    );
    private Tag tag3 = new Tag(
            tag3ID,
            "tag3",
            new HashSet<Image>() {{
                add(new Image("image3"));
            }}
    );

    private Image image1 = new Image(
            image1ID,
            "image1",
            "url://something/1",
            new Author(author1ID),
            new HashSet<Comment>() {{
                add(new Comment(comment1ID));
            }},
            new HashSet<Tag>() {{
                add(tag1);
                add(tag2);
            }},
            0,
            0
    );

    private Image image2 = new Image(
            image2ID,
            "image2",
            "url://something/2",
            new Author(author2ID),
            new HashSet<Comment>() {{
                add(new Comment(comment2ID));
            }},
            new HashSet<Tag>() {{
                add(tag1);
                add(tag2);
            }},
            0,
            0
    );

    private Image image3 = new Image(
            image3ID,
            "image3",
            "url://something/3",
            new Author(author3ID),
            new HashSet<Comment>() {{
                add(new Comment(comment3ID));
            }},
            new HashSet<Tag>() {{
                add(tag3);
            }},
            0,
            0
    );

    @Before
    public void init() {
        imageRepository.deleteAll();
        tagRepository.deleteAll();
        commentRepository.deleteAll();
        authorRepository.deleteAll();
        initDBData();
    }

    private void initDBData() {
        authorRepository.save(new HashSet<Author>() {{
            add(author1);
            add(author2);
            add(author3);
        }});

        commentRepository.save(new HashSet<Comment>() {{
            add(comment1);
            add(comment2);
            add(comment3);
        }});

        tagRepository.save(new HashSet<Tag>() {{
            add(tag1);
            add(tag2);
            add(tag3);
        }});

        imageRepository.save(new HashSet<Image>() {{
            add(image1);
            add(image2);
            add(image3);
        }});
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
