package persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import domain.User;
import utils.UserException;

/***
 * 
 * Class to create a Persistence Unit to deal with Users Persistence
 * 
 * @author lmrodrigues
 * 
 */
/**
 * @author lmrodrigues
 *
 */
public class UserCRUD extends AbstractCRUD {

    /**
     * Initialize a new standard persistence unit to deal with
     * {@link domain.User} persistence
     */
    public UserCRUD() {
        super();
    }

    /**
     * Initialize a new specific persistence unit to deal with
     * {@link domain.User} persistence
     */
    public UserCRUD(String unit) {
        super(unit);
    }

    /**
     * Create a new user on the Database
     * 
     * @param newUser
     *            the user to be created
     * @throws UserException
     */
    public void create(User newUser) throws UserException {
        beginTransaction();
        getEntityManager().persist(newUser);

        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new UserException("invalid.username.or.email");
            } else {
                throw new UserException("unexpected.error", e);
            }
        }
    }

    /**
     * Read a User from the Database
     * 
     * @param username
     *            the username of user to be readed
     * @return the expected User object
     * @throws UserException
     *             if user not exists
     */
    public User read(String username) throws UserException {
        User user = getEntityManager().find(User.class, username);

        if (user != null) {
            return user;
        } else
            throw new UserException("user.not.exists");
    }

    /**
     * Update an User on Database
     * 
     * @param user
     *            the user to be Updated
     * @throws UserException
     *             if the user not exists on the Database
     */
    public void update(User user) throws UserException {
        beginTransaction();
        User returnUser = getEntityManager().merge(user);
        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new UserException("invalid.username.or.email");
            } else {
                throw new UserException("unexpected.error", e);
            }
        }
        if (returnUser == null) {
            throw new UserException("user.not.exists");
        }
    }

    /**
     * Delete an User from Database
     * 
     * @param user
     *            the user to be deleted
     * @throws UserException
     *             if user not exists on database
     */
    public void delete(User user) throws UserException {
        User toDelete = read(user.getUsername());

        beginTransaction();
        getEntityManager().remove(toDelete);
        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new UserException("invalid.username.or.email");
            } else {
                throw new UserException("unexpected.error", e);
            }
        }
    }

    /**
     * @return All Users from database
     */
    public List<User> listAll() {
        TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u", User.class);

        return query.getResultList();
    }

    /**
     * Verify if an username is already on database
     * 
     * @param username
     *            to be verified
     * @return A Boolean Value: True -> username already assigned to an user
     *         False -> username not assigned yet
     */
    public Boolean isReplicatedUsername(String username) {
        try {
            read(username);
            return true;
        } catch (UserException _) {
            return false;
        }
    }

}
