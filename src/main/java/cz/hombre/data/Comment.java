package cz.hombre.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ondrej.dlabola
 */
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "comment")
    private String comment;

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

    public Comment() {

    }

    public Comment(String comment, Author author) {
        this.comment = comment;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (id != comment1.id) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (createdDate != null ? !createdDate.equals(comment1.createdDate) : comment1.createdDate != null)
            return false;
        if (changedDate != null ? !changedDate.equals(comment1.changedDate) : comment1.changedDate != null)
            return false;
        if (likesCount != null ? !likesCount.equals(comment1.likesCount) : comment1.likesCount != null) return false;
        if (dislikesCount != null ? !dislikesCount.equals(comment1.dislikesCount) : comment1.dislikesCount != null)
            return false;
        return !(author != null ? !author.equals(comment1.author) : comment1.author != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (changedDate != null ? changedDate.hashCode() : 0);
        result = 31 * result + (likesCount != null ? likesCount.hashCode() : 0);
        result = 31 * result + (dislikesCount != null ? dislikesCount.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", changedDate=" + changedDate +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                ", author=" + author +
                '}';
    }
}
