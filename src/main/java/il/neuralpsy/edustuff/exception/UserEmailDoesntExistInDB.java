package il.neuralpsy.edustuff.exception;

public class UserEmailDoesntExistInDB extends RuntimeException{
    public UserEmailDoesntExistInDB(String message){
        super(message);
    }
}
