package cz.hombre.data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ondrej.dlabola
 */


@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private int author_id;

    @Column(name = "name")
    private String name;

    @Column(name = "registration")
    private Date registrationDate;

    public Author() {}

    public Author(String name, Date registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (author_id != author.author_id) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        return !(registrationDate != null ? !registrationDate.equals(author.registrationDate) : author.registrationDate != null);

    }

    @Override
    public int hashCode() {
        int result = author_id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
