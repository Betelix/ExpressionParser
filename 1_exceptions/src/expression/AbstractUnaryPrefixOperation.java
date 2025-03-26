package expression;

public abstract class AbstractUnaryPrefixOperation extends AbstractUnaryOperation {
    public AbstractUnaryPrefixOperation(CustomExpr expr) {
        super(expr);
    }

    private void addPart(StringBuilder ans, int p1, int p2, String str) {
        if (p1 > p2) {
            ans.append("(");
            ans.append(str);
            ans.append(")");
        } else {
            ans.append(" ");
            ans.append(str);
        }
    }

    public String toMiniString() {
        StringBuilder ans = new StringBuilder();

        ans.append(getSign());
        addPart(ans, this.getPrior(0), expr.getPrior(0), expr.toMiniString());

        return ans.toString();
    }

    @Override
    public String toString() {
        return getSign() + "(" + expr + ")";
    }
}
