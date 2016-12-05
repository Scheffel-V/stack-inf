package serviceApi;

import java.util.List;

import domain.AbstractContent;
import domain.Answer;
import domain.Comment;
import domain.Permission;
import domain.Question;
import domain.Status;
import domain.User;
import persistence.ContentsCRUD;
import utils.ContentsException;

/**
 * @author flmachado
 *
 */

public class ContentsController {

    private ContentsCRUD contentCRUD;

    /**
     * Create a new database for the API.
     */
    public ContentsController() {
        this.contentCRUD = new ContentsCRUD();
    }

    /**
     * Creates a new Question
     * 
     * @param logged
     *            Author of question
     * @param text
     *            Text of question
     * @param tags
     *            List of question's tags
     * @param title
     *            Question's title
     * @return The created Question
     */
    public Question newQuestion(User logged, String text, List<String> tags, String title) {
        String authorName = logged.getUsername();

        Integer id = this.contentCRUD.getMaxQuestionId() + 1;

        Question newQuestion = new Question(text, tags, title, authorName, id);

        this.contentCRUD.create(newQuestion);

        return newQuestion;
    }

    /**
     * Creates a new answer for the a already existent question, in case this
     * question is closed, throws a exception
     * 
     * @param logged
     *            Answer's Author
     * @param text
     *            Answer's text
     * @param questionID
     *            Question'id of the answer
     * @throws ContentsException
     *             in case the Question is closed
     */

    public void newAnswer(User logged, String text, Integer questionID) throws ContentsException {
        String username = logged.getUsername();
        Integer answerId = this.contentCRUD.getMaxAnswerId() + 1;
        Question question = this.contentCRUD.readQuestion(questionID);

        if (question.getStatus() == Status.OPEN) {
            Answer newAnswer = new Answer(text, username, answerId);

            question.addAnswer(newAnswer);

            this.contentCRUD.update(question);
            this.contentCRUD.create(newAnswer);

        } else {
            unauthorizedException("closedQuestion");
        }
    }

    /**
     * Adds a comment to a question.
     * 
     * @param logged
     *            Author of comment
     * @param text
     *            comment's text
     * @param content
     *            Question that will receive this comment
     * @throws ContentsException
     */
    public void newComment(User logged, String text, Question question) throws ContentsException {
        String author = logged.getUsername();
        Integer commentId = this.contentCRUD.getMaxCommentId() + 1;
        Comment newComment = new Comment(text, author, commentId);

        question.addComment(newComment);

        this.contentCRUD.update(question);
        this.contentCRUD.create(newComment);

    }

    /**
     * Adds a comment to a answer.
     * 
     * @param logged
     *            Author of comment
     * @param text
     *            comment's text
     * @param answer
     *            Answer that will receive this comment
     * @throws ContentsException
     */
    public void newComment(User logged, String text, Answer answer) throws ContentsException {
        String author = logged.getUsername();
        Integer commentId = this.contentCRUD.getMaxCommentId() + 1;
        Comment newComment = new Comment(text, author, commentId);

        answer.addComment(newComment);

        this.contentCRUD.update(answer);
        this.contentCRUD.create(newComment);

    }

    /**
     * Edit a existent content
     * 
     * @param logged
     *            who edits the content
     * @param content
     *            the edited content
     * @throws ContentsException
     *             in case who edits the content doesn't have permission to edit
     *             it it
     */
    public void editContent(User logged, AbstractContent content) throws ContentsException {

        if (this.userAbleToEditContent(logged, content)) {
            this.contentCRUD.update(content);
        } else {
            this.unauthorizedException("unautorizedUser");
        }

    }

    /**
     * Delete a content of dataBase
     * 
     * @param logged
     *            who deletes the content
     * @param content
     *            the content that will be deleted
     * @throws ContentsException
     *             in case who wants to delete the content doesn't have
     *             permission to delete it
     */
    public void deleteContent(User logged, AbstractContent content) throws ContentsException {
        if (this.userAbleToEditContent(logged, content)) {
            this.contentCRUD.delete(content);
        } else {
            this.unauthorizedException("unautorizedUser");
        }
    }

