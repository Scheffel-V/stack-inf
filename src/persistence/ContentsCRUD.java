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
    private static final String UNIT = "ContentsCRUD";

    /**
     * Initialize a new persistence unit to deal with
     * {@link domain.AbstracContents} persistence
     */
    public ContentsCRUD() {
        super(UNIT);
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

    public Comment readComments(Long commentID) throws ContentsException {
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

    public void delete(AbstractContent content) throws ContentsException {
        beginTransaction();

        if (content instanceof Question) {
            Question toDelete = readQuestion(content.getId());
            getEntityManager().remove(toDelete);

        } else if (content instanceof Answer) {
            Answer toDelete = readAnswer(content.getId());
            getEntityManager().remove(toDelete);

        } else if (content instanceof Comment) {
            Comment toDelete = readComments(content.getId());
            getEntityManager().remove(toDelete);

        } else {
            throw new ContentsException("content.not.exists");

        }

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
                getEntityManager().createQuery("SELECT MAX(id) FROM Question", Long.class);

        return query.getSingleResult();
    }

    public Long getMaxCommentId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(id) FROM Comment", Long.class);

        return query.getSingleResult();

    }

    public Long getMaxAnswerId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(id) FROM Answer", Long.class);

        return query.getSingleResult();

    }

}
