package expression.exceptions;

import expression.CustomExpr;
import expression.Negate;
import expression.TripleExpression;
import expression.Variable;

public class CheckedNegate extends Negate {
    public CheckedNegate(CustomExpr exp) {
        super(exp);
    }

    @Override
    protected int makeOperation(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in Negate");
        }
        return -x;
    }

}
