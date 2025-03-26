package expression;

public class Divide extends AbstractBinaryOperation {

    public Divide(CustomExpr expr1, CustomExpr expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return "/";
    }

    @Override
    protected int makeOperation(int x, int y) {
        return x / y;
    }

    @Override
    public int getPrior(int n) {
        if (n == 0) {
            return 22;
        } else {
            return 20;
        }
    }
}
