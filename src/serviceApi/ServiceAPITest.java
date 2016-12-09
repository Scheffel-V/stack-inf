/**
 * 
 */
package serviceApi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import domain.Answer;
import domain.Comment;
import domain.Question;
import domain.User;
import persistence.ContentsCRUD;
import persistence.UserCRUD;
import utils.ContentsException;
import utils.Permission;
import utils.UserException;

/**
 * @author lmrodrigues
 * @author Scheffel-V
 *
 */
public class ServiceAPITest {

    ContentsCRUD       contentsCrud;
    UserCRUD           userCrud;
    User               loggedUser;
    UserController     userController;
    ContentsController contentsController;
    Session            session;

    User               userOne;
    User               userTwo;

    Question           questionOne;
    Question           questionTwo;

    Answer             answerOne;
    Answer             answerTwo;

    Comment            commentOne;
    Comment            commentTwo;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        contentsCrud = new ContentsCRUD("TestsCRUD");
        userCrud = new UserCRUD("TestsCRUD");

        userOne = new User("user_one", "user_one", "user@one", "User One");
        userTwo = new User("user_two", "user_two", "user@Two", "User Two");

        questionOne = new Question((long) 1, userOne, "Here is the Question One Text",
                "Question One", new ArrayList<String>());

        questionTwo = new Question((long) 2, userTwo, "Here is the Question Two Text",
                "Question Two", new ArrayList<String>());

        answerOne = new Answer((long) 1, userOne, "Here is the Answer One Text");
        answerTwo = new Answer((long) 2, userTwo, "Here is the Answer Two Text");

        commentOne = new Comment((long) 1, userOne, "Here is the Comment One Text");
        commentTwo = new Comment((long) 2, userTwo, "Here is the Comment Two Text");

