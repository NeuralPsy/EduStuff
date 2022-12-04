package il.neuralpsy.edustuff.exception;

public class CommentDoesntExistException extends RuntimeException{
    public CommentDoesntExistException(String message){
        super(message);
    }
}
