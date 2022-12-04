package il.neuralpsy.edustuff.exception;

public class SubjectDoesntExistException extends RuntimeException{
    public SubjectDoesntExistException(String message){
        super(message);
    }
}