    /**
     * return a specific question from the database
     * 
     * @param questionID
     *            wanted question's id
     * @return the wanted question
     */
    public Question selectQuestion(Integer questionID) {
        return this.contentCRUD.readQuestion(questionID);
    }

    /**
     * return a list of all question with the query on it's title
     * 
     * @param query
     *            the subject that will be search on the question's title
     * @return a list of all question with the query on it's title
     */
    public List<Question> searchQuestion(String query) {
        return this.contentCRUD.search(query);
    }

    /**
     * Return all question on the current database
     * 
     * @return all question on the database
     */
    public List<Question> listAllQuestions() {
        return this.contentCRUD.listAllQuestion();
    }

    /**
     * Set the best answer of a question, only the author of the question can do
     * this
     * 
     * @param logged
     *            the user that want's to set the best answer
     * @param questionID
     *            the id of the question that will receive the best answer
     * @param answerID
     *            the id of the answer that will be set as the best answer
     * @throws ContentsException
     *             in case the user that try to set a best answer is not the
     *             author of the question
     */
    public void bestAnswer(User logged, Integer questionID, Integer answerID) throws ContentsException {
        String userName = logged.getUsername();
        Question question = this.selectQuestion(questionID);
        String authorName = question.getAuthor();
        boolean ableToChooseBestAnswer = (userName == authorName);
        if (ableToChooseBestAnswer) {
            Answer bestAnswer = this.contentCRUD.readAnswer(answerID);
            question.setBestAnswer(bestAnswer);
            this.contentCRUD.update(question);
        } else {
            this.unauthorizedException("notAuthor");
        }
    }

    /**
     * closed a question for answers
     * 
     * @param logged
     *            the user that wants to close a question
     * @param questionID
     *            the id of the question that will be closed
     * @throws ContentsException
     *             in case the user that try changes the question's status isn't
     *             at least a ADMIN
     */
    public void closeQuestion(User logged, Integer questionID) throws ContentsException {
        if (this.areUserAdmin(logged)) {
            Question closedQuestion = this.selectQuestion(questionID);
            closedQuestion.closeQuestion();
            this.contentCRUD.update(closedQuestion);
        } else {
            this.unauthorizedException("unautorizedUser");
        }
    }

    /**
     * open a question for answers
     * 
     * @param logged
     *            user who wants to open a question
     * @param questionID
     *            id of the question that will be opened
     * @throws ContentsException
     *             in case the user who tries to change the question's status
     *             isn't at least a ADMIN
     */
    public void openQuestion(User logged, Integer questionID) throws ContentsException {
        if (this.areUserAdmin(logged)) {
            Question opennedQuestion = this.selectQuestion(questionID);
            opennedQuestion.openQuestion();
            this.contentCRUD.update(opennedQuestion);
        } else {
            this.unauthorizedException("unautorizedUser");
        }
    }

    /**
     * upvote a answer of a question
     * 
     * @param logged
     *            the user who wants to upvote a answer
     * @param answerID
     *            the id of the answer that will be upvoted
     */
    public void upVoteAnswer(Integer answerID) {
        Answer answer = this.contentCRUD.readAnswer(answerID);
        answer.addUpVotes();
        this.contentCRUD.update(answer);
    }

    /**
     * downvote a answer of a question
     * 
     * @param logged
     *            the user who wants to downvote a answer
     * @param answerID
     *            the id of the answer that will be downvoted
     */
    public void downVoteAnswer(Integer answerID) {
        Answer answer = this.contentCRUD.readAnswer(answerID);
        answer.addDownVotes();
        this.contentCRUD.update(answer);
    }

    private void unauthorizedException(String message) throws ContentsException {
        throw new ContentsException(message);
    }

    private boolean userAbleToEditContent(User user, AbstractContent content) {
        String authorName = content.getAuthor();
        String userName = user.getUsername();
        if (authorName == userName || this.areUserAdmin(user))
            return true;
        else
            return false;
    }

    private boolean areUserAdmin(User user) {
        Permission userPermission = user.getUserPermission();
        if (userPermission == Permission.ADMIN || userPermission == Permission.MODERATOR)
            return true;
        else
            return false;
    }

}
