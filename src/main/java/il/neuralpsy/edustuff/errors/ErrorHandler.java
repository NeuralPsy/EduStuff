package il.neuralpsy.edustuff.errors;

import il.neuralpsy.edustuff.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(final RuntimeException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({
            SubjectDoesntExistException.class,
            TaskDoesntExistException.class,
            UserDoesntExistException.class,
            UserEmailDoesntExistException.class,
            TaskDoesntExistException.class,
            CommentDoesntExistException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(final RuntimeException e){
        return new ErrorResponse(e.getMessage());
    }

}
