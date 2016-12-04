package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/***
 * @author lmrodrigues
 * 
 */

@Entity
public class Question extends AbstractContent {

    private String        title;
    private List<String>  tags;
    private List<Answer>  answers;
    private List<Comment> comments;
    private Answer        bestAnswer;
    private Status        status;    //

    // public Question() {
    //
    // }

    public Question(String text, List<String> tags, String title, String author, Integer id) {
        super(id, author, text);
        this.title = title;
        this.tags = tags;
        this.answers = new ArrayList<Answer>();
        this.comments = new ArrayList<Comment>();

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void delAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void delComment(Comment comment) {
        this.comments.remove(comment);
    }

    public Answer getBestAnswer() {
        return this.bestAnswer;
    }

    public void setBestAnswer(Answer bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    public Status getStatus() {
        return this.status;
    }

    public void openQuestion() {
        this.status = Status.OPEN;
    }

    public void closeQuestion() {
        this.status = Status.CLOSED;
    }

}
