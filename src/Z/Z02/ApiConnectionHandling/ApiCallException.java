package Z.Z02.ApiConnectionHandling;

public class ApiCallException extends Exception {
    public ApiCallException(Exception e) {
        super(e);
    }
    public ApiCallException(String message) {
        super(message);
    }
}
