package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Permission;
import domain.Question;
import domain.User;
import utils.UserException;

/**
 * @author lmrodrigues
 * 
 */

public class Session implements ServiceAPI {

    private User               loggedUser;
    private UserController     userController;
    private ContentsController contentsController;

    /**
     * 
     * Create a new session to a non logged user, to log in on system, the user
     * needs to execute the login method of this class.
     * 
     */
    public Session() {
        this.loggedUser = null;
        this.userController = new UserController();
        this.contentsController = new ContentsController();

    }

    /**
     * @return the loggedUser
     */
    public User getLoggedUser() {
        return loggedUser;
    }

    /**
     * @param loggedUser
     *            the loggedUser to set
     */
    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    /**
     * @return the userController
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * @param userController
     *            the userController to set
     */
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    /**
     * @return the contentsController
     */
    public ContentsController getContentsController() {
        return contentsController;
    }

    /**
     * @param contentsController
     *            the contentsController to set
     */
    public void setContentsController(ContentsController contentsController) {
        this.contentsController = contentsController;
    }

    /**
     * @see serviceApi.ServiceAPI#newAccount(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, domain.Permission)
     * 
     * 
     */
    public void newAccount(String username, String password, String email, String name, Permission userPermission) {

    }

    /**
     * @throws UserException 
     * @see serviceApi.ServiceAPI#login(java.lang.String, java.lang.String)
     */
    public void login(String username, String password) throws UserException {
        this.loggedUser = getUserController().login(username, password);

    }

    /**
     * @see serviceApi.ServiceAPI#logout()
     */
    public void logout() {

    }

    /**
     * @see serviceApi.ServiceAPI#blockUser(java.lang.String)
     * 
     * 
     */
    public void blockUser(String username) {

    }

    /**
     * @see serviceApi.ServiceAPI#unblockUser(java.lang.String)
     * 
     * 
     */
    public void unblockUser(String username) {

    }

    /**
     * @see serviceApi.ServiceAPI#changeUserPermission(java.lang.String,
     *      domain.Permission)
     * 
     * 
     */
    public void changeUserPermission(String username, Permission newPermission) {

    }

    /**
     * @see serviceApi.ServiceAPI#changeUserPassword(java.lang.String)
     * 
     * 
     */
    public void changeUserPassword(String newPassword) {

    }

    /**
     * @see serviceApi.ServiceAPI#newQuestion(java.lang.String, domain.,
     *      java.lang.String)
     * 
     * 
     */
    public void newQuestion(String text, List<String> tags, String title) {

    }

    /**
     * @see serviceApi.ServiceAPI#newAnswer(java.lang.String, java.lang.Integer)
     * 
     * 
     */
    public void newAnswer(String text, Integer questionID) {

    }

    /**
     * @see serviceApi.ServiceAPI#newComment(java.lang.String,
     *      domain.AbstractContent)
     * 
     * 
     */
    public void newComment(String text, AbstractContent content) {

    }

    /**
     * @see serviceApi.ServiceAPI#editContent(domain.AbstractContent)
     */
    public void editContent(AbstractContent content) {

    }

    /**
     * @see serviceApi.ServiceAPI#deleteContent(domain.AbstractContent)
     */
    public void deleteContent(AbstractContent content) {

    }

    /**
     * @see serviceApi.ServiceAPI#selectQuestion(java.lang.Integer)
     */
    public Question selectQuestion(Integer questionID) {
        return null;
    }

    /**
     * @see serviceApi.ServiceAPI#searchQuestion(java.lang.String)
     * 
     * 
     */
    public List<Question> searchQuestion(String query) {
        return null;
    }

    /**
     * @see serviceApi.ServiceAPI#listAllQuestions()
     * 
     * 
     */
    public List<Question> listAllQuestions() {
        return null;
    }

    /**
     * @see serviceApi.ServiceAPI#bestAnswer(java.lang.Integer,
     *      java.lang.Integer)
     */
    public void bestAnswer(Integer questionID, Integer answerID) {

    }

    /**
     * @see serviceApi.ServiceAPI#closeQuestion(java.lang.Integer)
     */
    public void closeQuestion(Integer questionID) {

    }

    /**
     * @see serviceApi.ServiceAPI#openQuestion(java.lang.Integer)
     */
    public void openQuestion(Integer questionID) {

    }

    /**
     * @see serviceApi.ServiceAPI#upVoteAnswer(java.lang.Integer)
     */
    public void upVoteAnswer(Integer answerID) {

    }

    /**
     * @see serviceApi.ServiceAPI#downVoteAnswer(java.lang.Integer)
     */
    public void downVoteAnswer(Integer answerID) {

    }

}
