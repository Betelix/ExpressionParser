package expression.exceptions;

import expression.CustomExpr;
import expression.Multiply;
import expression.TripleExpression;
import expression.Variable;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CustomExpr exp1, CustomExpr exp2) {
        super(exp1, exp2);
    }

    public static void checkMultiply(int x, int y) {
        if (x != 0 && y != 0) {
            if (x > 0) {
                if (y > 0 && x > Integer.MAX_VALUE / y) {
                    throw new OverflowException("overflow in Multiply");
                } else if (y < 0 && y < Integer.MIN_VALUE / x) {
                    throw new EvaluationException("overflow in Multiply");
                }
            } else {
                if (y > 0 && x < Integer.MIN_VALUE / y) {
                    throw new EvaluationException("overflow in Multiply");
                } else if (y < 0 && x < Integer.MAX_VALUE / y) {
                    throw new EvaluationException("overflow in Multiply");
                }
            }
        }
    }

    @Override
    protected int makeOperation(int x, int y) {
        checkMultiply(x, y);

        return x * y;
    }
}
