package expression.exceptions;

public class ParsingExceptions extends Exception {
    public ParsingExceptions(String message) {
        super(message);
    }
    public ParsingExceptions(String message, EvaluationException cause) {
        super(message, cause);
    }
}
