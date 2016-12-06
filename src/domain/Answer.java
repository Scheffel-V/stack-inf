package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 * @author lmrodrigues
 * @author flmachado
 */

@Entity
public class Answer extends AbstractContent {

    private List<Comment> comments;
    private Integer       upVotes;
    private Integer       downVotes;

    /**
     * New Answer Constructor
     * 
     * @param id
     * @param author
     * @param text
     */
    public Answer(Integer id, User author, String text) {
        super(id, author, text);
        this.comments = new ArrayList<Comment>();
        this.upVotes = 0;
        this.downVotes = 0;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @param comment
     *            the comment to added
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    /**
     * @param comment
     *            the comment to deleted
     */
    public void delComment(Comment comment) {
        this.comments.remove(comment);
    }

    /**
     * @return the upVotes
     */
    public Integer getUpVotes() {
        return upVotes;
    }

    /**
     * @param upVotes
     *            the upVotes to set
     */
    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }

    /**
     * Sum one to upVotes
     */
    public void addUpVotes() {
        this.upVotes++;
    }

    /**
     * @return the downVotes
     */
    public Integer getDownVotes() {
        return downVotes;
    }

    /**
     * @param downVotes
     *            the downVotes to set
     */
    public void setDownVotes(Integer downVotes) {
        this.downVotes = downVotes;
    }

    /**
     * Sum one to downVotes
     */
    public void addDownVotes() {
        this.downVotes++;
    }

}
