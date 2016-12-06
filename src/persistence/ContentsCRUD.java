package persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import domain.AbstractContent;
import domain.Answer;
import domain.Comment;
import domain.Question;
import utils.ContentsException;

/***
 * @author lmrodrigues
 * 
 */

public class ContentsCRUD extends AbstractCRUD {

    /**
     * Initialize a new standard persistence unit to deal with
     * {@link domain.AbstracContents} persistence
     */
    public ContentsCRUD() {
        super();
    }

    /**
     * Initialize a new specific persistence unit to deal with
     * {@link domain.AbstracContents} persistence
     */
    public ContentsCRUD(String unit) {
        super(unit);
    }

    public void create(AbstractContent newContent) {
        beginTransaction();
        getEntityManager().persist(newContent);
        commitTransaction();

    }

    public Question readQuestion(Long questionID) throws ContentsException {
        Question question = getEntityManager().find(Question.class, questionID);

        if (question != null) {
            return question;

        } else {
            throw new ContentsException("question.not.exists");

        }

    }

    public Answer readAnswer(Long answerID) throws ContentsException {
        Answer answer = getEntityManager().find(Answer.class, answerID);

        if (answer != null) {
            return answer;

        } else {
            throw new ContentsException("answer.not.exists");

        }

    }

    public Comment readComment(Long commentID) throws ContentsException {
        Comment comment = getEntityManager().find(Comment.class, commentID);

        if (comment != null) {
            return comment;

        } else {
            throw new ContentsException("comment.not.exists");

        }

    }

    public void update(AbstractContent content) {
        beginTransaction();
        getEntityManager().merge(content);
        commitTransaction();

    }

    public void delete(Question content) throws ContentsException {
        beginTransaction();

        Question toDelete = readQuestion(content.getId());
        getEntityManager().remove(toDelete);

        commitTransaction();

    }

    public void delete(Answer content) throws ContentsException {
        beginTransaction();

        Answer toDelete = readAnswer(content.getId());
        getEntityManager().remove(toDelete);

        commitTransaction();

    }

    public void delete(Comment content) throws ContentsException {
        beginTransaction();

        Comment toDelete = readComment(content.getId());
        getEntityManager().remove(toDelete);

        commitTransaction();

    }

    public List<Question> search(String query) {
        return null;
    }

    public List<Question> listAllQuestion() {
        TypedQuery<Question> query =
                getEntityManager().createQuery("SELECT q FROM Question q", Question.class);

        return query.getResultList();
    }

    public Long getMaxQuestionId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(q.id) FROM Question q", Long.class);

        return query.getSingleResult();
    }

    public Long getMaxCommentId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(c.id) FROM Comment c", Long.class);

        return query.getSingleResult();

    }

    public Long getMaxAnswerId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(a.id) FROM Answer a", Long.class);

        return query.getSingleResult();

    }

}
