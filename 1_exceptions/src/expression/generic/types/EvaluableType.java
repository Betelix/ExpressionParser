package expression.generic.types;

public interface EvaluableType<T extends Number> {
    public T add(T x, T y);
    public T divide(T x, T y);
    public T multiply(T x, T y);
    public T subtract(T x, T y);
    public T negate(T x);
    public T greater(T x, T y);
    public T less(T x, T y);
    public T notEqual(T x, T y);
    public T equal(T x, T y);
    public T greaterEqual(T x, T y);
    public T lessEqual(T x, T y);
    public T parseType(String s);
    public T intCast(int x);
}
