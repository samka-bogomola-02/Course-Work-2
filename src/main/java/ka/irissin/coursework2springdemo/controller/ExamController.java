package ka.irissin.coursework2springdemo.controller;

import ka.irissin.coursework2springdemo.model.Question;
import ka.irissin.coursework2springdemo.service.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public ResponseEntity<Collection<Question>> getQuestions(@PathVariable int amount) {
        try {
            Collection<Question> questions = examinerService.getQuestions(amount);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}