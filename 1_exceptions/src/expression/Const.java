package expression;

import java.util.Map;
import java.util.Objects;

public class Const implements CustomExpr {
    private final long val;

    public Const(int x) {
        this.val = x;
    }

    public Const(long x) {
        this.val = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const exp) {
            return exp.val == val;
        }
        return false;
    }

    @Override
    public int getPrior(int n) {
        return 30;
    }

    @Override
    public int hashCode() {
        return Objects.hash((int) val);
    }

    @Override
    public String toString() {
        return Long.toString(val);
    }

    @Override
    public int evaluate(int x) {
        return (int) val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) val;
    }

}
