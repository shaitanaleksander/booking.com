package customException;

public class UserDosntExistException extends Exception {

    private static String exception = "cannot fond user";

    public UserDosntExistException() {
        super(exception);
    }
}
