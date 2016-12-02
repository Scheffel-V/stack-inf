package domain;

/**
 * @author lmrodrigues 
 * 
 */
import javax.persistence.Entity;

@Entity
public class Comment extends AbstractContent {

    public Comment() {

    }

    public Comment(String text, String author, Double commentID) {

    }

}
