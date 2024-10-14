package ka.irissin.coursework2springdemo.service;

import ka.irissin.coursework2springdemo.exception.QuestionAmountMissMatchException;
import ka.irissin.coursework2springdemo.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = Mockito.mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void testGetQuestionsWithValidAmount() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("What is Java?", "A programming language."));
        mockQuestions.add(new Question("What is Java?", "A programming language."));

        when(questionService.getAll()).thenReturn(mockQuestions);
        when(questionService.getRandomQuestion()).thenReturn(mockQuestions.get(0), mockQuestions.get(1));

        Collection<Question> questions = examinerService.getQuestions(2);

        assertEquals(2, questions.size());
        assertTrue(questions.contains(mockQuestions.get(0)));
        assertTrue(questions.contains(mockQuestions.get(1)));
    }
    @Test
    void testGetQuestionsWithZeroAmount() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("What is Java?", "A programming language."));
        mockQuestions.add(new Question("What is Java?", "A programming language."));

        when(questionService.getAll()).thenReturn(mockQuestions);

        Collection<Question> questions = examinerService.getQuestions(0);

        assertEquals(0, questions.size());
    }
    @Test
    void testGetQuestionsWithNegativeAmount() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("What is Java?", "A programming language."));

        when(questionService.getAll()).thenReturn(mockQuestions);

        Exception exception = assertThrows(QuestionAmountMissMatchException.class, () -> {
            examinerService.getQuestions(-1);
        });

        String expectedMessage = "Incorrect amount of question";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testGetQuestionsWithTooLargeAmount() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("What is Java?", "A programming language."));

        when(questionService.getAll()).thenReturn(mockQuestions);

        Exception exception = assertThrows(QuestionAmountMissMatchException.class, () -> {
            examinerService.getQuestions(2);
        });

        String expectedMessage = "Incorrect amount of question";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testGetQuestionsWithExactAmount() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("What is Java?", "A programming language."));
        mockQuestions.add(new Question("What is Java?", "A programming language."));

        when(questionService.getAll()).thenReturn(mockQuestions);

        Collection<Question> questions = examinerService.getQuestions(2);

        assertEquals(2, questions.size());
        assertTrue(questions.contains(mockQuestions.get(0)));
        assertTrue(questions.contains(mockQuestions.get(1)));
    }
    @Test
    void testGetQuestionsWithLessThanAvailable() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("What is Java?", "A programming language."));
        mockQuestions.add(new Question("What is Java?", "A programming language."));

        when(questionService.getAll()).thenReturn(mockQuestions);

        // Mocking random question return
        when(questionService.getRandomQuestion()).thenReturn(mockQuestions.get(0), mockQuestions.get(1));

        Collection<Question> questions = examinerService.getQuestions(1);

        assertEquals(0, questions.size());
    }
}