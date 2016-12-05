package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;;

/**
 * @author lmrodrigues
 * @author flmachado
 */

@Entity
public abstract class AbstractContent {

    @Id
    private Integer id;
    private Date    date;
    private User    author;
    private String  text;

    public AbstractContent(Integer id, User author, String text) {
        this.id = id;
        this.date = new Date();
        this.author = author;
        this.text = text;

    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
