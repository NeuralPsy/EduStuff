package il.neuralpsy.edustuff.exception;

public class UserEmailDoesntExistException extends RuntimeException{
    public UserEmailDoesntExistException(String message){
        super(message);
    }
}
