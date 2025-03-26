package expression;

import java.util.Objects;

public abstract class AbstractBinaryOperation implements CustomExpr {
    public final CustomExpr expr1;
    public final CustomExpr expr2;

    public AbstractBinaryOperation(CustomExpr expr1, CustomExpr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    private void addPart(StringBuilder ans, int p1, int p2, String str) {
        if (p1 > p2) {
            ans.append("(");
            ans.append(str);
            ans.append(")");
        } else {
            ans.append(str);
        }
    }

    public String toMiniString() {
        StringBuilder ans = new StringBuilder();

        addPart(ans, this.getPrior(1), expr1.getPrior(0), expr1.toMiniString());
        ans.append(" ").append(getSign()).append(" ");
        addPart(ans, this.getPrior(0), expr2.getPrior(1), expr2.toMiniString());

        return ans.toString();
    }

    @Override
    public String toString() {
        return "(" + expr1.toString() + " " + getSign() + " " + expr2.toString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr1, expr2, getSign());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomExpr exp) {
            if (this.getClass() == exp.getClass()) {
                return expr1.equals(((AbstractBinaryOperation) exp).expr1) && expr2.equals(((AbstractBinaryOperation) exp).expr2);
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    public int evaluate(int x) {
        return makeOperation(expr1.evaluate(x), expr2.evaluate(x));
    }

    @Override

    public int evaluate(int x, int y, int z) {

        return makeOperation(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
    }

    abstract String getSign();

    abstract int makeOperation(int x, int y);
}
