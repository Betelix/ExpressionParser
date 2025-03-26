package expression;

public class NotEqual extends AbstractBinaryOperation {
    public NotEqual(CustomExpr expr1, CustomExpr expr2) {
        super(expr1, expr2);
    }

    @Override
    String getSign() {
        return "!=";
    }

    @Override
    int makeOperation(int x, int y) {
        if (x != y) {
            return 1;

        } else {
            return 0;
        }

    }

    @Override
    public int getPrior(int n) {
        if (n == 0) {
            return 3;
        } else {
            return 2;
        }

    }
}
