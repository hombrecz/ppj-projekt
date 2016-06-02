package cz.hombre.services;

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
        commentRepository.updateLikesCount(id);
    }

    public void incrementDislikesCount(UUID id) {
        commentRepository.updateDislikesCount(id);
    }
}
