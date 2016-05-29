package cz.hombre;

import cz.hombre.data.Author;
import cz.hombre.service.AuthorService;
import cz.hombre.service.CommentService;
import cz.hombre.service.ImageService;
import cz.hombre.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ondrej.dlabola
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
public class DBTest {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Before
    public void init() {
        authorService.deleteAuthors();
        commentService.deleteComments();
        imageService.deleteImages();
        tagService.deleteTags();
    }

    @Test
    public void findByName() {

    }

    @Test
    public void findByTag() {

    }

    @Test
    public void findByAuthor() {

    }
}
