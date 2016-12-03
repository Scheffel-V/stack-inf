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

    public void create(AbstractContent newContent) {
        beginTransaction();
        getEntityManager().persist(newContent);
        commitTransaction();

    }

    public Question readQuestion(Integer questionID) throws ContentsException {
        Question question = getEntityManager().find(Question.class, questionID);

        if (question != null) {
            return question;

        } else {
            throw new ContentsException("question.not.exists");

        }

    }

    public Answer readAnswer(Integer answerID) throws ContentsException {
        Answer answer = getEntityManager().find(Answer.class, answerID);

        if (answer != null) {
            return answer;

        } else {
            throw new ContentsException("answer.not.exists");

        }

    }

    public Comment readComments(Integer commentID) throws ContentsException {
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

    public Integer getMaxQuestionId() {
        TypedQuery<Integer> query =
                getEntityManager().createQuery("SELECT MAX(id) FROM Question", Integer.class);

        return query.getSingleResult();
    }

    public Integer getMaxCommentId() {
        TypedQuery<Integer> query =
                getEntityManager().createQuery("SELECT MAX(id) FROM Comment", Integer.class);

        return query.getSingleResult();

    }

    public Integer getMaxAnswerId() {
        TypedQuery<Integer> query =
                getEntityManager().createQuery("SELECT MAX(id) FROM Answer", Integer.class);

        return query.getSingleResult();

    }

}
