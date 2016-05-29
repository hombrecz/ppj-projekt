package cz.hombre.service;

import cz.hombre.data.Comment;
import cz.hombre.repositories.CommentRepository;
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
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments() {
        return StreamSupport.stream( commentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Comment comment) { commentRepository.save(comment);
    }

    public Comment getComment(UUID id) {
        return commentRepository.findOne(id);
    }


    public void saveOrUpdate(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(UUID id) {
        commentRepository.delete(id);
    }

    public void deleteComments() {
        commentRepository.deleteAll();
    }
}
