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
import utils.UserException;

/**
 * @author lmrodrigues
 * @author flmachado
 *
 */

public class ContentsController {
    private ContentsCRUD contentCRUD;

    /**
     * Create a new Contents controller for the API.
     */
    public ContentsController() {
        this.contentCRUD = new ContentsCRUD();
    }

    /**
     * Create a new Contents controller for the API. Allow Choose the
     * persistence unit.
     */
    public ContentsController(String persistenceUnit) {
        this.contentCRUD = new ContentsCRUD(persistenceUnit);
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
     * @throws ContentsException
     */
    public Question newQuestion(User logged, String text, List<String> tags, String title)
            throws ContentsException {
        Long id = this.contentCRUD.getMaxQuestionId() + 1;

        Question newQuestion = new Question(id, logged, text, title, tags);
        logged.addQuestion(newQuestion);

        try {
            this.contentCRUD.create(newQuestion);
        } catch (ContentsException e) {
            if (e.getMessage() == "invalid.id") {
                throw new ContentsException("replicated.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }
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
     * @throws UserException
     *             if user not exists on database
     */

    public void newAnswer(User logged, String text, Long questionID)
            throws ContentsException, UserException {
        Long answerId = this.contentCRUD.getMaxAnswerId() + 1;
        Question question = this.contentCRUD.readQuestion(questionID);

        if (question.getStatus() == Status.OPEN) {
            Answer newAnswer = new Answer(answerId, logged, text);

            question.addAnswer(newAnswer);
            logged.addAnswer(newAnswer);

            try {
                this.contentCRUD.update(question);
            } catch (ContentsException e) {
                if (e.getMessage() == "invalid.id") {
                    throw new ContentsException("replicated.id");
                } else {
                    throw new ContentsException("unexpected.error", e);
                }
            }

        } else {
            throw new ContentsException("closed.question");
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
     * @throws UserException
     *             if the user not exists on database
     */
    public void newComment(User logged, String text, Question question)
            throws ContentsException, UserException {
        Long commentId = this.contentCRUD.getMaxCommentId() + 1;
        Comment newComment = new Comment(commentId, logged, text);

        question.addComment(newComment);
        logged.addComment(newComment);

        try {
            this.contentCRUD.update(question);
        } catch (ContentsException e) {
            if (e.getMessage() == "invalid.id") {
                throw new ContentsException("replicated.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }

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
     * @throws UserException
     *             if user not exists on database
     */
    public void newComment(User logged, String text, Answer answer)
            throws ContentsException, UserException {
        Long commentId = this.contentCRUD.getMaxCommentId() + 1;
        Comment newComment = new Comment(commentId, logged, text);

        answer.addComment(newComment);
        logged.addComment(newComment);

        try {
            this.contentCRUD.update(answer);
        } catch (ContentsException e) {
            if (e.getMessage() == "invalid.id") {
                throw new ContentsException("replicated.id");
            } else {
                throw new ContentsException("unexpected.error", e);
            }
        }

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

        if (this.isAbleToEdit(logged, content)) {
            this.contentCRUD.update(content);
        } else {
            throw new ContentsException("not.authorized.user");
        }

    }

    /**
     * 
     * Delete a content of dataBase
     * 
     * @param logged
     *            who deletes the content
     * @param content
     *            the content that will be deleted
     * @throws ContentsException
     *             - in case who wants to delete the content doesn't have
     *             permission to delete it;
     * 
     *             - in case the content to be deleted is invalid;
     * @throws UserException
     *             in case the author of question is invalid
     */
    public void deleteContent(User logged, AbstractContent content)
            throws ContentsException, UserException {
        if (this.isAbleToEdit(logged, content)) {

            if (content instanceof Question) {
                if (logged == content.getAuthor()) {
                    logged.delQuestion((Question) content);
                }

                this.contentCRUD.delete((Question) content);

            } else if (content instanceof Answer) {
                if (logged == content.getAuthor()) {
                    logged.delAnswer((Answer) content);
                }
                this.contentCRUD.delete((Answer) content);

            } else if (content instanceof Comment) {
                if (logged == content.getAuthor()) {
                    logged.delComment((Comment) content);
                }

                this.contentCRUD.delete((Comment) content);

            } else {
                throw new ContentsException("invalid.content");
            }

        } else {
            throw new ContentsException("not.authorized.user");
        }
    }

    /**
     * return a specific question from the database
     * 
     * @param questionID
     *            wanted question's id
     * @return the wanted question
     * @throws ContentsException
     *             if question not exists
     */
    public Question selectQuestion(Long questionID) throws ContentsException {
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
        return this.contentCRUD.listAllQuestions();
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
     *             author of the question;
     * 
     *             if Question not exists;
     * 
     *             if Answer not exists;
     */
    public void bestAnswer(User logged, Long questionID, Long answerID) throws ContentsException {
        Question question = this.selectQuestion(questionID);

        Boolean isAuthor = logged.getUsername() == question.getAuthor().getUsername();
        if (isAuthor) {
            Answer bestAnswer = this.contentCRUD.readAnswer(answerID);
            question.setBestAnswer(bestAnswer);
            this.contentCRUD.update(question);

        } else {
            throw new ContentsException("user.is.not.author");
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
     * 
     *             in case the question not exists;
     */
    public void closeQuestion(User logged, Long questionID) throws ContentsException {
        if (isAdminOrModer(logged)) {

            Question toClose = this.selectQuestion(questionID);
            toClose.closeQuestion();
            this.contentCRUD.update(toClose);

        } else {
            throw new ContentsException("unauthorized.user");
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
     * 
     *             in case question not exists
     */
    public void openQuestion(User logged, Long questionID) throws ContentsException {
        if (isAdminOrModer(logged)) {

            Question toOpen = this.selectQuestion(questionID);
            toOpen.openQuestion();
            this.contentCRUD.update(toOpen);

        } else {
            throw new ContentsException("unauthorized.user");
        }
    }

    /**
     * upvote a answer of a question
     * 
     * @param logged
     *            the user who wants to upvote a answer
     * @param answerID
     *            the id of the answer that will be upvoted
     * @throws ContentsException
     *             in case the answer not exists
     */
    public void upVoteAnswer(Long answerID) throws ContentsException {
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
     * @throws ContentsException
     *             in case the answer not exists
     */
    public void downVoteAnswer(Long answerID) throws ContentsException {
        Answer answer = this.contentCRUD.readAnswer(answerID);
        answer.addDownVotes();
        this.contentCRUD.update(answer);
    }

    private boolean isAbleToEdit(User logged, AbstractContent content) {
        Boolean isAuthor = logged.getUsername() == content.getAuthor().getUsername();

        if (isAuthor || isAdminOrModer(logged)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAdminOrModer(User logged) {
        Permission userPermission = logged.getUserPermission();
        Boolean isAdmin = userPermission == Permission.ADMIN;
        Boolean isModer = userPermission == Permission.MODERATOR;

        if (isAdmin || isModer) {
            return true;
        } else {
            return false;
        }
    }

}
