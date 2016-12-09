/**
 * 
 */
package persistence;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import domain.AbstractContent;
import domain.Answer;
import domain.Comment;
import domain.Question;
import domain.User;
import utils.ContentsException;
import utils.Permission;
import utils.UserException;

/**
 * @author lmrodrigues
 *
 */
public class PersistenceTest extends AbstractCRUD {

    ContentsCRUD contentsCrud;
    UserCRUD     userCrud;

    User userOne;
    User userTwo;
    User userThree;

    Question questionOne;
    Question questionTwo;
    Question questionThree;

    Answer answerOne;
    Answer answerTwo;
    Answer answerThree;

    Comment commentOne;
    Comment commentTwo;
    Comment commentThree;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        contentsCrud = new ContentsCRUD("TestsCRUD");
        userCrud = new UserCRUD("TestsCRUD");

        assertTrue("Database not Empty before test", isDatabaseEmpty());

        userOne = new User("user_one", "user_one", "user@one", "User One", Permission.COMMON);
        userTwo = new User("user_two", "user_two", "user@Two", "User Two", Permission.COMMON);
        userThree = new User("user_three", "user_three", "user@three", "User  Three",
                Permission.COMMON);

        questionOne = new Question((long) 1, userOne, "Here is the Question One Text",
                "Question One", new ArrayList<String>());

        questionTwo = new Question((long) 2, userTwo, "Here is the Question Two Text",
                "Question Two", new ArrayList<String>());

        questionThree = new Question((long) 3, userThree, "Here is the Question Three Text",
                "Question Three", new ArrayList<String>());

        answerOne = new Answer((long) 1, userOne, "Here is the Answer One Text");
        answerTwo = new Answer((long) 2, userTwo, "Here is the Answer Two Text");
        answerThree = new Answer((long) 3, userThree, "Here is the Answer Three Text");

