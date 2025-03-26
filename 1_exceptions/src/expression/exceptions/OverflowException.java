package expression.exceptions;

public class OverflowException extends EvaluationException {
    public OverflowException(String message) {
        super(message);
    }
    public OverflowException(String message, EvaluationException cause) {
        super(message, cause);
    }
}
