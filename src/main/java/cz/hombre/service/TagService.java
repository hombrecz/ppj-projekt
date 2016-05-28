package cz.hombre.service;

import cz.hombre.data.Tag;
import cz.hombre.repositories.TagRepository;
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
public class TagService {

    @Qualifier("tagRepository")
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags() {
        return StreamSupport.stream( tagRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Tag tag) { tagRepository.save(tag);
    }

    public Tag getTag(UUID id) {
        return tagRepository.findOne(id);
    }


    public void saveOrUpdate(Tag tag) {
        tagRepository.save(tag);
    }

    public void delete(UUID id) {
        tagRepository.delete(id);
    }

    public void deleteTags() {
        tagRepository.deleteAll();
    }
}