        commentOne = new Comment((long) 1, userOne, "Here is the Comment One Text");
        commentTwo = new Comment((long) 2, userTwo, "Here is the Comment Two Text");
        commentThree = new Comment((long) 3, userThree, "Here is the Comment Three Text");

    }

    @After
    public void tearDown() throws Exception {
        assertTrue("Database not Empty after test", isDatabaseEmpty());
    }

    /**
     * Test method for {@link persistence.ContentsCRUD#ContentsCRUD()} and
     * {@link persistence.UserCRUD#UserCRUD()}.
     * 
     * @throws Exception
     * 
     */
    @Test
    public final void testConstructorsCRUD() throws Exception {
        try {
            EntityManager contentsEm = new ContentsCRUD().getEntityManager();
            Boolean isValidContentsEm = contentsEm != null;

            assertTrue("ContentsCRUD Problem.", isValidContentsEm);

        } catch (Exception e) {
            throw new Exception("ContentsCRUD went wrong");
        }

        try {
            EntityManager userEm = new UserCRUD().getEntityManager();
            Boolean isValidUserEm = userEm != null;

            assertTrue("UserCRUD Problem.", isValidUserEm);

        } catch (Exception e) {
            throw new Exception("UserCRUD went wrong");
        }

    }

    /**
     * Test method for
     * {@link persistence.AbstractCRUD#ContentsCRUD(java.lang.String)} and
     * {@link persistence.UserCRUD#UserCRUD(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testContentsCRUDString() throws Exception {
        try {
            EntityManager contentsEm = new ContentsCRUD("TestsCRUD").getEntityManager();
            Boolean isValidContentsEm = contentsEm != null;

            assertTrue("ContentsCRUD Problem.", isValidContentsEm);

        } catch (Exception e) {
            throw new Exception("ContentsCRUD went wrong");
        }

        try {
            EntityManager userEm = new UserCRUD("TestsCRUD").getEntityManager();
            Boolean isValidUserEm = userEm != null;

            assertTrue("UserCRUD Problem.", isValidUserEm);

        } catch (Exception e) {
            throw new Exception("UserCRUD went wrong");
        }
    }

    /**
     * Test method for {@link persistence.UserCRUD}.
     * 
     * @throws UserException
     * 
     * @throws Exception
     */
    @Test
    public final void testCreateReadUpdateDeleteUser() throws UserException {
        userCrud.create(userOne);

        try {
            userCrud.create(userOne);
        } catch (UserException e) {
            if (e.getMessage() == "invalid.email.or.username") {
                assertTrue("The user should not be added", true);
            } else {
                fail("Unexpected Error");
            }
        }

        User userOneRead = userCrud.read(userOne.getUsername());
        Boolean areUsersEquals = this.equalsUsers(userOne, userOneRead);

        assertTrue("Problem writing or reading user", areUsersEquals);

        userOneRead.setBlockStatus(true);

        userCrud.update(userOneRead);

        User userOneUpdated = userCrud.read(userOneRead.getUsername());

        Boolean stillUsersEquals = this.equalsUsers(userOneRead, userOneUpdated);

        assertTrue("Problem updating user", stillUsersEquals);

        userCrud.delete(userOneRead);

    }

    /**
     * Test method for {@link persistence.ContentsCRUD}.
     * 
     * @throws Exception
     */
    @Test
    public final void testCreateReadUpdateDeleteQuestion() throws Exception {

        userCrud.create(userOne);
        contentsCrud.create(questionOne);

        try {
            contentsCrud.create(questionOne);
        } catch (ContentsException e) {
            if (e.getMessage() == "invalid.id") {
                assertTrue("The user should not be added", true);
            } else {
                fail("Unexpected Error");
            }
        }

        Question questionOneRead = contentsCrud.readQuestion((long) 1);

        assertTrue("Problem Writing or Reading Question One",
                equalsContents(questionOne, questionOneRead));

        questionOneRead.addAnswer(answerTwo);
        contentsCrud.update(questionOneRead);

        Question questionOneWithAnswer = contentsCrud.readQuestion((long) 1);

        assertTrue("Problem updating Question One",
                equalsContents(answerTwo, questionOneWithAnswer.getAnswers().get(0)));

        assertTrue("Problem updating author references on answer",
                equalsUsers(userTwo, questionOneWithAnswer.getAnswers().get(0).getAuthor()));

        User userTwoRead = userCrud.read(userTwo.getUsername());
        assertTrue("Problem updating users references", userTwoRead.getUserAnswers().isEmpty());

        contentsCrud.delete(questionOneWithAnswer);

        Answer answerTwoDeleted = null;
        try {
            answerTwoDeleted = contentsCrud.readAnswer((long) 2);
            throw new ContentsException("Answer should not exists");
        } catch (ContentsException e) {
            assertTrue("This answer should not exists", answerTwoDeleted == null);
        }

        User userTwoReadAgain = userCrud.read(userTwo.getUsername());
        assertTrue("Problem removing users answer", userTwoReadAgain.getUserAnswers().isEmpty());

        userCrud.delete(userTwo);
        userCrud.delete(userOne);

    }

    /**
     * Test method for {@link persistence.ContentsCRUD}.
     * 
     * @throws Exception
     */
    @Test
    public final void testCreateAnswer() throws Exception {
        userCrud.create(userThree);

        contentsCrud.create(answerThree);
        Answer answerThreeRead = contentsCrud.readAnswer((long) 3);

        assertTrue("Problem Writing or Reading Question One",
                equalsContents(answerThree, answerThreeRead));

        contentsCrud.delete(answerThreeRead);

        userCrud.delete(userThree);
    }

    @Test
    public final void testUpdateAnswer() throws Exception {
        userCrud.create(userThree);
        userCrud.create(userTwo);

        contentsCrud.create(answerThree);
        answerThree.addComment(commentTwo);
        contentsCrud.update(answerThree);

        Answer answerThreeRead = contentsCrud.readAnswer((long) 3);
        Comment commentFiveRead = contentsCrud.readComment((long) 2);

        assertTrue("Problems updating Answer",
                equalsContents(answerThreeRead.getComments().get(0), commentFiveRead));
        contentsCrud.delete(answerThreeRead);

        userCrud.delete(userTwo);
        userCrud.delete(userThree);

    }

    @Test
    public final void testDeleteAnswer() throws Exception {

        userCrud.create(userOne);
        userCrud.create(userTwo);

        contentsCrud.create(questionOne);
        contentsCrud.create(answerTwo);
        questionOne.addAnswer(answerTwo);
        contentsCrud.update(questionOne);

        Question questionOneRead = contentsCrud.readQuestion((long) 1);
        questionOneRead.delAnswer(answerTwo);
        contentsCrud.update(questionOneRead);

        Answer answerTwoDeleted = null;
        try {
            answerTwoDeleted = contentsCrud.readAnswer((long) 2);
            throw new ContentsException("This answer should not exists");
        } catch (ContentsException e) {
            Question questionOneUpdated = contentsCrud.readQuestion((long) 1);
            assertTrue("Problem updating references on delete",
                    questionOneUpdated.getAnswers().isEmpty());
            assertTrue("Problem deleting answer", answerTwoDeleted == null);
        }
        contentsCrud.delete(questionOne);

        userCrud.delete(userOne);
        userCrud.delete(userTwo);

    }

    @Test
    public final void testDeleteCascadeAnswer() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);

        questionOne.setAnswers(new ArrayList<Answer>());
        questionOne.addAnswer(answerTwo);
        contentsCrud.create(answerTwo);
        contentsCrud.create(questionOne);
        contentsCrud.delete(questionOne);

        Answer answerTwoDeleted = null;
        try

        {
            answerTwoDeleted = contentsCrud.readAnswer((long) 2);
            throw new ContentsException("This answer should not exists");
        } catch (ContentsException e) {
            assertTrue("Problem deleting answer in Cascade", answerTwoDeleted == null);
        }

        userCrud.delete(userOne);
        userCrud.delete(userTwo);

    }

    /**
     * Test method for {@link persistence.ContentsCRUD}.
     * 
     * @throws Exception
     */
    @Test
    public final void testCreateReadUpdateDeleteComments() throws Exception {
        userCrud.create(userOne);

        contentsCrud.create(commentOne);
        Comment commentOneRead = contentsCrud.readComment((long) 1);
        assertTrue("Problems on create comment", equalsContents(commentOne, commentOneRead));

        commentOneRead.setText("New text of the comment One");
        contentsCrud.update(commentOneRead);

        Comment commentOneUpdated = contentsCrud.readComment((long) 1);
        assertTrue("Problems on update comment", equalsContents(commentOneRead, commentOneUpdated));

        contentsCrud.delete(commentOne);
        userCrud.delete(userOne);

    }

    @Test
    public final void testDeleteCascadeComment() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);
        userCrud.create(userThree);

        questionOne.addComment(commentTwo);
        contentsCrud.create(commentTwo);
        contentsCrud.create(questionOne);
        contentsCrud.delete(questionOne);

        Comment commentTwoDeleted = null;
        try

        {
            commentTwoDeleted = contentsCrud.readComment((long) 2);
            throw new ContentsException("This comment should not exists");
        } catch (ContentsException e) {
            assertTrue("Problem deleting comment in Cascade from question",
                    commentTwoDeleted == null);
        }

        answerOne.addComment(commentThree);
        contentsCrud.create(commentThree);
        contentsCrud.create(answerOne);
        contentsCrud.delete(answerOne);

        Comment commentThreeDeleted = null;
        try

        {
            commentThreeDeleted = contentsCrud.readComment((long) 3);
            throw new ContentsException("This comment should not exists");
        } catch (ContentsException e) {
            assertTrue("Problem deleting comment in Cascade from answer",
                    commentThreeDeleted == null);
        }

        userCrud.delete(userThree);
        userCrud.delete(userTwo);
        userCrud.delete(userOne);

    }

    /**
     * Test method for
     * {@link persistence.ContentsCRUD#search(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public final void testSearch() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);
        userCrud.create(userThree);

        contentsCrud.create(questionOne);
        contentsCrud.create(questionTwo);
        contentsCrud.create(questionThree);

        List<Question> searchQuestions = contentsCrud.search("Question");

        assertTrue("Question One not Present", searchQuestions.contains(questionOne));
        assertTrue("Question Two not Present", searchQuestions.contains(questionTwo));
        assertTrue("Question Three not Present", searchQuestions.contains(questionThree));

        contentsCrud.delete(questionThree);
        contentsCrud.delete(questionTwo);
        contentsCrud.delete(questionOne);

        userCrud.delete(userThree);
        userCrud.delete(userTwo);
        userCrud.delete(userOne);

    }

    /**
     * Test method for {@link persistence.ContentsCRUD#listAllQuestion()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testListAllQuestion() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);
        userCrud.create(userThree);

        contentsCrud.create(questionOne);
        contentsCrud.create(questionTwo);
        contentsCrud.create(questionThree);

        List<Question> searchQuestions = contentsCrud.listAllQuestions();
        assertTrue("Question One not Present", searchQuestions.contains(questionOne));
        assertTrue("Question Two not Present", searchQuestions.contains(questionTwo));
        assertTrue("Question Three not Present", searchQuestions.contains(questionThree));

        contentsCrud.delete(questionThree);

        searchQuestions = contentsCrud.listAllQuestions();
        assertTrue("Question One not Present", searchQuestions.contains(questionOne));
        assertTrue("Question Two not Present", searchQuestions.contains(questionTwo));
        assertTrue("Question Three Present", !searchQuestions.contains(questionThree));

        contentsCrud.delete(questionTwo);
        contentsCrud.delete(questionOne);

        userCrud.delete(userThree);
        userCrud.delete(userTwo);
        userCrud.delete(userOne);

    }

    /**
     * Test method for {@link persistence.ContentsCRUD#listAllQuestion()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testListAllAnswers() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);
        userCrud.create(userThree);

        contentsCrud.create(answerOne);
        contentsCrud.create(answerTwo);
        contentsCrud.create(answerThree);

        List<Answer> searchAnswers = contentsCrud.listAllAnswers();
        assertTrue("Question One not Present", searchAnswers.contains(answerOne));
        assertTrue("Question Two not Present", searchAnswers.contains(answerTwo));
        assertTrue("Question Three not Present", searchAnswers.contains(answerThree));

        contentsCrud.delete(answerThree);

        searchAnswers = contentsCrud.listAllAnswers();
        assertTrue("Question One not Present", searchAnswers.contains(answerOne));
        assertTrue("Question Two not Present", searchAnswers.contains(answerTwo));
        assertTrue("Question Three Present", !searchAnswers.contains(answerThree));

        contentsCrud.delete(answerTwo);
        contentsCrud.delete(answerOne);

        userCrud.delete(userThree);
        userCrud.delete(userTwo);
        userCrud.delete(userOne);

    }

    /**
     * Test method for {@link persistence.ContentsCRUD#getMaxQuestionId()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetMaxQuestionId() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);

        Long maxInitial = contentsCrud.getMaxQuestionId();
        assertTrue("Something get Wrong on Init", maxInitial == 0);

        contentsCrud.create(questionOne);

        Long maxFirstCreate = contentsCrud.getMaxQuestionId();
        assertTrue("Something get Wrong on First create", maxFirstCreate == questionOne.getId());

        contentsCrud.create(questionTwo);

        Long maxSecondCreate = contentsCrud.getMaxQuestionId();
        assertTrue("Something get Wrong on Second create", maxSecondCreate == questionTwo.getId());

        contentsCrud.delete(questionTwo);

        Long maxFirstDelete = contentsCrud.getMaxQuestionId();
        assertTrue("Something get Wrong on First delete", maxFirstDelete == questionOne.getId());

        contentsCrud.delete(questionOne);

        Long maxSecondDelete = contentsCrud.getMaxCommentId();
        assertTrue("Something get Wrong on Second delete", maxSecondDelete == 0);

        userCrud.delete(userTwo);
        userCrud.delete(userOne);
    }

    /**
     * Test method for {@link persistence.ContentsCRUD#getMaxCommentId()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetMaxCommentId() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);

        Long maxInitial = contentsCrud.getMaxCommentId();
        assertTrue("Something get Wrong on Init", maxInitial == 0);

        contentsCrud.create(commentOne);

        Long maxFirstCreate = contentsCrud.getMaxCommentId();
        assertTrue("Something get Wrong on First create", maxFirstCreate == commentOne.getId());

        contentsCrud.create(commentTwo);

        Long maxSecondCreate = contentsCrud.getMaxCommentId();
        assertTrue("Something get Wrong on Second create", maxSecondCreate == commentTwo.getId());

        contentsCrud.delete(commentTwo);

        Long maxFirstDelete = contentsCrud.getMaxCommentId();
        assertTrue("Something get Wrong on First delete", maxFirstDelete == commentOne.getId());

        contentsCrud.delete(commentOne);

        Long maxSecondDelete = contentsCrud.getMaxCommentId();
        assertTrue("Something get Wrong on Second delete", maxSecondDelete == 0);

        userCrud.delete(userTwo);
        userCrud.delete(userOne);
    }

    /**
     * Test method for {@link persistence.ContentsCRUD#getMaxAnswerId()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetMaxAnswerId() throws Exception {
        userCrud.create(userOne);
        userCrud.create(userTwo);

        Long maxInitial = contentsCrud.getMaxAnswerId();
        assertTrue("Something get Wrong on Init", maxInitial == 0);

        contentsCrud.create(answerOne);

        Long maxFirstCreate = contentsCrud.getMaxAnswerId();
        assertTrue("Something get Wrong on First create", maxFirstCreate == answerOne.getId());

        contentsCrud.create(answerTwo);

        Long maxSecondCreate = contentsCrud.getMaxAnswerId();
        assertTrue("Something get Wrong on Second create", maxSecondCreate == answerTwo.getId());

        contentsCrud.delete(answerTwo);

        Long maxFirstDelete = contentsCrud.getMaxAnswerId();
        assertTrue("Something get Wrong on First delete", maxFirstDelete == answerOne.getId());

        contentsCrud.delete(answerOne);

        Long maxSecondDelete = contentsCrud.getMaxAnswerId();
        assertTrue("Something get Wrong on Second delete", maxSecondDelete == 0);

        userCrud.delete(userTwo);
        userCrud.delete(userOne);
    }

    private boolean isDatabaseEmpty() throws Exception {
        Boolean emptyQuestions = contentsCrud.listAllQuestions().isEmpty();
        Boolean emptyAnswers = contentsCrud.listAllAnswers().isEmpty();
        Boolean emptyComments = contentsCrud.listAllComments().isEmpty();
        Boolean emptyUsers = userCrud.listAll().isEmpty();

        return (emptyQuestions && emptyAnswers && emptyComments && emptyUsers);

    }

    private Boolean equalsUsers(User originalUser, User newUser) {
        Boolean usernameEquals = originalUser.getUsername() == newUser.getUsername();
        Boolean passwordEquals = originalUser.getPassword() == newUser.getPassword();
        Boolean emailEquals = originalUser.getEmail() == newUser.getEmail();
        Boolean nameEquals = originalUser.getName() == newUser.getName();
        Boolean statusEquals = originalUser.getBlockStatus() == newUser.getBlockStatus();
        Boolean permissionEquals = originalUser.getUserPermission() == newUser.getUserPermission();

        return usernameEquals && emailEquals && nameEquals && passwordEquals && statusEquals
                && permissionEquals;
    }

    private Boolean equalsContents(AbstractContent originalContent, AbstractContent newContent) {
        Boolean idEquals = originalContent.getId() == newContent.getId();
        Boolean authorEquals = equalsUsers(originalContent.getAuthor(), newContent.getAuthor());
        Boolean dateEquals = originalContent.getDate() == newContent.getDate();
        Boolean textEquals = originalContent.getText() == newContent.getText();

        return idEquals && authorEquals && dateEquals && textEquals;
    }

}
