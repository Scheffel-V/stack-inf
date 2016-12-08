/**
 * 
 */
package domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lmrodrigues
 * @author Scheffel-V
 *
 */
public class CommentTest {

    Comment comment_0;
    Comment comment_1;
    Comment comment_2;
    User    user;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        user = new User("Vinicius", "123abc", "brsufirefox@gmail.com", "Name");
        comment_0 = new Comment();
        comment_1 = new Comment((long) 1, user, "Text");
        comment_2 = new Comment((long) 1, user, "Text");
    }

    /**
     * Test method for
     * {@link domain.Comment#Comment(java.lang.Integer, domain.User, java.lang.String)}
     * . This test will check the Comment constructor.
     */
    @Test
    public final void testComment() {
        assertNotNull("Problem in Comment construct with no arguments.", comment_1);
        assertTrue("Problem in ID field of Comment construct.", comment_0.getId() == null);
        assertTrue("Problem in author field of Comment construct.", comment_0.getAuthor() == null);
        assertTrue("Problem in text field of Comment construct.", comment_0.getText() == null);
        assertNotNull("Problem in Comment construct with arguments.", comment_2);
        assertTrue("Problem in ID field of Comment construct.", comment_2.getId() == 1);
        assertTrue("Problem in author field of Comment construct.",
                comment_2.getAuthor().getUsername().equals("Vinicius"));
        assertTrue("Problem in text field of Comment construct.",
                comment_2.getText().equals("Text"));
    }

    /**
     * Test method for {@link domain.Answer#getId()}. This test will check the
     * getId method.
     */
    @Test
    public final void testGetId() {

        assertTrue("Problem in getId of Answer construct.", comment_0.getId() == null);
        assertTrue("Problem in getId of Answer construct.", comment_1.getId() == 1);
    }

    /**
     * Test method for {@link domain.Answer#setId()}. This test will check the
     * setId method.
     */
    @Test
    public final void testSetId() {

        comment_1.setId((long) 2);
        assertTrue("Problem in setId of Answer construct.", comment_1.getId() == 2);
        comment_2.setId((long) 3);
        assertTrue("Problem in setId of Answer construct.", comment_2.getId() == 3);
    }

    /**
     * Test method for {@link domain.Answer#getDate()}. This test will check the
     * getDate method.
     */
    @Test
    public final void testGetDate() {
        Date date = new Date();
        comment_1.setDate(date);
        assertTrue("Problem in getDate of Answer construct.", comment_1.getDate() == date);
    }

    /**
     * Test method for {@link domain.Answer#setDate()}. This test will check the
     * setDate method.
     */
    @Test
    public final void testSetDate() {
        Date date_1 = new Date();
        Date date_2 = new Date();
        comment_1.setDate(date_1);
        assertTrue("Problem in setDate of Answer construct.", comment_1.getDate() == date_1);
        comment_1.setDate(date_2);
        assertTrue("Problem in setDate of Answer construct.", comment_1.getDate() == date_2);
    }

    /**
     * Test method for {@link domain.Answer#setAuthor()}. This test will check
     * the setAuthor method.
     */
    @Test
    public final void testSetAuthor() {

        comment_1.setAuthor(user);
        assertTrue("Problem in setAuthor of Answer construct.", comment_1.getAuthor() == user);
        User user_1 = new User();
        comment_1.setAuthor(user_1);
        assertTrue("Problem in setAuthor of Answer construct.", comment_1.getAuthor() == user_1);
    }

    /**
     * Test method for {@link domain.Answer#getAuthor()}. This test will check
     * the getAuthor method.
     */
    @Test
    public final void testGetAuthor() {

        assertTrue("Problem in getAuthor of Answer construct.", comment_1.getAuthor() == user);
        User user_1 = new User();
        comment_1.setAuthor(user_1);
        assertTrue("Problem in getAuthor of Answer construct.", comment_1.getAuthor() == user_1);
    }

    /**
     * Test method for {@link domain.Answer#getText()}. This test will check the
     * getText method.
     */
    @Test
    public final void testGetText() {

        assertTrue("Problem in getText of Answer construct.", comment_1.getText() == "Text");
        comment_1.setText("Text Test");
        assertTrue("Problem in getText of Answer construct.", comment_1.getText() == "Text Test");
    }

    /**
     * Test method for {@link domain.Answer#setText()}. This test will check the
     * setText method.
     */
    @Test
    public final void testSetText() {

        comment_1.setText("Text");
        assertTrue("Problem in setText of Answer construct.", comment_1.getText() == "Text");
        comment_1.setText("Text Test");
        assertTrue("Problem in setText of Answer construct.", comment_1.getText() == "Text Test");
    }

}
