package expression;

public class Add extends AbstractBinaryOperation {

    public Add(CustomExpr expr1, CustomExpr expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return "+";
    }

    @Override
    protected int makeOperation(int x, int y) {
        return x + y;
    }

    @Override
    public int getPrior(int n) {
        return 11;
    }

}
