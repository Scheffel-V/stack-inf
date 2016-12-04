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

    public abstract void blockUser(String username);

    public abstract void unblockUser(String username);

    public abstract void changeUserPermission(String username, Permission newPermission);

    public abstract void changeUserPassword(String newPassword);

    public abstract void newQuestion(String text, List<String> tags, String title);

    public abstract void newAnswer(String text, Integer questionID);

    public abstract void newComment(String text, AbstractContent content);

    public abstract void editContent(AbstractContent content);

    public abstract void deleteContent(AbstractContent content);

    public abstract Question selectQuestion(Integer questionID);

    public abstract List<Question> searchQuestion(String query);

    public abstract List<Question> listAllQuestions();

    public abstract void bestAnswer(Integer questionID, Integer answerID);

    public abstract void closeQuestion(Integer questionID);

    public abstract void openQuestion(Integer questionID);

    public abstract void upVoteAnswer(Integer answerID);

    public abstract void downVoteAnswer(Integer answerID);

    public abstract User getLoggedUser();

}
