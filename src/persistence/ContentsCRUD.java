package persistence;

import java.util.List;

import domain.AbstractContent;
import domain.Answer;
import domain.Comment;
import domain.Question;

/***
 * @author lmrodrigues
 * 
 */

public class ContentsCRUD extends AbstractCRUD {

    public ContentsCRUD() {

    }

    public void create(AbstractContent newContent) {

    }

    public Question readQuestion(Integer questionID) {
        return null;
    }

    public Answer readAnswer(Integer answerID) {
        return null;
    }

    public Comment readComments(Integer commentID) {
        return null;
    }

    public void update(AbstractContent content) {

    }

    public void delete(AbstractContent content) {

    }

    public List<Question> search(String query) {
        return null;
    }

    public List<Question> listAllQuestion() {
        return null;
    }

    public Integer getMaxQuestionId() {
        return null;
    }

    public Integer getMaxCommentId() {
        return null;
    }

    public Integer getMaxAnswerId() {
        return null;
    }

}
