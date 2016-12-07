package persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/***
 * @author lmrodrigues
 * 
 *         Class to create Entity Managers to All Persistence Units of the
 *         system.
 * 
 */
public abstract class AbstractCRUD {
    private EntityManager entityManager;

    /**
     * Creates an Standard Persistence Unit
     */
    public AbstractCRUD() {
        this.entityManager =
                Persistence.createEntityManagerFactory("AbstractCRUD").createEntityManager();
    }

    /**
     * Creates an Specific Persistence Unit
     * 
     * @param unit
     *            the name of the Persistence Unit
     */
    public AbstractCRUD(String unit) {
        this.entityManager = Persistence.createEntityManagerFactory(unit).createEntityManager();
    }

    /**
     * @return the Entity Manager of one Persistence Unit
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * Send a message to the Entity Manager to open a Transaction with DataBase
     */
    protected void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    /**
     * Send a message to the Entity Manager to close a Transaction with DataBase
     */
    protected void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

}
