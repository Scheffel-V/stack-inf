package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author lmrodrigues
 * @author flmachado
 */
@Entity
@Table(name = "ANSWER")
public class Answer extends AbstractContent {

    @OneToMany(orphanRemoval = true)
    private List<Comment> comments;

    private Integer upVotes;
    private Integer downVotes;

    /**
     * creates a empty answer
     */
    public Answer() {
        super();
        this.comments = null;
        this.upVotes = null;
        this.downVotes = null;
    }

    /**
     * creates a new answer
     * 
     * @param text
     *            the text of the answer
     * @param author
     *            the author of the answer
     * @param id
     *            the ID of the answer
     */
    public Answer(Long id, User author, String text) {
        super(id, author, text);
        this.comments = new ArrayList<Comment>();
        this.upVotes = 0;
        this.downVotes = 0;
    }

    /**
     * @return a list with all comments of this answer
     */
    public List<Comment> getComments() {
        return this.comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * adds a comment to a answer
     * 
     * @param comment
     *            the comment that will be addicted to the answer's comments
     */
    public void addComment(Comment comment) {
        if (comment != null) {
            this.comments.add(comment);
        }
    }

    /**
     * deletes a comment from a answer's comments
     * 
     * @param comment
     *            the comment that will be deleted
     */
    public void delComment(Comment comment) {
        if (comment != null) {
            Iterator<Comment> iter = comments.iterator();
            while (iter.hasNext()) {
                Comment toRemove = iter.next();
                if (toRemove.getId() == comment.getId()) {
                    iter.remove();
                }
            }
        }
    }

    /**
     * @return the number of upvotes of the answer
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
     * increase by one the number of upvotes of this answer
     */
    public void addUpVotes() {
        this.upVotes++;
    }

    /**
     * @return the number of downvotes of this answer
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
     * increase by one the number of downvotes of this answer
     */
    public void addDownVotes() {
        this.downVotes++;
    }

}
