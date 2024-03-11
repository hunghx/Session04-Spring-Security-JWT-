package ra.springsecurityjwt.exception;

import java.util.Map;

public class DataFieldExistException extends Exception{
    private Map<String, String> fieldError;

    public DataFieldExistException(Map<String, String> fieldError) {
        this.fieldError = fieldError;
    }

    public Map<String, String> getFieldError() {
        return fieldError;
    }
}
