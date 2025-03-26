package expression;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractUnaryOperation implements CustomExpr {
    protected final CustomExpr expr;

    public AbstractUnaryOperation(CustomExpr expr) {
        this.expr = expr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, getSign());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomExpr exp) {
            if (this.getClass() == exp.getClass()) {
                return expr.equals(((AbstractUnaryPrefixOperation) exp).expr);
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    public int evaluate(int x) {
        return makeOperation(expr.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(expr.evaluate(x, y, z));
    }

    protected abstract String getSign();

    protected abstract int makeOperation(int x);
}
