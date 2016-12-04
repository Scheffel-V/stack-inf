/**
 * 
 */
package domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lmrodrigues
 *
 */
public class UserUnityTest {

    User                user;
    ArrayList<User>     userList;
    ArrayList<Question> questions;
    ArrayList<Answer>   answers;
    ArrayList<Comment>  comments;
    List<String>        tags;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        userList = new ArrayList<User>();
        userList.add(new User()); // 0 - User with no arguments.
        userList.add(new User("vbscheffel", "123abc", "brsufirefox@gmail.com", "Vinicius")); // 1
                                                                                             // -
                                                                                             // User
                                                                                             // with
                                                                                             // arguments.

        tags = null;
        questions.add(new Question());
        questions.add(new Question());
        questions.add(new Question("Texto", tags, "Titulo", "Autor", 1));

        answers.add(new Answer());
        answers.add(new Answer());
        answers.add(new Answer("Texto", "Autor", 2));

        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment("Texto", "Autor", 3.0));
    }

    /**
     * Test method for {@link domain.User#User()}. This test will check if the
     * argumentless construct is correct.
     */
    @Test
    public final void testUser() {
        assertNotNull("User argumentless constructor gives null object.", userList.get(0));
        assertTrue("Username field of argumentless User construct is not null.", userList.get(0).getUsername() == null);
        assertTrue("Password field of argumentless User construct is not null.", userList.get(0).getPassword() == null);
        assertTrue("Name field of argumentless User construct is not null.", userList.get(0).getName() == null);
        assertTrue("Email field of argumentless User construct is not null.", userList.get(0).getEmail() == null);
        assertTrue("BlockStatus field of argumentless User construct is not null.",
                userList.get(0).getBlockStatus() == null);
        assertTrue("UserPermission field of argumentless User construct is not null.",
                userList.get(0).getUserPermission() == null);
        assertTrue("UserAnswers field of argumentless User construct is not null.",
                userList.get(0).getUserAnswers() == null);
        assertTrue("UserComments of argumentless User construct is not null.",
                userList.get(0).getUserComments() == null);
        assertTrue("UserQuestions field of argumentless User construct is not null.",
                userList.get(0).getUserQuestions() == null);
    }

    /**
     * Test method for
     * {@link domain.User#User(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * . This test will check if the construct with arguments is correct.
     */
    @Test
    public final void testUserStringStringStringString() {
        assertNotNull("User constructor with arguments gives null object.", userList.get(1));
        assertTrue("Problem in username field of construct with arguments.",
                userList.get(1).getUsername() == "vbscheffel");
        assertTrue("Problem in password field of construct with arguments.", userList.get(1).getPassword() == "123abc");
        assertTrue("Problem in name field of construct with arguments.", userList.get(1).getName() == "Vinicius");
        assertTrue("Problem in email field of construct with arguments.",
                userList.get(1).getEmail() == "brsufirefox@gmail.com");
        assertTrue("blockStatus from construct with arguments is not false.",
                userList.get(1).getBlockStatus() == false);
        assertSame("Problem in userPermission field from construct with arguments.", Permission.COMMON,
                userList.get(1).getUserPermission());
        assertSame("Problem in userAnswers list field from construct with arguments.", new ArrayList<Answer>(),
                userList.get(1).getUserAnswers());
        assertSame("Problem in userComments list field from construct with arguments.", new ArrayList<Comment>(),
                userList.get(1).getUserComments());
        assertSame("Problem in userQuestions list field from construct with arguments.", new ArrayList<Question>(),
                userList.get(1).getUserQuestions());
    }

    /**
     * Test method for {@link domain.User#getUsername()}. This test will check
     * if the getUsername method is correct.
     */
    @Test
    public final void testGetUsername() {
        assertTrue("Problem in getUsername.", userList.get(0).getUsername() == null);
        assertTrue("Problem in getUsername.", userList.get(1).getUsername() == "vbscheffel");
    }

    /**
     * Test method for {@link domain.User#setUsername(java.lang.String)}. This
     * test will check if the setUsername method is correct.
     */
    @Test
    public final void testSetUsername() {
        userList.get(0).setUsername("vbscheffel_test");
        assertTrue("Problem in setUsername.", userList.get(0).getUsername() == "vbscheffel_test");
    }

    /**
     * Test method for {@link domain.User#getPassword()}. This test will check
     * if the getPassoword method is correct.
     */
    @Test
    public final void testGetPassword() {
        assertTrue("Problem in getPassword.", userList.get(0).getPassword() == null);
        assertTrue("Problem in getPassword.", userList.get(1).getPassword() == "123abc");
    }

    /**
     * Test method for {@link domain.User#setPassword(java.lang.String)}. This
     * test will check if the setPassoword method is correct.
     */
    @Test
    public final void testSetPassword() {
        userList.get(0).setPassword("test");
        assertTrue("Problem in setPassword.", userList.get(0).getPassword() == "test");
    }

    /**
     * Test method for {@link domain.User#getEmail()}. This test will check if
     * the getEmail method is correct.
     */
    @Test
    public final void testGetEmail() {
        assertTrue("Problem in getEmail.", userList.get(0).getEmail() == null);
        assertTrue("Problem in getEmail.", userList.get(1).getEmail() == "brsufirefox@gmail.com");
    }

    /**
     * Test method for {@link domain.User#setEmail(java.lang.String)}. This test
     * will check if the setEmail method is correct.
     */
    @Test
    public final void testSetEmail() {
        userList.get(0).setEmail("test@test.com");
        assertTrue("Problem in setEmail.", userList.get(0).getEmail() == "test@test.com");
    }

    /**
     * Test method for {@link domain.User#getName()}. This test will check if
     * the getName method is correct.
     */
    @Test
    public final void testGetName() {
        assertTrue("Problem in getName.", userList.get(0).getName() == null);
        assertTrue("Problem in getName.", userList.get(1).getName() == "Vinicius");
    }

    /**
     * Test method for {@link domain.User#setName(java.lang.String)}. This test
     * will check if the setName method is correct.
     */
    @Test
    public final void testSetName() {
        userList.get(0).setName("Test Name");
        assertTrue("Problem in setName.", userList.get(0).getName() == "Test Name");
    }

    /**
     * Test method for {@link domain.User#isBlock()}. This test will check if
     * the isBlock method is correct.
     */
    @Test
    public final void testIsBlock() {
        assertTrue("Problem in isBlock.", userList.get(0).isBlock() == null);
        assertTrue("Problem in isBlock.", userList.get(1).isBlock() == false);
        userList.get(1).setBlockStatus(true);
        assertTrue("Problem in isBlock.", userList.get(1).isBlock() == true);
        userList.get(1).setBlockStatus(false);
    }

    /**
     * Test method for {@link domain.User#blockUser()}. This test will check if
     * the BlockUser method is correct.
     */
    @Test
    public final void testBlockUser() {
        userList.get(1).blockUser();
        assertTrue("Problem in blockUser.", userList.get(1).isBlock() == true);
        userList.get(1).blockUser();
        assertTrue("Problem in blockUser.", userList.get(1).isBlock() == true);
    }

    /**
     * Test method for {@link domain.User#unblockUser()}. This test will check
     * if the UnblockUser method is correct.
     */
    @Test
    public final void testUnblockUser() {
        userList.get(1).unblockUser();
        assertTrue("Problem in unblockUser.", userList.get(1).isBlock() == false);
        userList.get(1).unblockUser();
        assertTrue("Problem in unblockUser.", userList.get(1).isBlock() == false);
    }

    /**
     * Test method for {@link domain.User#getBlockStatus()}. This test will
     * check if the getBlockStatus method is correct.
     */
    @Test
    public final void testGetBlockStatus() {
        assertTrue("Problem in getBlockStatus.", userList.get(0).getBlockStatus() == null);
        assertTrue("Problem in getBlockStatus.", userList.get(1).getBlockStatus() == false);
        userList.get(1).setBlockStatus(true);
        assertTrue("Problem in getBlockStatus.", userList.get(1).getBlockStatus() == true);
    }

    /**
     * Test method for {@link domain.User#setBlockStatus(java.lang.Boolean)}.
     * This test will check if the setBlockStatus method is correct.
     */
    @Test
    public final void testSetBlockStatus() {
        userList.get(1).setBlockStatus(false);
        assertTrue("Problem in setBlockStatus.", userList.get(1).getBlockStatus() == false);
        userList.get(1).setBlockStatus(true);
        assertTrue("Problem in setBlockStatus.", userList.get(1).getBlockStatus() == true);
    }

    /**
     * Test method for {@link domain.User#getUserPermission()}. This test will
     * check if the getUserPermission method is correct.
     */
    @Test
    public final void testGetUserPermission() {
        assertTrue("Problem in getUserPermission.", userList.get(0).getUserPermission() == null);
        assertTrue("Problem in getUserPermission.", userList.get(1).getUserPermission() == Permission.COMMON);
        userList.get(1).setUserPermission(Permission.ADMIN);
        assertTrue("Problem in getUserPermission.", userList.get(1).getUserPermission() == Permission.ADMIN);
        userList.get(1).setUserPermission(Permission.MODERATOR);
        assertTrue("Problem in getUserPermission.", userList.get(1).getUserPermission() == Permission.MODERATOR);
    }

    /**
     * Test method for {@link domain.User#setUserPermission(domain.Permission)}.
     * This test will check if the setUserPermission method is correct.
     */
    @Test
    public final void testSetUserPermission() {
        userList.get(1).setUserPermission(Permission.ADMIN);
        assertTrue("Problem in setUserPermission.", userList.get(1).getUserPermission() == Permission.ADMIN);
        userList.get(1).setUserPermission(Permission.MODERATOR);
        assertTrue("Problem in setUserPermission.", userList.get(1).getUserPermission() == Permission.MODERATOR);
        userList.get(1).setUserPermission(Permission.COMMON);
        assertTrue("Problem in setUserPermission.", userList.get(1).getUserPermission() == Permission.COMMON);
    }

    /**
     * Test method for {@link domain.User#getUserQuestions()}. This test will
     * check if the getUserQuestions method is correct.
     */
    @Test
    public final void testGetUserQuestions() {
        assertTrue("Problem in getUserQuestions.", userList.get(0).getUserQuestions() == null);
        assertTrue("Problem in getUserQuestions.", userList.get(1).getUserQuestions().isEmpty() == true);
        userList.get(1).setUserQuestions(questions);
        assertTrue("Problem in getUserQuestions.", userList.get(1).getUserQuestions().isEmpty() == false);
        assertTrue("Problem in getUserQuestions.", userList.get(1).getUserQuestions().get(2).getId() == 1);
    }

    /**
     * Test method for {@link domain.User#setUserQuestions(java.util.List)}.
     * This test will check if the setUserQuestions method is correct.
     */
    @Test
    public final void testSetUserQuestions() {
        userList.get(1).setUserQuestions(questions);
        assertTrue("Problem in setUserQuestions", userList.get(1).getUserQuestions().size() == 3);
        questions.remove(1);
        userList.get(1).setUserQuestions(questions);
        assertTrue("Problem in setUserQuestions", userList.get(1).getUserQuestions().size() == 2);
        userList.get(1).setUserQuestions(null);
        assertTrue("Problem in setUserQuestions", userList.get(1).getUserQuestions() == null);
    }

    /**
     * Test method for {@link domain.User#getUserAnswers()}. This test will
     * check if the getUserAnswers method is correct.
     */
    @Test
    public final void testGetUserAnswers() {
        assertTrue("Problem in getUserAnswers.", userList.get(0).getUserAnswers() == null);
        assertTrue("Problem in getUserAnswers.", userList.get(1).getUserAnswers().isEmpty() == true);
        userList.get(1).setUserAnswers(answers);
        assertTrue("Problem in getUserAnswers.", userList.get(1).getUserAnswers().isEmpty() == false);
        assertTrue("Problem in getUserAnswers.", userList.get(1).getUserAnswers().get(2).getId() == 2);
    }

    /**
     * Test method for {@link domain.User#setUserAnswers(java.util.List)}. This
     * test will check if the setUserAnswers method is correct.
     */
    @Test
    public final void testSetUserAnswers() {
        userList.get(1).setUserAnswers(answers);
        assertTrue("Problem in setUserAnswers", userList.get(1).getUserAnswers().size() == 3);
        answers.remove(1);
        userList.get(1).setUserAnswers(answers);
        assertTrue("Problem in setUserAnswers", userList.get(1).getUserAnswers().size() == 2);
        userList.get(1).setUserAnswers(null);
        assertTrue("Problem in setUserAnswers", userList.get(1).getUserAnswers() == null);
    }

    /**
     * Test method for {@link domain.User#getUserComments()}. This test will
     * check if the getUserComments method is correct.
     */
    @Test
    public final void testGetUserComments() {
        assertTrue("Problem in getUserComments.", userList.get(0).getUserComments() == null);
        assertTrue("Problem in getUserComments.", userList.get(1).getUserComments().isEmpty() == true);
        userList.get(1).setUserComments(comments);
        assertTrue("Problem in getUserComments.", userList.get(1).getUserComments().isEmpty() == false);
        assertTrue("Problem in getUserComments.", userList.get(1).getUserComments().get(2).getId() == 3.0);
    }

    /**
     * Test method for {@link domain.User#setUserComments(java.util.List)}. This
     * test will check if the setUserComments method is correct.
     */
    @Test
    public final void testSetUserComments() {
        userList.get(1).setUserComments(comments);
        assertTrue("Problem in setUserComments", userList.get(1).getUserComments().size() == 3);
        comments.remove(1);
        userList.get(1).setUserComments(comments);
        assertTrue("Problem in setUserComments", userList.get(1).getUserComments().size() == 2);
        userList.get(1).setUserComments(null);
        assertTrue("Problem in setUserComments", userList.get(1).getUserComments() == null);
    }

}
