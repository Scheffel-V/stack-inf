package domain;

import javax.persistence.Entity;

/**
 * @author lmrodrigues
 * 
 */

@Entity
public class Comment extends AbstractContent {

    // public Comment(){
    //
    // }
    //
    public Comment(String text, String author, Integer commentID) {
        super(commentID, author, text);
    }

}
