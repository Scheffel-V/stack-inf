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

    User userOne;
    User userTwo;

    Question questionOne;
    Question questionTwo;

    Answer answerOne;
    Answer answerTwo;

    Comment commentOne;
    Comment commentTwo;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        contentsCrud = new ContentsCRUD("TestsCRUD");
        userCrud = new UserCRUD("TestsCRUD");

        userOne = new User("user_one", "user_one", "user@one", "User One", Permission.COMMON);
        userTwo = new User("user_two", "user_two", "user@Two", "User Two", Permission.COMMON);

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
            if (e.getMessage() == "invalid.username.or.email") {
                assertTrue("The user should not be added", true);
            } else {
                fail("Unexpected Error");
            }
        }

        try {
            session.newAccount("test_admin", "test_admin_pass", "test_admin_email",
                    "test_admin_name", Permission.ADMIN);
        } catch (UserException e) {
            if (e.getMessage() == "invalid.username.or.email") {
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
        session.logout();
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
            session.blockUser(null);
            fail("Problem in blockUser with null username.");
        } catch (UserException e1) {
            // e1.printStackTrace();
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
            session.blockUser("test_username");
        } catch (UserException e) {
            System.out.println(e.getMessage());
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
            if (e.getMessage() != "user.not.logged") {
                fail("Problem in unblockUser.");
            }
        }

        try {
            session.unblockUser(null);
            fail("Problem in unblockUser with null username.");
        } catch (UserException e1) {

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
            session.blockUser("test_username");
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
        } catch (Exception e) {
            fail("Problem in changeUserPermission with null username.");
        }

        try {
            session.changeUserPermission("test_username", null);
            fail("Problem in changeUserPermission with null Permission.");
        } catch (UserException e1) {
        } catch (Exception e) {
            fail("Problem in changeUserPermission with null Permission.");
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
            System.out.println(e.getMessage());
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

        try {
            session.changeUserPassword("test_admin_pass");
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
                assertTrue("Problem in newQuestion.", e.getMessage() == "invalid.contents");
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
                assertTrue("Problem in newQuestion.", e.getMessage() == "invalid.contents");
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
                assertTrue("Problem in newQuestion.", e.getMessage() == "invalid.contents");
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
                assertTrue("Problem in newAnswer.", e.getMessage() == "invalid.contents");
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
                assertTrue("Problem in newAnswer.", e.getMessage() == "invalid.contents");
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
                assertTrue("Problem in newComment.", e.getMessage() == "invalid.contents");
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
                assertTrue("Problem in newComments.", e.getMessage() == "invalid.contents");
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
        try {
            session.login("test_admin", "test_admin_pass");
        } catch (UserException e) {
            if (e.getMessage() == "incorrect.password") {
                fail("Problem in login or account creation.");
            } else {
                if (e.getMessage() == "blocked.user") {
                    fail("Problem in login or account creation.");
                } else {
                    System.out.println(e.getMessage());
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
            session.editContent(session.getLoggedUser().getUserQuestions().get(0));
        } catch (ContentsException e) {
            if (e.getMessage() == "not.authorized.user") {
                fail("Problem in editContent.");
            }
        } catch (UserException e) {
            if (e.getMessage() == "user.not.logged") {
                fail("Problem in editContent.");
            }
        }

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#deleteContent(domain.AbstractContent)}.
     */
    @Test
    public final void testDeleteContent() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        try {
            session.deleteContent(session.getLoggedUser().getUserQuestions().get(0));
        } catch (ContentsException e) {
            if (e.getMessage() == "invalid.content") {
                fail("Problem in deleteContent.");
            }
        } catch (UserException e) {
            if (e.getMessage() == "user.not.logged") {
                fail("Problem in deleteContent.");
            } else {
                if (e.getMessage() == "not.authorized.user") {
                    fail("Problem in deleteContent.");
                }
            }
        }
        assertTrue("Problem in deleteContent.",
                session.getLoggedUser().getUserQuestions().isEmpty() == true);

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#selectQuestion(java.lang.Integer)}.
     */
    @Test
    public final void testSelectQuestion() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        try {
            session.selectQuestion(session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in selectQuestion.");
        }

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#searchQuestion(java.lang.String)}.
     */
    @Test
    public final void testSearchQuestion() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        assertTrue("Problem in searchQuestion.",
                session.searchQuestion(session.getLoggedUser().getUserQuestions().get(0).getTitle())
                        .isEmpty() == false);

        session.logout();
    }

    /**
     * Test method for {@link serviceApi.ServiceAPI#listAllQuestions()}.
     */
    @Test
    public final void testListAllQuestions() {
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

        Integer initialSize = session.listAllQuestions().size();

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        try {
            session.newQuestion("Text Two", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }

        assertTrue("Problem in listAllQuestion.",
                session.listAllQuestions().size() == (initialSize + 2));

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#bestAnswer(java.lang.Integer, java.lang.Integer)}
     * .
     */
    @Test
    public final void testBestAnswer() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
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
            session.bestAnswer(session.getLoggedUser().getUserQuestions().get(0).getId(),
                    session.getLoggedUser().getUserAnswers().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in bestAnswer.");
        } catch (UserException e) {
            fail("Problem in bestAnswer.");
        }

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#closeQuestion(java.lang.Integer)}.
     */
    @Test
    public final void testCloseQuestion() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }
        try {
            session.closeQuestion(session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in closeQuestion.");
        } catch (UserException e) {
            fail("Problem in closeQuestion.");
        }

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#openQuestion(java.lang.Integer)}.
     */
    @Test
    public final void testOpenQuestion() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
            }
        }
        try {
            session.closeQuestion(session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in closeQuestion.");
        } catch (UserException e) {
            fail("Problem in closeQuestion.");
        }

        try {
            session.openQuestion(session.getLoggedUser().getUserQuestions().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in openQuestion.");
        } catch (UserException e) {
            fail("Problem in openQuestion.");
        }

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#upVoteAnswer(java.lang.Integer)}.
     */
    @Test
    public final void testUpVoteAnswer() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
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
            session.upVoteAnswer(session.getLoggedUser().getUserAnswers().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in upVoteAnswer.");
        } catch (UserException e) {
            fail("Problem in upVoteAnswer.");
        }

        session.logout();
    }

    /**
     * Test method for
     * {@link serviceApi.ServiceAPI#downVoteAnswer(java.lang.Integer)}.
     */
    @Test
    public final void testDownVoteAnswer() {
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

        ArrayList<String> tags = new ArrayList<String>();
        try {
            session.newQuestion("Text", tags, "Title");
        } catch (UserException e) {
            fail("Problem in getLogged from deleteContent.");
        } catch (ContentsException e) {
            if (e.getMessage() == "replicated.id") {
                fail("Problem in newQuestion.");
            } else {
                fail("Problem in newQuestion.");
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
            session.downVoteAnswer(session.getLoggedUser().getUserAnswers().get(0).getId());
        } catch (ContentsException e) {
            fail("Problem in downVoteAnswer.");
        } catch (UserException e) {
            fail("Problem in downVoteAnswer.");
        }

        session.logout();
    }

    /**
     * Test method for {@link serviceApi.ServiceAPI#getLoggedUser()}.
     */
    @Test
    public final void testGetLoggedUser() {
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
        assertTrue("Problem in getLoggedUser.",
                session.getLoggedUser().getUsername().contentEquals("test_admin"));
        assertTrue("ProbletestGetLoggedUserm in getLoggedUser.",
                session.getLoggedUser().getPassword().contentEquals("test_admin_pass"));

        session.logout();

    }
}
