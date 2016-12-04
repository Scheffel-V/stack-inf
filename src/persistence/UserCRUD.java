package persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import domain.User;
import utils.UserException;

/***
 * @author lmrodrigues
 * 
 */

public class UserCRUD extends AbstractCRUD {
    private static final String UNIT = "UserCRUD";

    /**
     * Initialize a new persistence unit to deal with {@link domain.User}
     * persistence
     */
    public UserCRUD() {
        super(UNIT);
    }

    public void create(User newUser) {
        beginTransaction();
        getEntityManager().persist(newUser);
        commitTransaction();
    }

    public User read(String username) throws UserException {
        User user = getEntityManager().find(User.class, username);

        if (user != null) {
            return user;
        } else
            throw new UserException("user.not.exists");
    }

    public void update(User user) {
        beginTransaction();
        getEntityManager().merge(user);
        commitTransaction();
    }

    public void delete(User user) throws UserException {
        User toDelete = read(user.getUsername());

        beginTransaction();
        getEntityManager().remove(toDelete);
        commitTransaction();
    }

    public List<User> listAll() {
        TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u", User.class);

        return query.getResultList();
    }

    public Boolean isReplicatedUsername(String username) {
        try {
            read(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
