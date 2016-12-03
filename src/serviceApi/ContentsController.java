package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Question;
import domain.User;
import persistence.ContentsCRUD;
import utils.ContentsException;

/**
 * @author lmrodrigues
 *
 */

public class ContentsController {

    private ContentsCRUD contentCRUD;

    public ContentsController() {

    }

    public Question newQuestion(User logged, String text, List<String> tags, String title) {
        return null;
    }

    public void newAnswer(User logged, String text, Integer questionID) {

    }

    public void newComment(User logged, String text, AbstractContent content) {

    }

    public void editContent(User logged, AbstractContent content) {

    }

    public void deleteContent(User logged, AbstractContent content) {

    }

    public Question selectQuestion(Integer questionID) {
        return null;
    }

    public List<Question> searchQuestion(String query) {
        return null;
    }

    public List<Question> listAllQuestions() {
        return null;
    }

    public void bestAnswer(User logged, Integer questionID, Integer answerID) {

    }

    public void closeQuestion(User logged, Integer questionID) {

    }

    public void openQuestion(User logged, Integer questionID) {

    }

    public void upVoteAnswer(User logged, Integer answerID) {

    }

    public void downVoteAnswer(User logged, Integer answerID) {

    }

    private Integer generateID() {
        return null;
    }

    private void unauthorizedException() throws ContentsException {

    }

}
