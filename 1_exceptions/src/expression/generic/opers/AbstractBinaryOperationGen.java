package expression.generic.opers;

import expression.AbstractBinaryOperation;

import java.util.Objects;

public abstract class AbstractBinaryOperationGen<S extends Number> implements GenericExpr<S> {
    protected final GenericExpr<S> expr1;
    protected final GenericExpr<S> expr2;

    public AbstractBinaryOperationGen(GenericExpr<S> expr1, GenericExpr<S> expr2) {
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractBinaryOperationGen<?> ob = (AbstractBinaryOperationGen<?>) obj;

        return Objects.equals(expr1, ob.expr1)
                && Objects.equals(expr2, ob.expr2)
                && Objects.equals(getSign(), ob.getSign());
    }

    @Override
    public S evaluate(int x, int y, int z) {
        return makeOperation(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
    }

    abstract String getSign();

    abstract S makeOperation(S x, S y);
}
