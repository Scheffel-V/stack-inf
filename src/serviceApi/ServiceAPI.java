package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Permission;
import domain.Question;
import domain.User;
import utils.ContentsException;
import utils.UserException;

/**
 * @author lmrodrigues
 * @author flmachado
 * 
 */

public interface ServiceAPI {

    public abstract void newAccount(String username, String password, String email, String name,
            Permission userPermission);

    /**
     * 
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param username
     * @param password
     * @throws UserException
     */
    public abstract void login(String username, String password) throws UserException;

    public abstract void logout();

    public abstract User getLoggedUser();

    public abstract void blockUser(String username);

    public abstract void unblockUser(String username);

    public abstract void changeUserPermission(String username, Permission newPermission);

    public abstract void changeUserPassword(String newPassword);

    /**
     * Insert a new question on database
     * 
     * @param text
     *            question's text
     * @param tags
     *            question's tags
     * @param title
     *            question's title
     */
    public abstract void newQuestion(String text, List<String> tags, String title);

    /**
     * Insert a new answer to a question on database
     * 
     * @param text
     *            answer's text
     * @param questionID
     *            id of the question that will be answered
     */
    public abstract void newAnswer(String text, Integer questionID) throws ContentsException;

    /**
     * Insert a new comment to a question or answer on database
     * 
     * @param text
     *            text of comment
     * @param content
     *            question or answer that will be commented
     */
    public abstract void newComment(String text, AbstractContent content) throws ContentsException;

    /**
     * Edits a content
     * 
     * @param content
     *            the edited content
     */
    public abstract void editContent(AbstractContent content) throws ContentsException;

    /**
     * Delete a content
     * 
     * @param content
     *            that will be deleteds
     */
    public abstract void deleteContent(AbstractContent content) throws ContentsException;

    /**
     * Return a specific question
     * 
     * @param questionID
     *            the id of the wanted question
     * @return the wanted question
     */
    public abstract Question selectQuestion(Integer questionID);

    /**
     * return a list of all question with the query on it's title
     * 
     * @param query
     *            the subject that will be search on the question's title
     * @return a list of all question with the query on it's title
     */
    public abstract List<Question> searchQuestion(String query);

    /**
     * Lists all questions on database
     * 
     * @return a list with all questions on database
     */
    public abstract List<Question> listAllQuestions();

    /**
     * set a answer as best answer of a question
     * 
     * @param questionID
     *            the id of the question that will receive a best answer
     * @param answerID
     *            the answer's id that will be set as best answer
     */
    public abstract void bestAnswer(Integer questionID, Integer answerID) throws ContentsException;

    /**
     * set a status of a question as closed
     * 
     * @param questionID
     *            the id of the question that will be closed
     */
    public abstract void closeQuestion(Integer questionID) throws ContentsException;

    /**
     * set a status of a question as open
     * 
     * @param questionID
     *            the id of the question that will be opened
     */
    public abstract void openQuestion(Integer questionID) throws ContentsException;

    /**
     * upvote a answer of a question
     * 
     * @param answerID
     *            the id of the question that will be upvoted
     */
    public abstract void upVoteAnswer(Integer answerID);

    /**
     * downvote a answer of a question
     * 
     * @param answerID
     *            the id of the question that will be downvoted
     */
    public abstract void downVoteAnswer(Integer answerID);

}
