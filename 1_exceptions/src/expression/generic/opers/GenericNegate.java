package expression.generic.opers;


import expression.generic.types.EvaluableType;

public class GenericNegate<S extends Number> extends AbstractUnaryOperationGen<S> {
    EvaluableType<S> type;

    public GenericNegate(GenericExpr<S> expr, EvaluableType<S> type) {
        super(expr);
        this.type = type;
    }

    @Override
    protected S makeOperation(S x) {
        return type.negate(x);
    }

    @Override
    String getSign() {
        return "-";
    }

    @Override
    public int getPrior(int n) {
        return 27;
    }
}
