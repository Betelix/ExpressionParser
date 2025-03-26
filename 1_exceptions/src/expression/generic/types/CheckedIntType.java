package expression.generic.types;

import expression.exceptions.DBZEvalException;
import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;

public class CheckedIntType implements EvaluableType<Integer> {

    @Override
    public Integer intCast(int x) {
        return (Integer) x;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        if ((x > 0 && y > 0 && x + y < 0) || (x < 0 && y < 0 && x + y >= 0 )) {
            throw new OverflowException("overflow");
        }
        return x + y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new DBZEvalException("Division by zero");
        }

        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("overflow in division");
        }


        return x / y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
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

        return x * y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if ((x >= 0 && y < 0 && x - y < 0) || (x < 0 && y > 0 && x - y >= 0 )) {
            throw new EvaluationException("overflow in subtract");
        }
        return x - y;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in Negate");
        }
        return -x;
    }

    @Override
    public Integer greater(Integer x, Integer y) {
        if (x > y) {
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public Integer less(Integer x, Integer y) {
        if (x < y) {
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public Integer notEqual(Integer x, Integer y) {
        if (!x.equals(y)) {
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public Integer equal(Integer x, Integer y) {
        if (x.equals(y)) {
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public Integer greaterEqual(Integer x, Integer y) {
        if (x > y || x.equals(y)) {
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public Integer lessEqual(Integer x, Integer y) {
        if (x < y || x.equals(y)) {
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public Integer parseType(String s) {
        return Integer.parseInt(s);
    }
}
