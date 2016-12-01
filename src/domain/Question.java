package domain;

/**
 * @author lmrodrigues 
 * 
 */

import java.util.List;
import javax.persistence.*;


@Entity
public class Question extends AbstractContent {

	private String title;
	private List<String> tags;
	private List<Answer> answers;
	private List<Comment> comments;
	private Answer bestAnswer;
	private Status status;

	
	
	public Question() {

	}
	
	public Question(String text, List<String> tags, String title, String author, double id) {

	}

	public String getTitle() {
		return null;
	}

	public Void setTitle(String newTitle) {
		return null;
	}

	public List<String> getTags() {
		return null;
	}

	public Void setTags(List<String> tags) {
		return null;
	}

	public List<Answer> getAnswers() {
		return null;
	}

	public void addAnswer(Answer answer) {

	}

	public void delAnswer(Answer answer) {

	}

	public List<Comment> getComments() {
		return null;
	}

	public void addComment(Comment comment) {

	}

	public void delComment(Comment comment) {

	}

	public Answer getBestAnswer() {
		return null;
	}

	public void setBestAnswer(Answer bestAnswer) {

	}

	public Status getStatus() {
		return null;
	}

	public Void openQuestion() {
		return null;
	}

	public Void closeQuestion() {
		return null;
	}

}
