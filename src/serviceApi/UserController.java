package serviceApi;

import domain.Permission;
import domain.User;
import persistence.UserCRUD;
import utils.UserException;

/**
 * @author lmrodrigues
 * @author pcrachevsky
 */

public class UserController {

    private UserCRUD userCRUD;

    /**
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     */
    public UserController() {
        this.userCRUD = new UserCRUD();

    }

    /**
     * 
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param username
     * @param password
     * @param email
     * @param name
     * @param userPermission
     * @throws UserException
     */
    public void newAccount(String username, String password, String email, String name, Permission userPermission)
            throws UserException {

        if (!userCRUD.isReplicatedUsername(username)) {
            User newUser = new User(username, password, email, name);
            userCRUD.create(newUser);

        } else {
            throw new UserException("replicated.username");

        }

    }

    /**
     * 
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param username
     * @param password
     * @return
     * @throws UserException
     */
    public User login(String username, String password) throws UserException {
        User requestedUser = userCRUD.read(username);

        if (requestedUser.getPassword() != password) {
            throw new UserException("incorrect.password");

        } else if (requestedUser.isBlock()) {
            throw new UserException("blocked.user");

        } else
            return requestedUser;

    }

    /**
     * 
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param logged
     * @param username
     * @throws UserException
     */
    public void blockUser(User logged, String username) throws UserException {
        Boolean isLoggedAdmin = logged.getUserPermission() == Permission.ADMIN;
        Boolean isLoggedModerator = logged.getUserPermission() == Permission.MODERATOR;

        if (isLoggedAdmin || isLoggedModerator) {

            User userToBlock = userCRUD.read(username);
            Boolean isAdminTargetUser = userToBlock.getUserPermission() == Permission.ADMIN;

            if (isAdminTargetUser) {
                throw new UserException("attempt.block.admin");

            } else {
                userToBlock.blockUser();
                userCRUD.update(userToBlock);

            }
        } else {
            throw new UserException("unauthorized.block.action");

        }
    }

    /**
     * 
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param logged
     * @param username
     * @throws UserException
     */
    public void unblockUser(User logged, String username) throws UserException {
        Boolean isLoggedAdmin = logged.getUserPermission() == Permission.ADMIN;
        Boolean isLoggedModerator = logged.getUserPermission() == Permission.MODERATOR;

        if (isLoggedAdmin || isLoggedModerator) {

            User userToUnblock = userCRUD.read(username);
            Boolean isAdminTargetUser = userToUnblock.getUserPermission() == Permission.ADMIN;

            if (isAdminTargetUser) {
                throw new UserException("attempt.unblock.admin");

            } else {
                userToUnblock.unblockUser();
                userCRUD.update(userToUnblock);

            }
        } else {
            throw new UserException("unauthorized.unblock.action");

        }
    }

    /**
     * 
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param logged
     * @param username
     * @param newPermission
     * @throws UserException
     */
    public void changeUserPermission(User logged, String username, Permission newPermission) throws UserException {
        Boolean isLoggedAdmin = logged.getUserPermission() == Permission.ADMIN;

        if (isLoggedAdmin) {
            User userToChangePower = userCRUD.read(username);
            userToChangePower.setUserPermission(newPermission);
            userCRUD.update(userToChangePower);

        } else {
            throw new UserException("not.admin.permission");

        }

    }

    /**
     * TODO WRITE DOCUMENTATION TO THIS METHOD
     * 
     * @param logged
     * @param newPassword
     * 
     */
    public void changeUserPassword(User logged, String newPassword) {
        logged.setPassword(newPassword);
        userCRUD.update(logged);

    }

}
