package expression.exceptions;

import expression.CustomExpr;
import expression.Divide;
import expression.TripleExpression;
import expression.Variable;

public class CheckedDivide extends Divide {
    public CheckedDivide(CustomExpr exp1, CustomExpr exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int makeOperation(int x, int y) {
        if (y == 0) {
            throw new DBZEvalException("Division by zero");
        }

        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("overflow in division");
        }


        return x / y;
    }
}
