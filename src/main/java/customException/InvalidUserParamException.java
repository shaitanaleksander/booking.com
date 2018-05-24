package customException;

public class InvalidUserParamException extends Exception {

    public InvalidUserParamException(String message) {
        super(message);
    }
}
