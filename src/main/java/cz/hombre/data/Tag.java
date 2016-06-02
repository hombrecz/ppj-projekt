package cz.hombre.data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Entity
@Table(name = "tags")
@Document(collection = "tags")
public class Tag {


    @Id
    @org.springframework.data.annotation.Id
    @Column
    private UUID id;

    @Column(name = "value")
    @Size(max = 16)
    private String value;

    @ManyToMany(mappedBy = "tagSet")
    @DBRef(lazy=true)
    private Set<Image> imageSet = new HashSet<>();

    public Tag() {
    }

    public Tag(UUID id) {
        this.id = id;
    }

    public Tag(String value) {
        this.value = value;
    }

    public Tag(UUID id, String value, Set<Image> imageSet) {
        this.id = id;
        this.value = value;
        this.imageSet = imageSet;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        if (value != null ? !value.equals(tag.value) : tag.value != null) return false;
        return true;

    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
