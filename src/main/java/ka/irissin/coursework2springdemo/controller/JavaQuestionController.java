package ka.irissin.coursework2springdemo.controller;

import ka.irissin.coursework2springdemo.model.Question;
import ka.irissin.coursework2springdemo.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService questionService;
    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam("question") String question,
                        @RequestParam("answer") String answer) {
        return questionService.add(question, answer);
    }
    @GetMapping("/remove")
    public Question remove(@RequestParam("question") String question,
                           @RequestParam("answer") String answer) {
        return questionService.remove(question, answer);
    }
    @GetMapping
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}
