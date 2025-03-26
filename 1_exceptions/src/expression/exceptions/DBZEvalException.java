package expression.exceptions;

public class DBZEvalException extends EvaluationException {
    public DBZEvalException(String message) {
        super(message);
    }
    public DBZEvalException(String message, EvaluationException cause) {
        super(message, cause);
    }
}