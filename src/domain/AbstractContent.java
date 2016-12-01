package domain;

/**
 * @author lmrodrigues 
 * 
 */

import java.util.Date;
import javax.persistence.*;;

@Entity
public abstract class AbstractContent {

	private Date date;
	private String author;
	private String text;
	@Id private Double id;

	public Date getDate() {
		return null;
	}

	public void setDate(Date date) {

	}

	public String getAuthor() {
		return null;
	}

	public void setAuthor(String author) {

	}

	public String getText() {
		return null;
	}

	public void setText(String text) {

	}

	public Integer getId() {
		return null;
	}

	public void setId(Integer id) {

	}

}
