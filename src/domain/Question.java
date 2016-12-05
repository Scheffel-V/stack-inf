package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/***
 * @author lmrodrigues
 * @author flmachado
 */

@Entity
public class Question extends AbstractContent {

    private String        title;
    private List<String>  tags;
    private List<Answer>  answers;
    private List<Comment> comments;
    private Answer        bestAnswer;
    private Status        status;

    /**
     * New Question Constructor
     * 
     * @param id
     * @param author
     * @param text
     * @param title
     * @param tags
     */
    public Question(Integer id, User author, String text, String title, List<String> tags) {
        super(id, author, text);
        this.title = title;
        this.tags = tags;
        this.answers = new ArrayList<Answer>();
        this.comments = new ArrayList<Comment>();
        this.bestAnswer = null;
        this.status = Status.OPEN;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the answers
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * @param answers
     *            the answers to set
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * @param answer
     *            the answer to be added.
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    /**
     * @param answer
     *            the answer to be deleted.
     */
    public void delAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @param comment
     *            the comment to added
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    /**
     * @param comment
     *            the comment to deleted
     */
    public void delComment(Comment comment) {
        this.comments.remove(comment);
    }

    /**
     * @return the bestAnswer
     */
    public Answer getBestAnswer() {
        return bestAnswer;
    }

    /**
     * @param bestAnswer
     *            the bestAnswer to set
     */
    public void setBestAnswer(Answer bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Change the Status to OPEN.
     */
    public void openQuestion() {
        this.status = Status.OPEN;
    }

    /**
     * Change the Status to CLOSE.
     */
    public void closeQuestion() {
        this.status = Status.CLOSED;
    }

}
