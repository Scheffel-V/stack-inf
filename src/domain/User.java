package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lmrodrigues
 * 
 */

/**
 * @author lmrodrigues
 * 
 */

@Entity
public class User {

    @Id
    private String         username;
    private String         password;
    private String         email;
    private String         name;
    private Boolean        blockStatus;
    private Permission     userPermission;
    private List<Question> userQuestions;
    private List<Answer>   userAnswers;
    private List<Comment>  userComments;

    /**
     * 
     * New User constructor method
     * 
     * @param username
     * @param password
     * @param email
     * @param name
     */
    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.userPermission = Permission.COMMON;
        this.blockStatus = false;
        this.userAnswers = null;
        this.userQuestions = null;
        this.userComments = null;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the blockStatus
     */
    public Boolean isBlock() {
        return this.blockStatus;
    }

    /**
     * set the blockStatus to true;
     */
    public void blockUser() {
        this.blockStatus = true;
    }

    /**
     * set the blockStatus to false;
     */
    public void unblockUser() {
        this.blockStatus = false;
    }

    /**
     * @return the blockStatus
     */
    public Boolean getBlockStatus() {
        return blockStatus;
    }

    /**
     * @param blockStatus
     *            the blockStatus to set
     */
    public void setBlockStatus(Boolean blockStatus) {
        this.blockStatus = blockStatus;
    }

    /**
     * @return the userPermission
     */
    public Permission getUserPermission() {
        return userPermission;
    }

    /**
     * @param userPermission
     *            the userPermission to set
     */
    public void setUserPermission(Permission userPermission) {
        this.userPermission = userPermission;
    }

    /**
     * @return the userQuestions
     */
    public List<Question> getUserQuestions() {
        return userQuestions;
    }

    /**
     * @param userQuestions
     *            the userQuestions to set
     */
    public void setUserQuestions(List<Question> userQuestions) {
        this.userQuestions = userQuestions;
    }

    /**
     * @param question
     *            the question to be added.
     */
    public void addQuestion(Question question) {
        this.userQuestions.add(question);
    }

    /**
     * @param question
     *            the question to be deleted.
     */
    public void delQuestion(Question question) {
        this.userQuestions.remove(question);
    }

    /**
     * @return the userAnswers
     */
    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    /**
     * @param userAnswers
     *            the userAnswers to set
     */
    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * @param answer
     *            the answer to be added.
     */
    public void addAnswer(Answer answer) {
        this.userAnswers.add(answer);
    }

    /**
     * @param answer
     *            the answer to be deleted.
     */
    public void delAnswer(Answer answer) {
        this.userAnswers.remove(answer);
    }

    /**
     * @return the userComments
     */
    public List<Comment> getUserComments() {
        return userComments;
    }

    /**
     * @param userComments
     *            the userComments to set
     */
    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    /**
     * @param comment
     *            the comment to be added.
     */
    public void addComment(Comment comment) {
        this.userComments.add(comment);
    }

    /**
     * @param comment
     *            the comment to be deleted.
     */
    public void delComments(Comment comment) {
        this.userComments.remove(comment);
    }

}
