package serviceApi;

import domain.Permission;
import domain.User;
import persistence.UserCRUD;
import utils.UserException;

/**
 * @author lmrodrigues
 * 
 */

public class UserController {

    private UserCRUD userCRUD;

    public UserController() {

    }

    public User newAccount(String username, String password, String email, String name, Permission userPermission) {
        return null;
    }

    public void login(String username, String password) {

    }

    public void blockUser(User logged, String username) {

    }

    public void unblockUser(User logged, String username) {

    }

    public void changeUserPermission(User logged, String username) {

    }

    public void changeUserPassword(User user, String newPassword) {

    }

    private void unauthorizedException() throws UserException {

    }

}