        session = new Session("TestsCRUD");
    }

    /**
     * Test method for {@link serviceApi.Session(java.lang.String)} .
     */
    @Test
    public final void testSession() {
        assertTrue("Problem in Session constructor.", session.getLoggedUser() == null);
        assertNotNull("Problem in Session constructor.", session.getContentsController());
        assertNotNull("Problem in Session constructor.", session.getUserController());
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#newAccount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, domain.Permission)}
     * .
     */
    @Test
    public final void testNewAccount() {
        try {
            session.newAccount("test_username", "test_password", "test_email", "test_name",
                    Permission.COMMON);
        } catch (UserException e) {
            if (e.getMessage() == "replicated.username") {
                assertTrue("The user should not be added", true);
            } else {
                e.getCause().printStackTrace();
                fail("Unexpected Error");
            }
        }

        try {
            session.newAccount("test_admin", "test_admin_pass", "test_admin_email",
                    "test_admin_name", Permission.ADMIN);
        } catch (UserException e) {
            if (e.getMessage() == "invalid.email.or.username") {
                assertTrue("The user should not be added", true);
            } else {
                fail("Unexpected Error");
            }
        }
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#login(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testLogin() {
        try {
            session.login("test_username", "test_password");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }
        session.logout();

        try {
            session.login(null, "test_password");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                }
            }
        } catch (NullPointerException e) {
            fail("Null username not verified.");
        }
        session.logout();

        try {
            session.login("test_username", null);
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                }
            }
        } catch (NullPointerException e) {
            fail("Null password not verified.");
        }
    }

    /**
     * Test method for {@link serviceApi.ServiceAPI#logout()}.
     */
    @Test
    public final void testLogout() {
        try {
            session.login("test_username", "test_password");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }
        session.logout();

        assertNull("Problem in logout.", session.getLoggedUser());
    }

    /**
     * Test method for {@link serviceApi.ServiceAPI#blockUser(java.lang.String)}
     * .
     */
    @Test
    public final void testBlockUser() {

        try {
            session.blockUser("test_username");
            fail("Problem in blockUser.");
        } catch (UserException e) {
            if (e.getMessage() != "unauthorized.unblock.action") {
                fail("Problem in blockUser.");
            }
        }

        try {
            session.unblockUser(null);
            fail("Problem in blockUser with null username.");
        } catch (UserException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            fail("Problem in blockUser with null username.");
        }

        try {
            session.login("test_admin", "test_admin_pass");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        try {
            session.blockUser("test_username");
        } catch (UserException e) {
            fail("Problem in blockUser.");
        }
        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#unblockUser(java.lang.String)}.
     */
    @Test
    public final void testUnblockUser() {
        try {
            session.unblockUser("test_username");
            fail("Problem in unblockUser.");
        } catch (UserException e) {
            if (e.getMessage() != "unauthorized.unblock.action") {
                fail("Problem in unblockUser.");
            }
        }

        try {
            session.unblockUser(null);
            fail("Problem in unblockUser with null username.");
        } catch (UserException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            fail("Problem in unblockUser with null username.");
        }

        try {
            session.login("test_admin", "test_admin_pass");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        try {
            session.unblockUser("test_username");
        } catch (UserException e) {
            fail("Problem in unblockUser.");
        }
        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#changeUserPermission(java.lang.String, domain.Permission)}
     * .
     */
    @Test
    public final void testChangeUserPermission() {

        try {
            session.changeUserPermission(null, Permission.COMMON);
            fail("Problem in changeUserPermission with null Permission.");
        } catch (UserException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            fail("Problem in changeUserPermission with null username.");
        }

        try {
            session.changeUserPermission("test_username", null);
            fail("Problem in changeUserPermission with null Permission.");
        } catch (UserException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            fail("Problem in changeUserPermission with null Permission.");
        }

        try {
            session.changeUserPermission("test_username", Permission.MODERATOR);
            fail("Problem in changeUserPermission.");
        } catch (UserException e) {
            if (e.getMessage() != "unauthorized.unblock.action") {
                fail("Problem in changeUserPermission.");
            }
        }

        try {
            session.login("test_admin", "test_admin_pass");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        try {
            session.changeUserPermission("test_username", Permission.MODERATOR);
        } catch (UserException e) {
            fail("Problem in changeUserPermission.");
        }
        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#changeUserPassword(java.lang.String)}.
     */
    @Test
    public final void testChangeUserPassword() {
        try {
            session.changeUserPassword(null);
            fail("Problem in changeUserPassword with null Password.");
        } catch (UserException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            fail("Problem in changeUserPassword with null Password.");
        }

        try {
            session.changeUserPassword("abc");
            fail("Problem in changeUserPassword.");
        } catch (UserException e) {

        }

        try {
            session.login("test_admin", "test_admin_pass");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        try {
            session.changeUserPassword("abc");
        } catch (UserException e) {
            fail("Problem in changeUserPassword.");
        }
        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#newQuestion(java.lang.String, java.util.List, java.lang.String)}
     * .
     */
    @Test
    public final void testNewQuestion() {

        try {
            session.login("test_username", "test_password");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from New Question.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from New Question.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        assertTrue("Problem in newQuestion.",
                session.getLoggedUser().getUserQuestions().size() == 2);

        try {
            session.newQuestion(null, tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from New Question.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        assertTrue("Problem in newQuestion.",
                session.getLoggedUser().getUserQuestions().size() == 2);

        try {
            session.newQuestion("Text", tags, null);
        } catch (UserException e) {
            fail("Problem in getLogged from New Question.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        assertTrue("Problem in newQuestion.",
                session.getLoggedUser().getUserQuestions().size() == 2);

        try {
            session.newQuestion("Text", null, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from New Question.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        assertTrue("Problem in newQuestion.",
                session.getLoggedUser().getUserQuestions().size() == 2);

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#newAnswer(java.lang.String, java.lang.Integer)}
     * .
     */
    @Test
    public final void testNewAnswer() {
        try {
            session.login("test_username", "test_password");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        try {
            session.newAnswer("Text", session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (UserException e) {
            fail("Problem in getLogged from New Answer.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newAnswer.");
            } else {
                fail("Problem in newAnswer.");
            }
        }

        try {
            session.newAnswer("Text", session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (UserException e) {
            fail("Problem in getLogged from New Answer.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newAnswer.");
            } else {
                fail("Problem in newAnswer.");
            }
        }

        assertTrue("Problem in newAnswer.", session.getLoggedUser().getUserAnswers().size() == 2);

        try {
            session.newAnswer(null, session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (UserException e) {
            fail("Problem in getLogged from New Answer.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newAnswer.");
            } else {
                fail("Problem in newAnswer.");
            }
        }

        assertTrue("Problem in newAnswer.", session.getLoggedUser().getUserAnswers().size() == 2);

        try {
            session.newAnswer("Text", null);
        } catch (UserException e) {
            fail("Problem in getLogged from New Answer.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newAnswer.");
            } else {
                fail("Problem in newAnswer.");
            }
        }

        assertTrue("Problem in newAnswer.", session.getLoggedUser().getUserAnswers().size() == 2);

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#newComment(java.lang.String, domain.AbstractContent)}
     * .
     */
    @Test
    public final void testNewComment() {
        try {
            session.login("test_username", "test_password");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    fail("Unexpeceted Error");
                }
            }
        }

        try {
            session.newComment("Text", session.getLoggedUser().getUserQuestions().get(0));
        } catch (UserException e) {
            fail("Problem in getLogged from New Comment.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newComment.");
            } else {
                fail("Problem in newComment.");
            }
        }

        try {
            session.newComment("Text", session.getLoggedUser().getUserQuestions().get(0));
        } catch (UserException e) {
            fail("Problem in getLogged from New Comment.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newComment.");
            } else {
                fail("Problem in newComment.");
            }
        }

        assertTrue("Problem in newComment.", session.getLoggedUser().getUserComments().size() == 2);

        try {
            session.newComment(null, session.getLoggedUser().getUserQuestions().get(0));
        } catch (UserException e) {
            fail("Problem in getLogged from New Comment.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newComment.");
            } else {
                fail("Problem in newComment.");
            }
        }

        assertTrue("Problem in newComment.", session.getLoggedUser().getUserComments().size() == 2);

        try {
            session.newComment("Text", null);
        } catch (UserException e) {
            fail("Problem in getLogged from New Comment.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newComment.");
            } else {
                fail("Problem in newComment.");
            }
        }

        assertTrue("Problem in newComment.", session.getLoggedUser().getUserComments().size() == 2);

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#editContent(domain.AbstractContent)}.
     */
    @Test
    public final void testEditContent() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#deleteContent(domain.AbstractContent)}.
     */
    @Test
    public final void testDeleteContent() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#selectQuestion(java.lang.Integer)}.
     */
    @Test
    public final void testSelectQuestion() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#searchQuestion(java.lang.String)}.
     */
    @Test
    public final void testSearchQuestion() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link serviceApi.ServiceAPI#listAllQuestions()}.
     */
    @Test
    public final void testListAllQuestions() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#bestAnswer(java.lang.Integer, java.lang.Integer)}
     * .
     */
    @Test
    public final void testBestAnswer() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#closeQuestion(java.lang.Integer)}.
     */
    @Test
    public final void testCloseQuestion() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#openQuestion(java.lang.Integer)}.
     */
    @Test
    public final void testOpenQuestion() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#upVoteAnswer(java.lang.Integer)}.
     */
    @Test
    public final void testUpVoteAnswer() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#downVoteAnswer(java.lang.Integer)}.
     */
    @Test
    public final void testDownVoteAnswer() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link serviceApi.ServiceAPI#getLoggedUser()}.
     */
    @Test
    public final void testGetLoggedUser() {
        fail("Not yet implemented"); // TODO
    }

}
