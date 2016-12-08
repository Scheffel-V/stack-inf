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
 * @author Priscila
 * 
 */

public interface ServiceAPI {

    /**
     * Creates new account
     * 
     * @param username
     * @param password
     * @param email
     * @param name
     * @param userPermission
     * @throws UserException
     */
    public abstract void newAccount(String username, String password, String email, String name,
            Permission userPermission) throws UserException;

    /**
     * Logins in to account
     * 
     * @param username
     *            the user who will login
     * @param password
     * @throws UserException
     */
    public abstract void login(String username, String password) throws UserException;

    /**
     * Logout account
     */
    public abstract void logout();

    /**
     * @return user who logged
     */
    public abstract User getLoggedUser();

    /**
     * Blocks another user
     * 
     * @param username
     *            the user who will be blocked
     * @throws UserException
     */
    public abstract void blockUser(String username) throws UserException;

    /**
     * Unblocks another user
     * 
     * @param username
     *            the user who will be unblocked
     * @throws UserException
     */
    public abstract void unblockUser(String username) throws UserException;

    /**
     * Changes another user's permission
     * 
     * @param username
     *            the user who will change its permission
     * @param newPermission
     * @throws UserException
     */
    public abstract void changeUserPermission(String username, Permission newPermission)
            throws UserException;

    /**
     * Changes user's password
     * 
     * @param newPassword
     * @throws UserException
     */
    public abstract void changeUserPassword(String newPassword) throws UserException;

    /**
     * Insert a new question on database
     * 
     * @param text
     *            question's text
     * @param tags
     *            question's tags
     * @param title
     *            question's title
     * @throws UserException
     *             if logged user not exists on database
     * @throws ContentsException
     */
    public abstract void newQuestion(String text, List<String> tags, String title)
            throws UserException, ContentsException;

    /**
     * Insert a new answer to a question on database
     * 
     * @param text
     *            answer's text
     * @param questionID
     *            id of the question that will be answered
     * @throws ContentsException,
     *             in case the question is closed, throws ContentsException
     * @throws UserException
     *             if logged user not exists on database
     */
    public abstract void newAnswer(String text, Long questionID)
            throws ContentsException, UserException;

    /**
     * Insert a new comment to a question or answer on database
     * 
     * @param text
     *            text of comment
     * @param content
     *            question or answer that will be commented
     * @throws ContentsException
     *             in case the user try to comment something that isn't a
     *             question or a answer, throws ContentsException
     * @throws UserException
     *             if logged user not exists on database
     */
    public abstract void newComment(String text, AbstractContent content)
            throws ContentsException, UserException;

    /**
     * Edits a content
     * 
     * @param content
     *            the edited content
     * @throws ContentsException
     *             in case the user who tries to edit isn't admin or the author
     *             of the content, throws ContentsException
     */
    public abstract void editContent(AbstractContent content) throws ContentsException;

    /**
     * Delete a content
     * 
     * @param content
     *            that will be deleted
     * @throws ContentsException
     *             in case the user who tries to delete isn't admin or the
     *             author of the content, throws ContentsException
     */
    public abstract void deleteContent(AbstractContent content)
            throws ContentsException, UserException;

    /**
     * Return a specific question
     * 
     * @param questionID
     *            the id of the wanted question
     * @return the wanted question
     * @throws ContentsException
     *             in case the question not exists
     */
    public abstract Question selectQuestion(Long questionID) throws ContentsException;

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
     * @throws ContentsException
     *             in case the user who wants to select the best answer isn't
     *             the author of the question, throws ContentsException
     */
    public abstract void bestAnswer(Long questionID, Long answerID) throws ContentsException;

    /**
     * set a status of a question as closed
     * 
     * @param questionID
     *            the id of the question that will be closed
     * @throws ContentsException
     *             in case the user who tries to close the question isn't at
     *             least a admin, throws ContentsException
     */
    public abstract void closeQuestion(Long questionID) throws ContentsException;

    /**
     * set a status of a question as open
     * 
     * @param questionID
     *            the id of the question that will be opened
     * @throws ContentsException
     *             in case the user who tries to open the question isn't at
     *             least a admin, throws ContentsException
     */
    public abstract void openQuestion(Long questionID) throws ContentsException;

    /**
     * upvote a answer of a question
     * 
     * @param answerID
     *            the id of the answer that will be upvoted
     * @throws ContentsException
     *             in case the answer not exist
     */
    public abstract void upVoteAnswer(Long answerID) throws ContentsException;

    /**
     * downvote a answer of a question
     * 
     * @param answerID
     *            the id of the answer that will be downvoted
     * @throws ContentsException
     *             in case the answer not exists
     */
    public abstract void downVoteAnswer(Long answerID) throws ContentsException;

}
