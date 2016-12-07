/**
 * 
 */
package domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lmrodrigues
 * @author Scheffel-V
 *
 */
public class AnswerTest {

    ArrayList<Answer>  answers;
    ArrayList<Comment> comments;

    User               user     = new User("Vinicius", "123abc", "brsufirefox@gmail.com", "Name");

    Comment            comment0 = new Comment(2, user, "Comment Text");
    Comment            comment1 = new Comment(3, user, "Comment Text Two");
    Comment            comment2 = new Comment(4, user, "Test Text");

    Answer             answer0  = new Answer();
    Answer             answer1  = new Answer(1, user, "Text");

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        comments = new ArrayList<Comment>();
        comments.add(comment0);
        comments.add(comment1);
        comments.add(comment2);

        answers = new ArrayList<Answer>();
        answers.add(answer0); // 0 - Null Answer.
        answers.add(answer1); // 1 - Answer with arguments.
    }

    /**
     * Test method for
     * {@link domain.Answer#Answer(java.lang.Integer, domain.User, java.lang.String)}
     * . This method will check the Answer constructor.
     */
    @Test
    public final void testAnswer() {

        assertNotNull("Problem in Answer construct with no arguments.", answers.get(0));
        assertTrue("Problem in ID field of Answer construct.", answers.get(0).getId() == null);
        assertTrue("Problem in author field of Answer construct.", answers.get(0).getAuthor() == null);
        assertTrue("Problem in text field of Answer construct.", answers.get(0).getText() == null);
        assertNotNull("Problem in Answer construct with arguments.", answers.get(1));
        assertTrue("Problem in ID field of Answer construct.", answers.get(1).getId() == 1);
        assertTrue("Problem in author field of Answer construct.",
                answers.get(1).getAuthor().getUsername() == "Vinicius");
        assertTrue("Problem in text field of Answer construct.", answers.get(1).getText() == "Text");
    }

    /**
     * Test method for {@link domain.Answer#getId()}. This test will check the
     * getId method.
     */
    @Test
    public final void testGetId() {

        assertTrue("Problem in getId of Answer construct.", answers.get(0).getId() == null);
        assertTrue("Problem in getId of Answer construct.", answers.get(1).getId() == 1);
    }

    /**
     * Test method for {@link domain.Answer#setId()}. This test will check the
     * setId method.
     */
    @Test
    public final void testSetId() {

        answers.get(1).setId(2);
        assertTrue("Problem in setId of Answer construct.", answers.get(1).getId() == 2);
        answers.get(1).setId(3);
        assertTrue("Problem in setId of Answer construct.", answers.get(1).getId() == 3);
    }

    /**
     * Test method for {@link domain.Answer#getDate()}. This test will check the
     * getDate method.
     */
    @Test
    public final void testGetDate() {
        Date date = new Date();
        answers.get(1).setDate(date);
        assertTrue("Problem in getDate of Answer construct.", answers.get(1).getDate() == date);
    }

    /**
     * Test method for {@link domain.Answer#setDate()}. This test will check the
     * setDate method.
     */
    @Test
    public final void testSetDate() {
        Date date_1 = new Date();
        Date date_2 = new Date();
        answers.get(1).setDate(date_1);
        assertTrue("Problem in setDate of Answer construct.", answers.get(1).getDate() == date_1);
        answers.get(1).setDate(date_2);
        assertTrue("Problem in setDate of Answer construct.", answers.get(1).getDate() == date_2);
    }

    /**
     * Test method for {@link domain.Answer#setAuthor()}. This test will check
     * the setAuthor method.
     */
    @Test
    public final void testSetAuthor() {

        answers.get(1).setAuthor(user);
        assertTrue("Problem in setAuthor of Answer construct.", answers.get(1).getAuthor() == user);
        User user_1 = new User();
        answers.get(1).setAuthor(user_1);
        assertTrue("Problem in setAuthor of Answer construct.", answers.get(1).getAuthor() == user_1);
    }

    /**
     * Test method for {@link domain.Answer#getAuthor()}. This test will check
     * the getAuthor method.
     */
    @Test
    public final void testGetAuthor() {

        assertTrue("Problem in getAuthor of Answer construct.", answers.get(1).getAuthor() == user);
        User user_1 = new User();
        answers.get(1).setAuthor(user_1);
        assertTrue("Problem in getAuthor of Answer construct.", answers.get(1).getAuthor() == user_1);
    }

    /**
     * Test method for {@link domain.Answer#getText()}. This test will check the
     * getText method.
     */
    @Test
    public final void testGetText() {

        assertTrue("Problem in getText of Answer construct.", answers.get(1).getText() == "Text");
        answers.get(1).setText("Text Test");
        assertTrue("Problem in getText of Answer construct.", answers.get(1).getText() == "Text Test");
    }

    /**
     * Test method for {@link domain.Answer#setText()}. This test will check the
     * setText method.
     */
    @Test
    public final void testSetText() {

        answers.get(1).setText("Text");
        assertTrue("Problem in setText of Answer construct.", answers.get(1).getText() == "Text");
        answers.get(1).setText("Text Test");
        assertTrue("Problem in setText of Answer construct.", answers.get(1).getText() == "Text Test");
    }

    /**
     * Test method for {@link domain.Answer#getComments()}. This test will check
     * the getComments method.
     */
    @Test
    public final void testGetComments() {
        assertTrue("Problem in getComments.", answers.get(0).getComments() == null);
        assertTrue("Problem in getComments.", answers.get(1).getComments().isEmpty() == true);
        answers.get(1).setComments(comments);
        assertTrue("Problem in getComments.", answers.get(1).getComments().get(0).getId() == 2);
        assertTrue("Problem in getComments.", answers.get(1).getComments().get(1).getId() == 3);
    }

    /**
     * Test method for {@link domain.Answer#setComments(java.util.List)}. This
     * test will check the setComments method.
     */
    @Test
    public final void testSetComments() {
        answers.get(1).setComments(comments);
        assertTrue("Problem in setComments.", answers.get(1).getComments().get(0).getId() == 2);
        assertTrue("Problem in setComments.", answers.get(1).getComments().get(1).getId() == 3);
        answers.get(1).delComment(comment0);
        answers.get(1).delComment(comment1);
        answers.get(1).delComment(comment2);
        comments.add(comment0);
        comments.add(comment1);
        comments.add(comment2);
        answers.get(1).setComments(comments);
        assertTrue("Problem in setComments.", answers.get(1).getComments().get(0).getId() == 2);
        answers.get(1).setComments(null);
        assertTrue("Problem in setComments.", answers.get(1).getComments() == null);
    }

    /**
     * Test method for {@link domain.Answer#addComment(domain.Comment)}. This
     * test will check the addComment method.
     */
    @Test
    public final void testAddComment() {
        answers.get(1).setComments(new ArrayList<Comment>());
        answers.get(1).addComment(comments.get(0));
        answers.get(1).addComment(comments.get(1));
        answers.get(1).addComment(comments.get(2));
        assertTrue("Problem in addComment.", answers.get(1).getComments().get(0).getId() == 2);
        assertTrue("Problem in addComment.", answers.get(1).getComments().get(1).getId() == 3);
        assertTrue("Problem in addComment.", answers.get(1).getComments().get(2).getId() == 4);
        answers.get(1).delComment(comments.get(0));
        answers.get(1).delComment(comments.get(1));
        answers.get(1).delComment(comments.get(2));
        answers.get(1).addComment(null);
        assertTrue("Problem in addComment.", answers.get(1).getComments().isEmpty() == true);
        answers.get(1).addComment(comments.get(1));
        assertTrue("Problem in addComment.", answers.get(1).getComments().get(0).getId() == 3);
    }

    /**
     * Test method for {@link domain.Answer#delComment(domain.Comment)}. This
     * test will check the delComment method.
     */
    @Test
    public final void testDelComment() {
        answers.get(1).setComments(comments);
        answers.get(1).delComment(comment0);
        assertTrue("Problem in delComment.", answers.get(1).getComments().get(0).getId() == 3);
        assertTrue("Problem in delComment.", answers.get(1).getComments().get(1).getId() == 4);
        answers.get(1).delComment(comment1);
        answers.get(1).delComment(comment2);
        assertTrue("Problem in delComment.", answers.get(1).getComments().isEmpty() == true);
        answers.get(1).delComment(comment2);
        assertTrue("Problem in delComment.", answers.get(1).getComments().isEmpty() == true);
    }

    /**
     * Test method for {@link domain.Answer#getUpVotes()}. This test will check
     * the getUpVotes method.
     */
    @Test
    public final void testGetUpVotes() {
        assertTrue("Problem in getUpVotes", answers.get(1).getUpVotes() == 0);
        answers.get(1).addUpVotes();
        assertTrue("Problem in getUpVotes", answers.get(1).getUpVotes() == 1);
        answers.get(1).addUpVotes();
        assertTrue("Problem in getUpVotes", answers.get(1).getUpVotes() == 2);
    }

    /**
     * Test method for {@link domain.Answer#setUpVotes(java.lang.Integer)}. This
     * test will check the setUpVotes method.
     */
    @Test
    public final void testSetUpVotes() {
        answers.get(1).setUpVotes(null);
        assertTrue("Problem in setUpVotes", answers.get(1).getUpVotes() == null);
        answers.get(1).setUpVotes(2);
        assertTrue("Problem in setUpVotes", answers.get(1).getUpVotes() == 2);
        answers.get(1).setUpVotes(1);
        assertTrue("Problem in setUpVotes", answers.get(1).getUpVotes() == 1);
    }

    /**
     * Test method for {@link domain.Answer#addUpVotes()}. This test will check
     * the addUpVotes method.
     */
    @Test
    public final void testAddUpVotes() {
        answers.get(1).setUpVotes(0);
        answers.get(1).addUpVotes();
        assertTrue("Problem in addUpVotes", answers.get(1).getUpVotes() == 1);
        answers.get(1).addUpVotes();
        assertTrue("Problem in addUpVotes", answers.get(1).getUpVotes() == 2);
    }

    /**
     * Test method for {@link domain.Answer#getDownVotes()}. This test will
     * check the getDownVotes method.
     */
    @Test
    public final void testGetDownVotes() {
        assertTrue("Problem in getDownVotes", answers.get(1).getDownVotes() == 0);
        answers.get(1).addDownVotes();
        assertTrue("Problem in getDownVotes", answers.get(1).getDownVotes() == 1);
        answers.get(1).addDownVotes();
        assertTrue("Problem in getDownVotes", answers.get(1).getDownVotes() == 2);
    }

    /**
     * Test method for {@link domain.Answer#setDownVotes(java.lang.Integer)}.
     * This test will check the setDownVotes method.
     */
    @Test
    public final void testSetDownVotes() {
        answers.get(1).setDownVotes(null);
        assertTrue("Problem in setDownVotes", answers.get(1).getDownVotes() == null);
        answers.get(1).setDownVotes(2);
        assertTrue("Problem in setDownVotes", answers.get(1).getDownVotes() == 2);
        answers.get(1).setDownVotes(1);
        assertTrue("Problem in setDownVotes", answers.get(1).getDownVotes() == 1);
    }

    /**
     * Test method for {@link domain.Answer#addDownVotes()}. This test will
     * check the addDownVotes method.
     */
    @Test
    public final void testAddDownVotes() {
        answers.get(1).setDownVotes(0);
        answers.get(1).addDownVotes();
        assertTrue("Problem in addDownVotes", answers.get(1).getDownVotes() == 1);
        answers.get(1).addDownVotes();
        assertTrue("Problem in addDownVotes", answers.get(1).getDownVotes() == 2);
    }

}
