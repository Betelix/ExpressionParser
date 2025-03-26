package expression;

public class Negate extends AbstractUnaryPrefixOperation {

    public Negate(CustomExpr expr) {
        super(expr);
    }

    @Override
    protected String getSign() {
        return "-";
    }

    @Override
    protected int makeOperation(int x) {
        return -x;
    }

    @Override
    public int getPrior(int n) {
        return 27;
    }
}
