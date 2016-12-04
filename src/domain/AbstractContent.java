package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;;

/**
 * @author lmrodrigues
 * 
 */

@Entity
public abstract class AbstractContent {

    @Id
    private Integer id;
    private Date    date;
    private String  author;
    private String  text;

    public AbstractContent(Integer id, String author, String text) {
        this.id = id;
        this.date = new Date();
        this.author = author;
        this.text = text;

    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
