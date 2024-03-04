package ra.springsecurityjwt.exception;

public class UsernameOrPasswordException extends Exception{
    public UsernameOrPasswordException(String message) {
        super(message);
    }
}
