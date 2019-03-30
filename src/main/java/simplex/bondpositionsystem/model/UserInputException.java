package simplex.bondpositionsystem.model;

public class UserInputException extends Exception {

    public UserInputException(String message) {
        super(message);
    }

    public UserInputException(String message, Throwable cause){
        super(message, cause);
    }

}
