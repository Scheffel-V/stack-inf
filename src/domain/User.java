package domain;

import java.util.List;

import javax.persistence.Entity;

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

    private String         username;
    private String         password;
    private String         email;
    private String         name;
    private Boolean        blockStatus;
    private Permission     userPermission;
    private List<Question> userQuestions;
    private List<Answer>   userAnswers;
    private List<Comment>  userComments;
    private Integer        id;

    public User() {

    }

    public User(User copyUser) {
        this.username = copyUser.getUsername();
        this.password = copyUser.getPassword();
        this.id = copyUser.getId();
        this.email = copyUser.getEmail();
        this.name = copyUser.getName();
        this.userPermission = copyUser.getPermission();
        this.blockStatus = copyUser.isBlock();
        this.userAnswers = copyUser.getUserAnswer();
        this.userQuestions = copyUser.getUserQuestion();
        this.userComments = copyUser.getUserComment();

    }

    public User(String username, String password, String email, String name, Integer id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.id = id;
        this.userPermission = Permission.COMMON;
        this.blockStatus = false;
        this.userAnswers = null;
        this.userQuestions = null;
        this.userComments = null;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermission(Permission permission) {
        this.userPermission = permission;
    }

    public Permission getPermission() {
        return this.userPermission;
    }

    public Boolean isBlock() {
        return this.blockStatus;
    }

    public void blockUser() {
        this.blockStatus = true;
    }

    public void unblockUser() {
        this.blockStatus = false;
    }

    public List<Question> getUserQuestion() {
        return this.userQuestions;
    }

    public void setUserQuestion(List<Question> questions) {
        this.userQuestions = questions;
    }

    public List<Answer> getUserAnswer() {
        return this.userAnswers;
    }

    public void setUserAnswer(List<Answer> answers) {
        this.userAnswers = answers;
    }

    public List<Comment> getUserComment() {
        return this.userComments;
    }

    public void setUserComment(List<Comment> comments) {
        this.userComments = comments;
    }
}
