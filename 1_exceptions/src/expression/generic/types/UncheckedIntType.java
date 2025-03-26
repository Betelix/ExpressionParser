package expression.generic.types;

import expression.exceptions.DBZEvalException;

import java.math.BigInteger;

public class UncheckedIntType implements EvaluableType<Integer> {

    @Override
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new DBZEvalException("Division by zero");
        }
        return x / y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer negate(Integer x) {
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

    @Override
    public Integer intCast(int x) {
        return x;
    }
}
