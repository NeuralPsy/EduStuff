package il.neuralpsy.edustuff.exception;

public class UserDoesntExistException extends RuntimeException{
    public UserDoesntExistException(String message){
        super(message);
    }
}
