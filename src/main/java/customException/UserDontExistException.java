package customException;

public class UserDontExistException extends Exception {

    private static String exception = "cannot fond user";

    public UserDontExistException() {
        super(exception);
    }
}
