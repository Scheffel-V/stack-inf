package persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import domain.AbstractContent;
import domain.Answer;
import domain.Comment;
import domain.Question;
import utils.ContentsException;

/***
 * 
 * Class to create a Persistence Unit to deal with Contents Persistence
 * 
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

    /**
     * Create a new content on the Database
     * 
     * @param newContent
     *            the content to be created
     * @throws ContentsException
     */
    public void create(AbstractContent newContent) throws ContentsException {
        beginTransaction();
        getEntityManager().persist(newContent);
        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new ContentsException("invalid.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }
    }

    /**
     * 
     * Read a Question from the DataBase
     * 
     * @param questionID
     *            the id of the question to be read
     * 
     * @return the Question expected object
     * 
     * @throws ContentsException
     *             if the question not exists on the Database
     */
    public Question readQuestion(Long questionID) throws ContentsException {
        Question question = getEntityManager().find(Question.class, questionID);

        if (question != null) {
            return question;

        } else {
            throw new ContentsException("question.not.exists");

        }

    }

    /**
     * 
     * Read a Answer from the DataBase
     * 
     * @param answerID
     *            the id of the answer to be read
     * 
     * @return the Answer expected object
     * 
     * @throws ContentsException
     *             if the answer not exists on the Database
     */
    public Answer readAnswer(Long answerID) throws ContentsException {
        Answer answer = getEntityManager().find(Answer.class, answerID);

        if (answer != null) {
            return answer;

        } else {
            throw new ContentsException("answer.not.exists");

        }

    }

    /**
     * 
     * Read a Comment from the DataBase
     * 
     * @param commentID
     *            the id of the comment to be read
     * 
     * @return the Comment expected object
     * 
     * @throws ContentsException
     *             if the comment not exists on the Database
     */
    public Comment readComment(Long commentID) throws ContentsException {
        Comment comment = getEntityManager().find(Comment.class, commentID);

        if (comment != null) {
            return comment;

        } else {
            throw new ContentsException("comment.not.exists");

        }

    }

    /**
     * Update an Content on Database
     * 
     * @param content
     * @throws ContentsException
     *             if the content not exists on Database
     */
    public void update(AbstractContent content) throws ContentsException {
        beginTransaction();
        AbstractContent newContent = getEntityManager().merge(content);
        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new ContentsException("invalid.content");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }

        if (newContent == null) {
            throw new ContentsException(content.getClass().toString() + ".not.exists");
        }

    }

    /**
     * Delete a question from DataBase
     * 
     * @param question
     *            the question to be deleted
     * @throws ContentsException
     *             if the question not exists on Database
     */
    public void delete(Question question) throws ContentsException {
        beginTransaction();

        Question toDelete = readQuestion(question.getId());
        getEntityManager().remove(toDelete);

        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new ContentsException("invalid.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }

    }

    /**
     * Delete a answer from DataBase
     * 
     * @param answer
     *            the answer to be deleted
     * @throws ContentsException
     *             if the answer not exists on Database
     */
    public void delete(Answer answer) throws ContentsException {
        beginTransaction();

        Answer toDelete = readAnswer(answer.getId());
        getEntityManager().remove(toDelete);

        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new ContentsException("invalid.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }

    }

    /**
     * Delete a comment from DataBase
     * 
     * @param comment
     *            the comment to be deleted
     * @throws ContentsException
     *             if the comment not exists on Database
     */
    public void delete(Comment content) throws ContentsException {
        beginTransaction();

        Comment toDelete = readComment(content.getId());
        getEntityManager().remove(toDelete);

        try {
            commitTransaction();
        } catch (Exception e) {
            if (e.getMessage() == "duplicated.key") {
                throw new ContentsException("invalid.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }

    }

    /**
     * Search questions by the title name
     * 
     * @param string
     *            the string to match on titles
     * 
     * @return the question that title contains the query
     */
    public List<Question> search(String string) {
        TypedQuery<Question> query = getEntityManager().createQuery(
                "SELECT q FROM Question q WHERE q.title LIKE '%" + string + "%'", Question.class);

        return query.getResultList();
    }

    /**
     * @return All questions saved on the Database
     */
    public List<Question> listAllQuestions() {
        TypedQuery<Question> query =
                getEntityManager().createQuery("SELECT q FROM Question q", Question.class);

        return query.getResultList();
    }

    /**
     * @return All answers saved on the Database
     */
    public List<Answer> listAllAnswers() {
        TypedQuery<Answer> query =
                getEntityManager().createQuery("SELECT a FROM Answer a", Answer.class);

        return query.getResultList();
    }

    /**
     * @return All comments saved on the Database
     */
    public List<Comment> listAllComments() {
        TypedQuery<Comment> query =
                getEntityManager().createQuery("SELECT c FROM Comment c", Comment.class);

        return query.getResultList();
    }

    /**
     * @return the max ID of all questions on Database
     */
    public Long getMaxQuestionId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(q.id) FROM Question q", Long.class);

        if (query.getSingleResult() == null) {
            return (long) 0;
        } else {
            return query.getSingleResult();
        }
    }

    /**
     * @return the max ID of answers on Database
     */
    public Long getMaxAnswerId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(a.id) FROM Answer a", Long.class);

        if (query.getSingleResult() == null) {
            return (long) 0;
        } else {
            return query.getSingleResult();
        }
    }

    /**
     * @return the max ID of comments on Database
     */
    public Long getMaxCommentId() {
        TypedQuery<Long> query =
                getEntityManager().createQuery("SELECT MAX(c.id) FROM Comment c", Long.class);

        if (query.getSingleResult() == null) {
            return (long) 0;
        } else {
            return query.getSingleResult();
        }
    }

}
