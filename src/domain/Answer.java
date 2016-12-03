package domain;

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

    public Answer() {

    }

    public Answer(String text, String author, Integer id) {

    }

    public List<Comment> getComments() {
        return null;
    }

    public void addComment(Comment comment) {

    }

    public void delComment(Comment comment) {

    }

    public Integer getUpVotes() {
        return null;
    }

    public void addUpVotes() {

    }

    public Integer getDownVotes() {
        return null;
    }

    public void addDownVotes() {

    }

}
