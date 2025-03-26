package expression.exceptions;

public class EvaluationException extends RuntimeException {
    public EvaluationException(String message) {
        super(message);
    }
    public EvaluationException(String message, EvaluationException cause) {
        super(message, cause);
    }
}

