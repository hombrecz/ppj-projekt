package cz.hombre.service;

import cz.hombre.data.Author;
import cz.hombre.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ondrej.dlabola
 */

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Author author) {
        authorRepository.save(author);
    }

    public Author getOffer(Integer id) {
        return authorRepository.findOne(id);
    }


    public void saveOrUpdate(Author author) {
        authorRepository.save(author);
    }

    public void delete(int id) {
        authorRepository.delete(id);
    }

    public void deleteAuthors() {
        authorRepository.deleteAll();
    }
}
