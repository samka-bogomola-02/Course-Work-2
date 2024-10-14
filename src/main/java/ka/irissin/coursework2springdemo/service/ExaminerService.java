package ka.irissin.coursework2springdemo.service;

import ka.irissin.coursework2springdemo.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
