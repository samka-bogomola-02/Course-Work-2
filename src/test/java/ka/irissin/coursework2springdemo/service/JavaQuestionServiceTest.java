package ka.irissin.coursework2springdemo.service;

import ka.irissin.coursework2springdemo.exception.QuestionNotFoundException;
import ka.irissin.coursework2springdemo.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    void testAdd_NewQuestion() {
        // Arrange
        String questionText = "What is Java?";
        String answerText = "A programming language.";

        // Act
        Question result = javaQuestionService.add(questionText, answerText);

        // Assert
        assertNotNull(result);
        assertEquals(questionText, result.getQuestion());
        assertEquals(answerText, result.getAnswer());
    }

    @Test
    void testAdd_DuplicateQuestion() {
        // Arrange
        String questionText = "What is Java?";
        String answerText = "A programming language.";
        javaQuestionService.add(questionText, answerText); // Добавляем вопрос

        // Act & Assert
        QuestionNotFoundException exception = assertThrows(QuestionNotFoundException.class, () -> {
            javaQuestionService.add(questionText, answerText); // Пытаемся добавить дубликат
        });

        assertEquals(questionText, exception.getQuestion());
        assertEquals(answerText, exception.getAnswer());
    }

    @Test
    void addQuestion() {
        Question question = new Question("What is Java?", "A programming language");
        Question addedQuestion = javaQuestionService.add(question);

        assertEquals(question, addedQuestion);
        assertTrue(javaQuestionService.getAll().contains(question));
    }

    @Test
    void testAddDuplicateQuestionThrowsException() {
        Question question = new Question("What is Java?", "A programming language");
        javaQuestionService.add(question);

        assertThrows(QuestionNotFoundException.class, () -> {
            javaQuestionService.add(question);
        });
    }

    @Test
    void testRemoveQuestionSuccessfully() {
        String questionText = "What is Java?";
        String answerText = "A programming language.";

        javaQuestionService.add(questionText, answerText);
        Question removed = javaQuestionService.remove(questionText, answerText);

        assertEquals(questionText, removed.getQuestion());
        assertEquals(answerText, removed.getAnswer());

        // Проверяем, что вопрос больше не существует
        assertThrows(QuestionNotFoundException.class, () -> {
            javaQuestionService.remove(questionText, answerText);
        });
    }

    @Test
    void testRemoveNonExistentQuestionThrowsException() {
        String questionText = "What is Java?";
        String answerText = "A programming language.";

        QuestionNotFoundException exception = assertThrows(QuestionNotFoundException.class, () -> {
            javaQuestionService.remove(questionText, answerText);
        });

        assertEquals(questionText, exception.getQuestion());
        assertEquals(answerText, exception.getAnswer());
    }

    @Test
    void testRemoveQuestionWithObject() {
        Question question = new Question("What is the capital of France?", "Paris");
        javaQuestionService.add(question);

        Question removedQuestion = javaQuestionService.remove(question);

        assertNotNull(removedQuestion);
        assertEquals(question, removedQuestion);
    }

    @Test
    void testRemoveNonExistentQuestionWithObject() {
        Question nonExistentQuestion = new Question("Question '%s' with answer '%s' not found", "Answer");

        Exception exception = assertThrows(QuestionNotFoundException.class, () -> {
            javaQuestionService.remove(nonExistentQuestion);
        });
        String expectedMessage = "Question '%s' with answer '%s' not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllQuestions() {
        String question1 = "What is Java?";
        String answer1 = "A programming language.";
        String question2 = "What is Spring?";
        String answer2 = "A framework for Java.";

        javaQuestionService.add(question1, answer1);
        javaQuestionService.add(question2, answer2);

        Collection<Question> questions = javaQuestionService.getAll();

        assertEquals(2, questions.size());
    }

    @Test
    void getRandomQuestion() {
        Question question1 = new Question("What is Java?", "A programming language");
        Question question2 = new Question("What is Spring?", "A framework");

        javaQuestionService.add(question1);
        javaQuestionService.add(question2);

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        assertTrue(randomQuestion.equals(question1) || randomQuestion.equals(question2));
    }
}