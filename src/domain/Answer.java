package domain;

/**
 * @author lmrodrigues 
 * 
 */

import java.util.List;
import javax.persistence.*;

@Entity
public class Answer extends AbstractContent {

	private List<Comment> comments;
	private Integer upVotes;
	private Integer downVotes;

	public Answer() {

	}

	public Answer(String text, String author, Integer id) {

	}

	public List<Comment> getComments() {
		return null;
	}

	public void addComment(Comment comment) {

	}

	public Void delComment(Comment comment) {
		return null;
	}

	public Integer getUpVotes() {
		return null;
	}

	public Void addUpVotes() {
		return null;
	}

	public Integer getDownVotes() {
		return null;
	}

	public Void addDownVotes() {
		return null;
	}

}
