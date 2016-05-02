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

}
