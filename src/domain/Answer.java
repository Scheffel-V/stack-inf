package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 * @author lmrodrigues
 * 
 */

@Entity
public class Answer extends AbstractContent {

    private List<Comment> comments;
    private Integer       upVotes;
    private Integer       downVotes;
    //
    // public Answer() {
    //
    // }

    public Answer(String text, String author, Integer id) {
        super(id, author, text);
        this.comments = new ArrayList<Comment>();
        this.upVotes = 0;
        this.downVotes = 0;

    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void delComment(Comment comment) {
        this.comments.remove(comment);

    }

    public Integer getUpVotes() {
        return this.upVotes;
    }

    public void addUpVotes() {
        this.upVotes++;
    }

    public Integer getDownVotes() {
        return this.downVotes;
    }

    public void addDownVotes() {
        this.downVotes++;
    }

}
