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

    public void newAccount(String username, String password, String email, String name, Permission userPermission) {
        User newUser;
        Integer maxId;
        if (userCRUD.replicateUsername(username) == false) {
            maxId = userCRUD.getMaxUserId();
            newUser = new User(username, password, email, name, maxId);
            userCRUD.create(newUser);
        } else {
            // this.unauthorizedException("Username j� existente");
        }

    }

    public void login(String username, String password) {
        User loginUser, logged;
        loginUser = new User();
        loginUser = userCRUD.read(username);

        if (loginUser.getPassword() != password) {
            // this.unauthorizedException("Senha incorreta");
        } else {
            logged = new User(loginUser);
            if (logged.isBlock() == true) {
                // this.unauthorizedException("Voc� est� bloqueado");
            }
        }

    }

    public void blockUser(User logged, String username) {
        User userToBlock;
        if ((logged.getPermission() == Permission.ADMIN) || (logged.getPermission() == Permission.MODERATOR)) {
            userToBlock = userCRUD.read(username);
            if (userToBlock.getPermission() == Permission.ADMIN) {
                // this.unauthorizedException("Usu�rio a ser bloqueado �
                // admistrador, logo, n�o voc� n�o pode bloquea-lo");
            } else {
                userToBlock.blockUser();
                userCRUD.update(userToBlock);
            }
        } else {
            // this.unauthorizedException("Voc� n�o possui o poder de bloquear
            // usu�rios");
        }
    }

    public void unblockUser(User logged, String username) {
        User userToUnblock;
        if ((logged.getPermission() == Permission.ADMIN) || (logged.getPermission() == Permission.MODERATOR)) {
            userToUnblock = userCRUD.read(username);
            userToUnblock.unblockUser();
            userCRUD.update(userToUnblock);
        } else {
            // this.unauthorizedException("Voc� n�o possui o poder de
            // desbloquear usu�rios");
        }

    }

    public void changeUserPermission(User logged, String username, Permission permission) {
        User userToChangePower;
        if (logged.getPermission() == Permission.ADMIN) {
            userToChangePower = userCRUD.read(username);
            userToChangePower.setPermission(permission);
            userCRUD.update(userToChangePower);
        } else {
            // this.unauthorizedException("Voc� n�o possui o poder de trocar
            // poder dos usu�rios");
        }

    }

    public void changeUserPassword(User logged, String newPassword) {
        logged.setPassword(newPassword);
        userCRUD.update(logged);
    }

    public void logout(User logged) {
        // destr�i logged
    }

    private void unauthorizedException(String message) throws UserException {
        // UserException erro = erro.UserException(message);

    }

}
