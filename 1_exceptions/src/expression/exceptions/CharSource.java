package expression.exceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    void putBack();
    char getCurrent();
    char next();
    ParsingExceptions error(String message);
}
