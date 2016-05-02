package cz.hombre.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ondrej.dlabola
 */
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    private int image_id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "created")
    private Date createdDate;

    @Column(name = "changed")
    private Date changedDate;

    @Column(name = "likes")
    private Integer likesCount;

    @Column(name = "dislikes")
    private Integer dislikesCount;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable( name="image_comments",
                joinColumns = {@JoinColumn(name="image_id")},
                inverseJoinColumns = {@JoinColumn(name="comment_id")})
    private Set<Comment> commentSet = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable( name="image_tags",
                joinColumns = {@JoinColumn(name="image_id")},
                inverseJoinColumns = {@JoinColumn(name="tag_id")})
    private Set<Tag> tagSet = new HashSet<>();

    public Image() {
    }

    public Image(String name, String url, Author author) {
        this.name = name;
        this.url = url;
        this.author = author;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(Integer dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (image_id != image.image_id) return false;
        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        if (url != null ? !url.equals(image.url) : image.url != null) return false;
        if (createdDate != null ? !createdDate.equals(image.createdDate) : image.createdDate != null) return false;
        if (changedDate != null ? !changedDate.equals(image.changedDate) : image.changedDate != null) return false;
        if (likesCount != null ? !likesCount.equals(image.likesCount) : image.likesCount != null) return false;
        if (dislikesCount != null ? !dislikesCount.equals(image.dislikesCount) : image.dislikesCount != null)
            return false;
        if (author != null ? !author.equals(image.author) : image.author != null) return false;
        if (commentSet != null ? !commentSet.equals(image.commentSet) : image.commentSet != null) return false;
        return !(tagSet != null ? !tagSet.equals(image.tagSet) : image.tagSet != null);

    }

    @Override
    public int hashCode() {
        int result = image_id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (changedDate != null ? changedDate.hashCode() : 0);
        result = 31 * result + (likesCount != null ? likesCount.hashCode() : 0);
        result = 31 * result + (dislikesCount != null ? dislikesCount.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (commentSet != null ? commentSet.hashCode() : 0);
        result = 31 * result + (tagSet != null ? tagSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image_id=" + image_id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                ", author=" + author +
                ", commentSet=" + commentSet +
                ", tagSet=" + tagSet +
                '}';
    }
}
