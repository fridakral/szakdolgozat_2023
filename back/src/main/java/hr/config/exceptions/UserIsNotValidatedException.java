package hr.config.exceptions;

public class UserIsNotValidatedException extends RuntimeException{

    private String message;

    public UserIsNotValidatedException() {
    }

    public UserIsNotValidatedException(String message) {
        super(message);
        this.message = message;
    }
}
