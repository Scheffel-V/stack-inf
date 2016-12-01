package domain;

/***
 * @author lmrodrigues
 * 
 */

import java.util.List;
import javax.persistence.*;


@Entity
public class User {

	@Id private String username;
	private String password;
	private String email;
	private String name;
	private Boolean blockStatus;
	private Permission userPermission;
	private List<Question> userQuestions;
	private List<Answer> userAnswer;
	private List<Comment> userComments;


	public User() {

	}
	
	public User(String username, String password, double id, String email, String name, Permission userPermission) {

	}

	public String getUsername() {
		return null;
	}

	public void setUsername(String username) {

	}

	public String getPassword() {
		return null;
	}

	public void setPassword(String newPassword) {

	}

	public String getEmail() {
		return null;
	}

	public void setEmail(String email) {

	}

	public String getName() {
		return null;
	}

	public void setName(String name) {

	}

	public void setPermission(Permission permission) {

	}

	public Permission getPermission() {
		return null;
	}

	public Boolean isBlock() {
		return null;
	}

	public Void blockUser() {
		return null;
	}

	public Void unblockUser() {
		return null;
	}

}
