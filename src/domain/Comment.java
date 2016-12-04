package domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lmrodrigues
 * @author flmachado
 * 
 */

@Entity
@Table(name = "COMMENT")
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
    public Comment(Long id, User author, String text) {
        super(commentID, author, text);
    }

}
