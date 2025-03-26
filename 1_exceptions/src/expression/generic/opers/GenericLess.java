package expression.generic.opers;


import expression.generic.types.EvaluableType;

public class GenericLess<S extends Number> extends AbstractBinaryOperationGen<S> {
    EvaluableType<S> type;

    public GenericLess(GenericExpr<S> expr1, GenericExpr<S> expr2, EvaluableType<S> type) {
        super(expr1, expr2);
        this.type = type;
    }

    @Override
    protected S makeOperation(S x, S y) {
        return type.less(x, y);
    }

    @Override
    String getSign() {
        return "<";
    }

    @Override
    public int getPrior(int n) {
        if (n == 0) {
            return 5;
        } else {
            return 4;
        }
    }
}
