package cz.hombre.services;

import cz.hombre.data.Comment;
import cz.hombre.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void incrementLikesCount(UUID id) {
        Comment comment = commentRepository.findOne(id);
        comment.setLikesCount(comment.getLikesCount()+1);
        commentRepository.save(comment);
    }

    public void incrementDislikesCount(UUID id) {
        Comment comment = commentRepository.findOne(id);
        comment.setDislikesCount(comment.getDislikesCount()+1);
        commentRepository.save(comment);
    }
}
