package expression;

public class Greater extends expression.AbstractBinaryOperation {
    public Greater(CustomExpr expr1, CustomExpr expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return ">";
    }

    @Override
    protected int makeOperation(int x, int y) {
        if (x > y) {
            return 1;

        } else {
            return 0;
        }

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
