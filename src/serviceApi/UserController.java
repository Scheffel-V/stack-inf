package serviceApi;

import domain.Permission;
import domain.User;
import persistence.UserCRUD;
import utils.UserException;

public class UserController {

    private UserCRUD userCRUD;

    public UserController() {

    }

    public User newAccount(String username, String password, String email, String name, Permission userPermission) {
        return null;
    }

    public Void login(String username, String password) {
        return null;
    }

    public Void blockUser(User logged, String username) {
        return null;
    }

    public Void unblockUser(User logged, String username) {
        return null;
    }

    public void changeUserPermission(User logged, String username) {

    }

    public Void changeUserPassword(User user, String newPassword) {
        return null;
    }

    private UserException unauthorizedException() {
        return null;
    }

}
