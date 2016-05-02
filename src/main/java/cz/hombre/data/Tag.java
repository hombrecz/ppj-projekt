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

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
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

        if (tag_id != tag.tag_id) return false;
        if (value != null ? !value.equals(tag.value) : tag.value != null) return false;
        return !(imageSet != null ? !imageSet.equals(tag.imageSet) : tag.imageSet != null);

    }

    @Override
    public int hashCode() {
        int result = tag_id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (imageSet != null ? imageSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag_id=" + tag_id +
                ", value='" + value + '\'' +
                ", imageSet=" + imageSet +
                '}';
    }
}
