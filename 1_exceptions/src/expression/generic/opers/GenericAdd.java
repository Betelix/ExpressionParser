package expression.generic.opers;


import expression.generic.types.EvaluableType;

public class GenericAdd<S extends Number> extends AbstractBinaryOperationGen<S> {
    EvaluableType<S> type;

    public GenericAdd(GenericExpr<S> expr1, GenericExpr<S> expr2, EvaluableType<S> type) {
        super(expr1, expr2);
        this.type = type;
    }

    @Override
    protected S makeOperation(S x, S y) {
        return type.add(x, y);
    }

    @Override
    String getSign() {
        return "+";
    }

    @Override
    public int getPrior(int n) {
        return 11;
    }
}
