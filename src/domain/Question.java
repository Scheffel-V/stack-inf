package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***
 * @author lmrodrigues
 * @author flmachado
 */

@Entity
@Table(name = "QUESTION")
public class Question extends AbstractContent {

    private String       title;
    private List<String> tags;

    @OneToMany(orphanRemoval = true)
    private List<Answer> answers;

    @OneToMany(orphanRemoval = true)
    private List<Comment> comments;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "BEST_ANSWER")
    private Answer bestAnswer;

    private Status status;

    /**
     * creates a empty question
     */
    public Question() {
        super();
        this.title = null;
        this.tags = null;
        this.answers = null;
        this.comments = null;
        this.bestAnswer = null;
        this.status = null;
    }

    /**
     * creates a new question
     * 
     * @param text
     *            the question text
     * @param tags
     *            the tags of the question
     * @param title
     *            the title of the question
     * @param author
     *            the author of the question
     * @param id
     *            the id of the question
     */
    public Question(Long id, User author, String text, String title, List<String> tags) {
        super(id, author, text);
        this.title = title;
        this.tags = tags;
        this.answers = new ArrayList<Answer>();
        this.comments = new ArrayList<Comment>();
        this.bestAnswer = null;
        this.status = Status.OPEN;
    }

    /**
     * @return the question title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set a new title of the question
     * 
     * @param newTitle
     *            the new title of the question
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * @return return a list with all tags of the question
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * set a new tag list for the question
     * 
     * @param tags
     *            the new tag list
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * return the answers of the question
     * 
     * @return a list with all answer of the question
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
     * adds a new answer to the question
     * 
     * @param answer
     *            the answer that will be add to the question
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    /**
     * deletes a answer of a question
     * 
     * @param answer
     *            the answer that will be deleted
     */
    public void delAnswer(Answer answer) {
        if (answer != null) {
            Iterator<Answer> iter = answers.iterator();
            while (iter.hasNext()) {
                Answer toRemove = iter.next();
                if (toRemove.getId() == answer.getId()) {
                    iter.remove();
                }
            }
        }
    }

    /**
     * return all the comments of the question
     * 
     * @return a list with all the comments of the question
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
     * adds a comment to the question
     * 
     * @param comment
     *            the comment that will be add to the question
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    /**
     * deletes a comment of a question
     * 
     * @param comment
     *            the comment that will be deleted
     */
    public void delComment(Comment comment) {
        if (comment != null) {
            Iterator<Comment> iter = comments.iterator();
            while (iter.hasNext()) {
                Comment toRemove = iter.next();
                if (toRemove.getId() == comment.getId()) {
                    iter.remove();
                }
            }
        }
    }

    /**
     * get the best answer of the question
     * 
     * @return the best answer of the question
     */
    public Answer getBestAnswer() {
        return bestAnswer;
    }

    /**
     * set a best answer to the question
     * 
     * @param bestAnswer
     *            the answer that will turn into the best answer
     */
    public void setBestAnswer(Answer bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    /**
     * the status of question
     * 
     * @return OPEN, if the question is open for answer, or CLOSED if it's
     *         closed for answer
     */
    public Status getStatus() {
        return status;
    }

    /**
     * set a status to the question
     * 
     * @param status
     *            the new status of the question
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * set the question's status for OPEN, so it's possible to answer this
     * question
     */
    public void openQuestion() {
        this.status = Status.OPEN;
    }

    /**
     * set the question's status for CLOSED, so it's not possible to answer this
     * question
     */
    public void closeQuestion() {
        this.status = Status.CLOSED;
    }

}
