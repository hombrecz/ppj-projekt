package cz.hombre.data;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Entity
@Table(name = "images")
@Document(collection = "images")
public class Image {

    @Id
    @org.springframework.data.annotation.Id
    @Column
    private UUID id;

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
    @JoinColumn(name = "author")
    @DBRef
    private Author author;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @DBRef
    private Set<Comment> commentSet = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "image_tags",
            joinColumns = {@JoinColumn(name = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")},
            uniqueConstraints = {@UniqueConstraint(
            columnNames = {"image_id", "tag_id"})})
    @DBRef
    private Set<Tag> tagSet = new HashSet<>();

    public Image() {
    }

    public Image(UUID id) {
        this.id = id;
    }

    public Image(String name) {
        this.name = name;
    }

    public Image(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Image(UUID id, String name, String url, Author author, Set<Comment> commentSet, Set<Tag> tagSet,
                 int likesCount, int dislikesCount) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.author = author;
        this.commentSet = commentSet;
        this.tagSet = tagSet;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.createdDate = new Date();
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
    public String toString() {
        return "Image{" +
                "id=" + id +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (id != null ? !id.equals(image.id) : image.id != null) return false;
        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        if (url != null ? !url.equals(image.url) : image.url != null) return false;
        if (createdDate != null ? !createdDate.equals(image.createdDate) : image.createdDate != null) return false;
        if (changedDate != null ? !changedDate.equals(image.changedDate) : image.changedDate != null) return false;
        if (likesCount != null ? !likesCount.equals(image.likesCount) : image.likesCount != null) return false;
        if (dislikesCount != null ? !dislikesCount.equals(image.dislikesCount) : image.dislikesCount != null)
            return false;
        if (author != null ? !author.getId().equals(image.author.getId()) : image.author != null) return false;
        return true;
    }
}
