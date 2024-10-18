package ka.irissin.coursework2springdemo.service;

import ka.irissin.coursework2springdemo.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

//    Question add(String question);

    Question add(Question question);

    Question remove(String question, String answer);

//    Question remove(String question);
    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
