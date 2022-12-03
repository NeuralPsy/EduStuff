package il.neuralpsy.edustuff.exception;

public class TaskDoesntExistException extends RuntimeException{
    public TaskDoesntExistException(String message){
        super(message);
    }
}
