package persistence;

/***
 * @author lmrodrigues
 * 
 */

import javax.persistence.*;

public abstract class AbstractCRUD {
	private EntityManager entityManager;

	public AbstractCRUD(){

	}


	public EntityManager getEntityManager(){
		return this.entityManager;
	}

}
