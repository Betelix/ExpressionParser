package expression.generic.opers;

import expression.AbstractUnaryPrefixOperation;

import java.util.Objects;

public abstract class AbstractUnaryOperationGen<S extends Number> implements GenericExpr<S> {
    protected final GenericExpr<S> expr;

    public AbstractUnaryOperationGen(GenericExpr<S> expr) {
        this.expr = expr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, getSign());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractUnaryOperationGen<?> ob = (AbstractUnaryOperationGen<?>) obj;

        return Objects.equals(expr, ob.expr);
    }

    @Override
    public S evaluate(int x, int y, int z) {
        return makeOperation(expr.evaluate(x, y, z));
    }

    abstract String getSign();

    abstract S makeOperation(S x);
}