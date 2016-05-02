package cz.hombre.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ondrej.dlabola
 */
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue
    private int tag_id;

    @Column(name = "value")
    private String value;

    @ManyToMany(mappedBy = "images")
    private Set<Image> imageSet = new HashSet<>();
}
