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
     * creates a empty comment
     */
    public Comment() {
        super();
    }

    /**
     * creates a new comment
     * 
     * @param text
     *            the text of the comment
     * @param author
     *            the author of the comment
     * @param commentID
     *            the id of the comment
     */
    public Comment(Integer commentID, User author, String text) {
        super(commentID, author, text);
    }

}
