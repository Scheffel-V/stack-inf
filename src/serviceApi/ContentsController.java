package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Question;
import domain.User;
import persistence.ContentsCRUD;
import utils.ContentsException;

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

    public Void editContent(User logged, AbstractContent content) {
        return null;
    }

    public Void deleteContent(User logged, AbstractContent content) {
        return null;
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

    public Void bestAnswer(User logged, Integer questionID, Integer answerID) {
        return null;
    }

    public Void closeQuestion(User logged, Integer questionID) {
        return null;
    }

    public Void openQuestion(User logged, Integer questionID) {
        return null;
    }

    public Void upVoteAnswer(User logged, Integer answerID) {
        return null;
    }

    public Void downVoteAnswer(User logged, Integer answerID) {
        return null;
    }

    private Integer generateID() {
        return null;
    }

    private ContentsException unauthorizedException() {
        return null;
    }

}
