package expression.exceptions;

import expression.Add;
import expression.CustomExpr;

public class CheckedAdd extends Add {
    public CheckedAdd(CustomExpr exp1, CustomExpr exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int makeOperation(int x, int y) {
        if ((x > 0 && y > 0 && x > Integer.MAX_VALUE - y) || (x < 0 && y < 0 && x < Integer.MIN_VALUE - y)) {
            throw new OverflowException("overflow");
        }

        return x + y;
    }
}
