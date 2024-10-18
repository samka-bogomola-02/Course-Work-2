package ka.irissin.coursework2springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAmountMissMatchException extends RuntimeException {
    public QuestionAmountMissMatchException(int amount) {
        super(String.format("Incorrect amount of question", amount));
    }
}
