package expression.exceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public void putBack() {
        if (pos > 0) {
            pos--;
        }
    }

    @Override
    public char getCurrent() {
        return data.charAt(pos);
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ParsingExceptions error(final String message) {
        return new ParsingExceptions(pos + ": " + message);
    }
}
