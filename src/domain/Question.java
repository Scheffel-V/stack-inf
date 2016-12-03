package domain;

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
    private Status        status;

    public Question() {

    }

    public Question(String text, List<String> tags, String title, String author, Integer id) {

    }

    public String getTitle() {
        return null;
    }

    public void setTitle(String newTitle) {

    }

    public List<String> getTags() {
        return null;
    }

    public void setTags(List<String> tags) {

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

    public void openQuestion() {

    }

    public void closeQuestion() {

    }

}
