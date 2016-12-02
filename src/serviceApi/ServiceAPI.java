package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Permission;
import domain.Question;
import domain.User;

public interface ServiceAPI {

    public abstract void newAccount(String username, String password, String email, String name,
            Permission userPermission);

    public abstract Void login(String username, String password);

    public abstract void logout();

    public abstract void blockUser(String username);

    public abstract void unblockUser(String username);

    public abstract void changeUserPermission(String username, Permission newPermission);

    public abstract void changeUserPassword(String newPassword);

    public abstract void newQuestion(String text, List<String> tags, String title);

    public abstract void newAnswer(String text, Integer questionID);

    public abstract void newComment(String text, AbstractContent content);

    public abstract Void editContent(AbstractContent content);

    public abstract Void deleteContent(AbstractContent content);

    public abstract Question selectQuestion(Integer questionID);

    public abstract List<Question> searchQuestion(String query);

    public abstract List<Question> listAllQuestions();

    public abstract void bestAnswer(Integer questionID, Integer answerID);

    public abstract Void closeQuestion(Integer questionID);

    public abstract Void openQuestion(Integer questionID);

    public abstract Void upVoteAnswer(Integer answerID);

    public abstract Void downVoteAnswer(Integer answerID);

    public abstract User getLoggedUser();

}
