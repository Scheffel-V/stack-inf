package domain;

import javax.persistence.Entity;

/**
 * @author lmrodrigues
 * @author flmachado
 * 
 */

@Entity
public class Comment extends AbstractContent {

    /**
     * New Comment Construct
     * 
     * @param id
     * @param author
     * @param text
     */
    public Comment(Integer id, User author, String text) {
        super(id, author, text);
    }

}