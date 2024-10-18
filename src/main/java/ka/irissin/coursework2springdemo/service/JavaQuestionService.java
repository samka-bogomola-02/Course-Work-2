package ka.irissin.coursework2springdemo.service;

import ka.irissin.coursework2springdemo.exception.QuestionNotFoundException;
import ka.irissin.coursework2springdemo.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.unmodifiableSet;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();
    @Override
    public Question add(String question, String answer){
        Question questionResult = new Question(question, answer);
        if (questions.contains(questionResult)) {
            throw new QuestionNotFoundException(question, answer);
        }
        questions.add(questionResult);
        return questionResult;
    }

    @Override
    public Question add(Question question){
        if (questions.contains(question)) {
            throw new QuestionNotFoundException(question.getQuestion(), question.getAnswer());
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer){
        Question questionResult = new Question(question, answer);
        if (!questions.contains(questionResult)) {
            throw new QuestionNotFoundException(question, answer);
        }
        questions.remove(questionResult);
        return questionResult;
    }
    @Override
    public Question remove(Question question){
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException(question.getQuestion(), question.getAnswer());
        }
        questions.remove(question);
        return question;
    }
    @Override
    public Collection<Question> getAll() {
        return unmodifiableSet(questions);
    }
    @Override
    public Question getRandomQuestion() {
        return questions.toArray(new Question[0])[random.nextInt(questions.size())];
    }


}
