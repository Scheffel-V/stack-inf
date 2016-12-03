package persistence;

import javax.persistence.EntityManager;

/***
 * @author lmrodrigues
 * 
 */

public abstract class AbstractCRUD {
    private EntityManager entityManager;

    public AbstractCRUD() {

    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}
