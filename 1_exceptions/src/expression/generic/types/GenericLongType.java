package expression.generic.types;

import expression.exceptions.DBZEvalException;

import java.math.BigInteger;

public class GenericLongType implements EvaluableType<Long> {

    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long divide(Long x, Long y) {
        if (y == 0) {
            throw new DBZEvalException("Division by zero");
        }
        return x / y;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long negate(Long x) {
        return -x;
    }

    @Override
    public Long greater(Long x, Long y) {
        if (x > y) {
            return 1L;
        } else {
            return 0L;
        }
    }

    @Override
    public Long less(Long x, Long y) {
        if (x < y) {
            return 1L;
        } else {
            return 0L;
        }
    }

    @Override
    public Long notEqual(Long x, Long y) {
        if (!x.equals(y)) {
            return 1L;

        } else {
            return 0L;
        }
    }

    @Override
    public Long equal(Long x, Long y) {
        if (x.equals(y)) {
            return 1L;

        } else {
            return 0L;
        }
    }

    @Override
    public Long greaterEqual(Long x, Long y) {
        if (x > y || x.equals(y)) {
            return 1L;

        } else {
            return 0L;
        }
    }

    @Override
    public Long lessEqual(Long x, Long y) {
        if (x < y || x.equals(y)) {
            return 1L;

        } else {
            return 0L;
        }
    }

    @Override
    public Long parseType(String s) {
        return Long.parseLong(s);
    }

    @Override
    public Long intCast(int x) {
        return (long) x;
    }
}
