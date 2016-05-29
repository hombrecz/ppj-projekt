package cz.hombre;

import cz.hombre.repositories.AuthorRepository;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import cz.hombre.repositories.TagRepository;
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
    private AuthorRepository authorRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    @Before
    public void init() {
        authorRepository.deleteAll();
        commentRepository.deleteAll();
        imageRepository.deleteAll();
        tagRepository.deleteAll();
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
