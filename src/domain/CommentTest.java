/**
 * 
 */
package domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lmrodrigues
 * @author Scheffel-V
 *
 */
public class CommentTest {

    Comment comment_1;
    Comment comment_2;
    User    user;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        user = new User("Vinicius", "123abc", "brsufirefox@gmail.com", "Name");
        comment_1 = new Comment();
        comment_2 = new Comment(1, user, "Text");
    }

    /**
     * Test method for
     * {@link domain.Comment#Comment(java.lang.Integer, domain.User, java.lang.String)}
     * . This test will check the Comment constructor.
     */
    @Test
    public final void testComment() {
        assertNotNull("Problem in Comment construct with no arguments.", comment_1);
        assertTrue("Problem in ID field of Comment construct.", comment_1.getId() == null);
        assertTrue("Problem in author field of Comment construct.", comment_1.getAuthor() == null);
        assertTrue("Problem in text field of Comment construct.", comment_1.getText() == null);
        assertNotNull("Problem in Comment construct with arguments.", comment_2);
        assertTrue("Problem in ID field of Comment construct.", comment_2.getId() == 1);
        assertTrue("Problem in author field of Comment construct.",
                comment_2.getAuthor().getUsername().equals("Vinicius"));
        assertTrue("Problem in text field of Comment construct.", comment_2.getText().equals("Text"));
    }

}
