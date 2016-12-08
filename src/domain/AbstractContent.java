package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;;

/**
 * @author lmrodrigues
 * @author flmachado
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractContent {

    @Id
    private Long   id;
    private Date   date;
    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private User   author;
    private String text;

    /**
     * creates a empty content
     */
    public AbstractContent() {
        this.id = null;
        this.date = null;
        this.author = null;
        this.text = null;
    }

    /**
     * creates the base of contents
     * 
     * @param id
     *            the ID of the content
     * @param author
     *            the author of the content
     * @param text
     *            the text of the content
     */
    public AbstractContent(Long id, User author, String text) {
        this.id = id;
        this.date = new Date();
        this.author = author;
        this.text = text;

    }

    /**
     * 
     * @return the id of the content
     */
    public Long getId() {
        return this.id;
    }

    /**
     * set a new id for the content
     * 
     * @param id
     *            the new id for the content
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return return the date of the content
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * set the date of content
     * 
     * @param date
     *            the new date of the content
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the author of the content
     */
    public User getAuthor() {
        return author;
    }

    /**
     * set a new author for the content
     * 
     * @param author
     *            the new author of the content
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * @return the text of the content
     */
    public String getText() {
        return text;
    }

    /**
     * set a new text for the content
     * 
     * @param text
     *            the new text of the content
     */
    public void setText(String text) {
        this.text = text;
    }

}
