package cz.hombre.services;

import cz.hombre.data.Comment;
import cz.hombre.data.Image;
import cz.hombre.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ondrej.dlabola
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void incrementLikes(Comment comment){
        commentRepository.incrementLikes(comment.getId());
    }

    public void incrementDisikes(Comment comment){
        commentRepository.incrementDisikes(comment.getId());
    }
}
