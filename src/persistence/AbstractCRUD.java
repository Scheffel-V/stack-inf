package persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/***
 * @author lmrodrigues
 * 
 */

public abstract class AbstractCRUD {
    private EntityManager entityManager;

    public AbstractCRUD() {
        this.entityManager = Persistence.createEntityManagerFactory("AbstractCRUD").createEntityManager();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    protected void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

}
