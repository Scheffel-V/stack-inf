/**
 * 
 */
package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lmrodrigues
 * @author Scheffel-V
 *
 */
public class QuestionTest {

    ArrayList<Question> questionList;
    List<String>        tags;
    User                vinicius, sujeito;
    ArrayList<Answer>   answerList;
    Answer              answer, answer_2;
    ArrayList<Comment>  commentList;
    Comment             comment, comment_2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        tags = null;
        vinicius = new User("vbscheffel", "123abc", "brsufirefox@gmail.com", "Vinicius");
        sujeito = new User("sujeitousername", "123567", "sujeito.legal@gmail.com", "Sujeito");
        answer = new Answer(10, sujeito, "Text");
        answer_2 = new Answer(12, sujeito, "Text");
        answerList = new ArrayList<Answer>();
        answerList.add(answer);
        answerList.add(answer_2);
        comment = new Comment(11, sujeito, "Comment text");
        comment_2 = new Comment(13, sujeito, "Comment text");
        commentList = new ArrayList<Comment>();
        commentList.add(comment);
        commentList.add(comment_2);
        questionList = new ArrayList<Question>();
        questionList.add(new Question()); // 0 - Question null
        questionList.add(new Question(1, vinicius, "Text", "Title", tags)); // 1
                                                                            // -
                                                                            // Question
                                                                            // one
        questionList.add(new Question(2, sujeito, "Text Two", "Title Two", tags)); // 2
                                                                                   // -
                                                                                   // Question
                                                                                   // two
    }

    /**
     * Test method for {@link domain.Question#Question()}. This test will check
     * if the argumentless construct is correct.
     */
    @Test
    public final void testQuestion() {

        assertNotNull("Question argumentless constructor gives null object.", questionList.get(0));
        assertTrue("Title field of argumentless Question construct is not null.",
                questionList.get(0).getTitle() == null);
        assertTrue("Tags field of argumentless Question construct is not null.", questionList.get(0).getTags() == null);
        assertTrue("Answers field of argumentless Question construct is not null.",
                questionList.get(0).getAnswers() == null);
        assertTrue("Comments field of argumentless Question construct is not null.",
                questionList.get(0).getComments() == null);
        assertTrue("Best Answers field of argumentless Question construct is not null.",
                questionList.get(0).getBestAnswer() == null);
        assertTrue("Status field of argumentless Question construct is not null.",
                questionList.get(0).getStatus() == null);
    }

    /**
     * Test method for
     * {@link domain.Question#Question(java.lang.Integer, domain.User, java.lang.String, java.lang.String, java.util.List)}
     * . . This test will check if the construct with arguments is correct.
     */
    @Test
    public final void testQuestionWithArguments() {
        assertNotNull("Question constructor with arguments gives null object.", questionList.get(1));
        assertTrue("Problem in ID field of construct with arguments.", questionList.get(1).getId() == 1);
        assertTrue("Problem in author field of construct with arguments.", questionList.get(1).getAuthor() == vinicius);
        assertTrue("Problem in text field of construct with arguments.", questionList.get(1).getText() == "Text");
        assertTrue("Problem in title field of construct with arguments.", questionList.get(1).getTitle() == "Title");
        assertTrue("Problem in tags field of construct with arguments.", questionList.get(1).getTags() == tags);
        assertEquals("Problem in Answers list field from construct with arguments.", new ArrayList<Answer>(),
                questionList.get(1).getAnswers());
        assertEquals("Problem in Comments list field from construct with arguments.", new ArrayList<Comment>(),
                questionList.get(1).getComments());
        assertNull("bestAnswer field of construct with arguments is not null.", questionList.get(1).getBestAnswer());
        assertSame("Problem in status field from construct with arguments.", Status.OPEN,
                questionList.get(1).getStatus());
    }

    /**
     * Test method for {@link domain.Answer#getId()}. This test will check the
     * getId method.
     */
    @Test
    public final void testGetId() {

        assertTrue("Problem in getId of Answer construct.", questionList.get(0).getId() == null);
        assertTrue("Problem in getId of Answer construct.", questionList.get(1).getId() == 1);
    }

    /**
     * Test method for {@link domain.Answer#setId()}. This test will check the
     * setId method.
     */
    @Test
    public final void testSetId() {

        questionList.get(1).setId(2);
        assertTrue("Problem in setId of Answer construct.", questionList.get(1).getId() == 2);
        questionList.get(1).setId(3);
        assertTrue("Problem in setId of Answer construct.", questionList.get(1).getId() == 3);
    }

    /**
     * Test method for {@link domain.Answer#getDate()}. This test will check the
     * getDate method.
     */
    @Test
    public final void testGetDate() {
        Date date = new Date();
        questionList.get(1).setDate(date);
        assertTrue("Problem in getDate of Answer construct.", questionList.get(1).getDate() == date);
    }

    /**
     * Test method for {@link domain.Answer#setDate()}. This test will check the
     * setDate method.
     */
    @Test
    public final void testSetDate() {
        Date date_1 = new Date();
        Date date_2 = new Date();
        questionList.get(1).setDate(date_1);
        assertTrue("Problem in setDate of Answer construct.", questionList.get(1).getDate() == date_1);
        questionList.get(1).setDate(date_2);
        assertTrue("Problem in setDate of Answer construct.", questionList.get(1).getDate() == date_2);
    }

    /**
     * Test method for {@link domain.Answer#setAuthor()}. This test will check
     * the setAuthor method.
     */
    @Test
    public final void testSetAuthor() {

        questionList.get(1).setAuthor(vinicius);
        assertTrue("Problem in setAuthor of Answer construct.", questionList.get(1).getAuthor() == vinicius);
        User user_1 = new User();
        questionList.get(1).setAuthor(user_1);
        assertTrue("Problem in setAuthor of Answer construct.", questionList.get(1).getAuthor() == user_1);
    }

    /**
     * Test method for {@link domain.Answer#getAuthor()}. This test will check
     * the getAuthor method.
     */
    @Test
    public final void testGetAuthor() {

        assertTrue("Problem in getAuthor of Answer construct.", questionList.get(1).getAuthor() == vinicius);
        User user_1 = new User();
        questionList.get(1).setAuthor(user_1);
        assertTrue("Problem in getAuthor of Answer construct.", questionList.get(1).getAuthor() == user_1);
    }

    /**
     * Test method for {@link domain.Answer#getText()}. This test will check the
     * getText method.
     */
    @Test
    public final void testGetText() {

        assertTrue("Problem in getText of Answer construct.", questionList.get(1).getText() == "Text");
        questionList.get(1).setText("Text Test");
        assertTrue("Problem in getText of Answer construct.", questionList.get(1).getText() == "Text Test");
    }

    /**
     * Test method for {@link domain.Answer#setText()}. This test will check the
     * setText method.
     */
    @Test
    public final void testSetText() {

        questionList.get(1).setText("Text");
        assertTrue("Problem in setText of Answer construct.", questionList.get(1).getText() == "Text");
        questionList.get(1).setText("Text Test");
        assertTrue("Problem in setText of Answer construct.", questionList.get(1).getText() == "Text Test");
    }

    /**
     * Test method for {@link domain.Question#getTitle()}. This test will check
     * the getTitle method.
     */
    @Test
    public final void testGetTitle() {
        assertTrue("Problem in getTitle.", questionList.get(0).getTitle() == null);
        assertTrue("Problem in getTitle.", questionList.get(1).getTitle() == "Title");
    }

    /**
     * Test method for {@link domain.Question#setTitle(java.lang.String)}. This
     * test will check the setTitle method.
     */
    @Test
    public final void testSetTitle() {
        questionList.get(1).setTitle("Test Title");
        assertTrue("Probme in setTitle.", questionList.get(1).getTitle() == "Test Title");
    }

    /**
     * Test method for {@link domain.Question#getTags()}. This test will check
     * the getTags method.
     */
    @Test
    public final void testGetTags() {
        assertTrue("Problem in getTags.", questionList.get(0).getTags() == null);
        assertTrue("Problem in getTags.", questionList.get(1).getTags() == tags);
    }

    /**
     * Test method for {@link domain.Question#setTags(java.util.List)}. This
     * test will check the setTags method.
     */
    @Test
    public final void testSetTags() {
        questionList.get(1).setTags(null);
        assertTrue("Problem in setTags.", questionList.get(1).getTags() == null);
        questionList.get(1).setTags(tags);
        assertTrue("Problem in setTags.", questionList.get(1).getTags() == tags);
    }

    /**
     * Test method for {@link domain.Question#getAnswers()}. This test will
     * check the getAnswers method.
     */
    @Test
    public final void testGetAnswers() {
        assertTrue("Problem in getAnswers.", questionList.get(0).getAnswers() == null);
        assertEquals("Problem in getAnswers.", questionList.get(1).getAnswers(), new ArrayList<Answer>());
        questionList.get(1).setAnswers(answerList);
        assertTrue("Problem in getAnswers.", questionList.get(1).getAnswers().get(0).getId() == 10);
        assertTrue("Problem in getAnswers.", questionList.get(1).getAnswers().get(1).getId() == 12);
    }

    /**
     * Test method for {@link domain.Question#setAnswers(java.util.List)}. This
     * test will check the setAnswer method.
     */
    @Test
    public final void testSetAnswers() {
        questionList.get(1).setAnswers(answerList);
        assertTrue("Problem in setAnswers.", questionList.get(1).getAnswers().get(0).getId() == 10);
        assertTrue("Problem in setAnswers.", questionList.get(1).getAnswers().get(1).getId() == 12);
        questionList.get(1).setAnswers(null);
        assertTrue("Problem in setAnswers.", questionList.get(1).getAnswers() == null);
    }

    /**
     * Test method for {@link domain.Question#addAnswer(domain.Answer)}. This
     * test will check the addAnswer method.
     */
    @Test
    public final void testAddAnswer() {
        questionList.get(2).addAnswer(answer_2);
        assertTrue("Problem in addAnswer.", questionList.get(2).getAnswers().get(0).getId() == 12);
        questionList.get(2).addAnswer(answer);
        assertTrue("Problem in addAnswer.", questionList.get(2).getAnswers().get(0).getId() == 12);
        assertTrue("Problem in addAnswer.", questionList.get(2).getAnswers().get(1).getId() == 10);
    }

    /**
     * Test method for {@link domain.Question#delAnswer(domain.Answer)}. This
     * test will check the delAnswer method.
     */
    @Test
    public final void testDelAnswer() {
        questionList.get(1).setAnswers(answerList);
        questionList.get(1).delAnswer(answer_2);
        assertTrue("Problem in delAnswer.", questionList.get(1).getAnswers().get(0).getId() == 10);
        questionList.get(1).delAnswer(answer);
        assertTrue("Problem in delAnswer.", questionList.get(1).getAnswers().isEmpty() == true);
        questionList.get(1).delAnswer(answer);
        assertTrue("Problem in delAnswer.", questionList.get(1).getAnswers().isEmpty() == true);
        questionList.get(1).addAnswer(answer);
        questionList.get(1).delAnswer(answer_2);
        assertTrue("Problem in delAnswer.", questionList.get(1).getAnswers().get(0).getId() == 10);
    }

    /**
     * Test method for {@link domain.Question#getComments()}. This test will
     * check getComments the method.
     */
    @Test
    public final void testGetComments() {
        assertTrue("Problem in getComments.", questionList.get(0).getComments() == null);
        assertEquals("Problem in getComments.", questionList.get(1).getComments(), new ArrayList<Comment>());
        questionList.get(1).setComments(commentList);
        assertTrue("Problem in getComments.", questionList.get(1).getComments().get(0).getId() == 11);
        assertTrue("Problem in getComments.", questionList.get(1).getComments().get(1).getId() == 13);
    }

    /**
     * Test method for {@link domain.Question#setComments(java.util.List)}. This
     * test will check the setComments method.
     */
    @Test
    public final void testSetComments() {
        questionList.get(1).setComments(commentList);
        assertTrue("Problem in setComments.", questionList.get(1).getComments().get(0).getId() == 11);
        assertTrue("Problem in setComments.", questionList.get(1).getComments().get(1).getId() == 13);
        questionList.get(1).setComments(null);
        assertTrue("Problem in setComments.", questionList.get(1).getComments() == null);
    }

    /**
     * Test method for {@link domain.Question#addComment(domain.Comment)}. This
     * test will check the addComment method.
     */
    @Test
    public final void testAddComment() {
        questionList.get(2).addComment(comment_2);
        assertTrue("Problem in addComment.", questionList.get(2).getComments().get(0).getId() == 13);
        questionList.get(2).addComment(comment);
        assertTrue("Problem in addComment.", questionList.get(2).getComments().get(0).getId() == 13);
        assertTrue("Problem in addComment.", questionList.get(2).getComments().get(1).getId() == 11);
    }

    /**
     * Test method for {@link domain.Question#delComment(domain.Comment)}. This
     * test will check the delComment method.
     */
    @Test
    public final void testDelComment() {
        questionList.get(1).setComments(commentList);
        questionList.get(1).delComment(comment_2);
        assertTrue("Problem in delComment.", questionList.get(1).getComments().get(0).getId() == 11);
        questionList.get(1).delComment(comment);
        assertTrue("Problem in delComment.", questionList.get(1).getComments().isEmpty() == true);
        questionList.get(1).delComment(comment);
        assertTrue("Problem in delComment.", questionList.get(1).getComments().isEmpty() == true);
        questionList.get(1).addComment(comment);
        questionList.get(1).delComment(comment_2);
        assertTrue("Problem in delComment.", questionList.get(1).getComments().get(0).getId() == 11);
    }

    /**
     * Test method for {@link domain.Question#getBestAnswer()}. This test will
     * check the getBestAnswer method.
     */
    @Test
    public final void testGetBestAnswer() {
        assertTrue("Problem in getBestAnswer.", questionList.get(0).getBestAnswer() == null);
        assertTrue("Problem in getBestAnswer.", questionList.get(1).getBestAnswer() == null);
        questionList.get(1).setBestAnswer(answer);
        assertTrue("Problem in getBestAnswer.", questionList.get(1).getBestAnswer().getId() == 10);
    }

    /**
     * Test method for {@link domain.Question#setBestAnswer(domain.Answer)}.
     * This test will check the setBestAnswer method.
     */
    @Test
    public final void testSetBestAnswer() {
        questionList.get(1).setBestAnswer(answer);
        assertTrue("Problem in setBestAnswer.", questionList.get(1).getBestAnswer().getId() == 10);
        questionList.get(1).setBestAnswer(null);
        assertTrue("Problem in setBestAnswer.", questionList.get(1).getBestAnswer() == null);
    }

    /**
     * Test method for {@link domain.Question#getStatus()}. This test will check
     * the getStatus method.
     */
    @Test
    public final void testGetStatus() {
        assertTrue("Problem in getStatus method.", questionList.get(0).getStatus() == null);
        assertTrue("Problem in getStatus method.", questionList.get(1).getStatus() == Status.OPEN);
        questionList.get(1).setStatus(Status.CLOSED);
        assertTrue("Problem in getStatus method.", questionList.get(1).getStatus() == Status.CLOSED);
    }

    /**
     * Test method for {@link domain.Question#setStatus(domain.Status)}. This
     * test will check the setStatus method.
     */
    @Test
    public final void testSetStatus() {
        questionList.get(1).setStatus(Status.OPEN);
        assertTrue("Problem in setStatus method.", questionList.get(1).getStatus() == Status.OPEN);
        questionList.get(1).setStatus(Status.CLOSED);
        assertTrue("Problem in setStatus method.", questionList.get(1).getStatus() == Status.CLOSED);
    }

    /**
     * Test method for {@link domain.Question#openQuestion()}. This test will
     * check the openQuestion method.
     */
    @Test
    public final void testOpenQuestion() {
        questionList.get(1).openQuestion();
        assertTrue("Problem in openQuestion method.", questionList.get(1).getStatus() == Status.OPEN);
        questionList.get(1).openQuestion();
        assertTrue("Problem in openQuestion method.", questionList.get(1).getStatus() == Status.OPEN);
        questionList.get(1).closeQuestion();
        questionList.get(1).openQuestion();
        assertTrue("Problem in openQuestion method.", questionList.get(1).getStatus() == Status.OPEN);
    }

    /**
     * Test method for {@link domain.Question#closeQuestion()}. This test will
     * check the closeQuestion method.
     */
    @Test
    public final void testCloseQuestion() {
        questionList.get(1).closeQuestion();
        assertTrue("Problem in closeQuestion method.", questionList.get(1).getStatus() == Status.CLOSED);
        questionList.get(1).closeQuestion();
        assertTrue("Problem in closeQuestion method.", questionList.get(1).getStatus() == Status.CLOSED);
        questionList.get(1).openQuestion();
        questionList.get(1).closeQuestion();
        assertTrue("Problem in closeQuestion method.", questionList.get(1).getStatus() == Status.CLOSED);
    }

}
