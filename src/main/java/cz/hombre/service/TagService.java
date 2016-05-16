package cz.hombre.service;

import cz.hombre.data.Tag;
import cz.hombre.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ondrej.dlabola
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags() {
        return StreamSupport.stream( tagRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Tag tag) { tagRepository.save(tag);
    }

    public Tag getTag(Integer id) {
        return tagRepository.findOne(id);
    }


    public void saveOrUpdate(Tag tag) {
        tagRepository.save(tag);
    }

    public void delete(int id) {
        tagRepository.delete(id);
    }

    public void deleteTags() {
        tagRepository.deleteAll();
    }
}
