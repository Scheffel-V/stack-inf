package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import utils.Permission;

/**
 * @author lmrodrigues
 * @author pcrachevsky
 * 
 */

@Entity
@Table(name = "StackUser")
public class User {

    @Id
    @Column(name = "USERNAME")
    private String         username;
    private String         password;
    @Column(unique = true)
    private String         email;
    private String         name;
    private Boolean        blockStatus;
    @Enumerated(EnumType.STRING)
    private Permission     userPermission;
    @OneToMany(orphanRemoval = true)
    private List<Question> userQuestions;
    @OneToMany(orphanRemoval = true)
    private List<Answer>   userAnswers;
    @OneToMany(orphanRemoval = true)
    private List<Comment>  userComments;

    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.name = null;
        this.blockStatus = null;
        this.userPermission = null;
        this.userQuestions = null;
        this.userAnswers = null;
        this.userComments = null;
    }

    /**
     * 
     * New User constructor method
     * 
     * @param username
     * @param password
     * @param email
     * @param name
     */
    public User(String username, String password, String email, String name,
            Permission userPermission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.userPermission = userPermission;
        this.blockStatus = false;
        this.userAnswers = new ArrayList<Answer>();
        this.userQuestions = new ArrayList<Question>();
        this.userComments = new ArrayList<Comment>();
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
        if (question != null) {
            Iterator<Question> iter = userQuestions.iterator();
            while (iter.hasNext()) {
                Question toRemove = iter.next();
                if (toRemove.getId() == question.getId()) {
                    iter.remove();
                }
            }
        }
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
        if (answer != null) {
            Iterator<Answer> iter = userAnswers.iterator();
            while (iter.hasNext()) {
                Answer toRemove = iter.next();
                if (toRemove.getId() == answer.getId()) {
                    iter.remove();
                }
            }
        }
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
    public void delComment(Comment comment) {
        if (comment != null) {
            Iterator<Comment> iter = userComments.iterator();
            while (iter.hasNext()) {
                Comment toRemove = iter.next();
                if (toRemove.getId() == comment.getId()) {
                    iter.remove();
                }
            }
        }
    }

}
