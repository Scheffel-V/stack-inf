package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Answer;
import domain.Question;
import domain.User;
import utils.ContentsException;
import utils.Permission;
import utils.UserException;

/**
 * @author lmrodrigues
 * @author flmachado
 * @author Priscila
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
     * 
     * Create a new session to a non logged user, to log in on system, the user
     * needs to execute the login method of this class. Allow choose the
     * persistence unit of the system to test the system.
     * 
     */
    public Session(String persistenceUnit) {
        this.loggedUser = null;
        this.userController = new UserController(persistenceUnit);
        this.contentsController = new ContentsController(persistenceUnit);

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
     * @throws UserException
     * @see serviceApi.ServiceAPI#newAccount(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, utils.Permission)
     */
    public void newAccount(String username, String password, String email, String name,
            Permission userPermission) throws UserException {
        this.userController.newAccount(username, password, email, name, userPermission);
    }

    /**
     * @throws UserException
     * 
     * @see serviceApi.ServiceAPI#login(java.lang.String, java.lang.String)
     */
    public void login(String username, String password) throws UserException {
        if (username != null && password != null) {
            this.loggedUser = getUserController().login(username, password);
        } else {
            throw new UserException("invalid.parameters");
        }

    }

    /**
     * @see serviceApi.ServiceAPI#logout()
     */
    public void logout() {
        this.loggedUser = null;

    }

    /**
     * @throws UserException
     * @see serviceApi.ServiceAPI#blockUser(java.lang.String)
     */
    public void blockUser(String username) throws UserException {
        User logged = this.getLoggedUser();
        if (logged != null) {
            if (username != null) {
                this.userController.blockUser(logged, username);
            } else {
                throw new UserException("invalid.parameters");
            }
        } else {
            throw new UserException("user.not.logged");
        }

    }

    /**
     * @throws UserException
     * @see serviceApi.ServiceAPI#unblockUser(java.lang.String)
     */
    public void unblockUser(String username) throws UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            if (username != null) {
                this.userController.unblockUser(logged, username);
            } else {
                throw new UserException("invalid.parameters");
            }
        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws UserException
     * @see serviceApi.ServiceAPI#changeUserPermission(java.lang.String,
     *      utils.Permission)
     */
    public void changeUserPermission(String username, Permission newPermission)
            throws UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            if (username != null && newPermission != null) {
                this.userController.changeUserPermission(this.getLoggedUser(), username,
                        newPermission);
            } else {
                throw new UserException("invalid.parameters");
            }
        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws UserException
     * @see serviceApi.ServiceAPI#changeUserPassword(java.lang.String)
     */
    public void changeUserPassword(String newPassword) throws UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            if (newPassword != null) {
                this.userController.changeUserPassword(logged, newPassword);
            } else {
                throw new UserException("invalid.parameters");
            }
        } else {
            throw new UserException("user.not.logged");
        }

    }

    /**
     * @throws UserException
     * @throws ContentsException
     * @see serviceApi.ServiceAPI#newQuestion(java.lang.String, domain.,
     *      java.lang.String)
     */
    public void newQuestion(String text, List<String> tags, String title)
            throws UserException, ContentsException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.newQuestion(logged, text, tags, title);
        } else {
            throw new UserException("user.not.logged");
        }

    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#newAnswer(java.lang.String, java.lang.Integer)
     * 
     * 
     */
    public void newAnswer(String text, Long questionID) throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.newAnswer(logged, text, questionID);
        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#newComment(java.lang.String,
     *      domain.AbstractContent)
     * 
     * 
     */
    public void newComment(String text, AbstractContent content)
            throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {

            if (content instanceof Question)
                this.contentsController.newComment(logged, text, (Question) content);
            else if (content instanceof Answer) {
                this.contentsController.newComment(logged, text, (Answer) content);
            } else {
                throw new ContentsException("content.not.able.to.comment");
            }

        } else {
            throw new UserException("user.not.logged");
        }

    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#editContent(domain.AbstractContent)
     */
    public void editContent(AbstractContent content) throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.editContent(logged, content);
        } else {
            throw new UserException("user.not.logged");
        }

    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#deleteContent(domain.AbstractContent)
     */
    public void deleteContent(AbstractContent content) throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.deleteContent(logged, content);
        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws ContentsException
     * @see serviceApi.ServiceAPI#selectQuestion(java.lang.Integer)
     */
    public Question selectQuestion(Long questionID) throws ContentsException {
        return this.contentsController.selectQuestion(questionID);
    }

    /**
     * @see serviceApi.ServiceAPI#searchQuestion(java.lang.String)
     * 
     * 
     */
    public List<Question> searchQuestion(String query) {
        return this.contentsController.searchQuestion(query);
    }

    /**
     * @see serviceApi.ServiceAPI#listAllQuestions()
     * 
     * 
     */
    public List<Question> listAllQuestions() {
        return this.contentsController.listAllQuestions();
    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#bestAnswer(java.lang.Integer,
     *      java.lang.Integer)
     */
    public void bestAnswer(Long questionID, Long answerID) throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.bestAnswer(logged, questionID, answerID);
        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#closeQuestion(java.lang.Integer)
     */
    public void closeQuestion(Long questionID) throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.closeQuestion(logged, questionID);
        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#openQuestion(java.lang.Integer)
     */
    public void openQuestion(Long questionID) throws ContentsException, UserException {
        User logged = this.getLoggedUser();

        if (logged != null) {
            this.contentsController.openQuestion(logged, questionID);

        } else {
            throw new UserException("user.not.logged");
        }
    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#upVoteAnswer(java.lang.Integer)
     */
    public void upVoteAnswer(Long answerID) throws ContentsException, UserException {
        User user = this.getLoggedUser();

        if (user != null) {
            this.contentsController.upVoteAnswer(answerID);
        } else {
            throw new UserException("user.not.logged");
        }

    }

    /**
     * @throws ContentsException
     * @throws UserException
     * @see serviceApi.ServiceAPI#downVoteAnswer(java.lang.Integer)
     */
    public void downVoteAnswer(Long answerID) throws ContentsException, UserException {
        User user = this.getLoggedUser();

        if (user != null) {
            this.contentsController.downVoteAnswer(answerID);
        } else {
            throw new UserException("user.not.logged");
        }

    }

}
