package expression.generic.types;

import expression.exceptions.DBZEvalException;

import java.math.BigInteger;

public class GenericBigIntegerType implements EvaluableType<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        if (y.compareTo(new BigInteger("0")) == 0) {
            throw new DBZEvalException("Division by zero");
        }
        return x.divide(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger greater(BigInteger x, BigInteger y) {
        if (x.compareTo(y) > 0) {
            return new BigInteger("1");
        } else {
            return new BigInteger("0");
        }
    }

    @Override
    public BigInteger less(BigInteger x, BigInteger y) {
        if (x.compareTo(y) < 0) {
            return new BigInteger("1");
        } else {
            return new BigInteger("0");
        }
    }

    @Override
    public BigInteger notEqual(BigInteger x, BigInteger y) {
        if (x.compareTo(y) != 0) {
            return new BigInteger("1");
        } else {
            return new BigInteger("0");
        }
    }

    @Override
    public BigInteger equal(BigInteger x, BigInteger y) {
        if (x.compareTo(y) == 0) {
            return new BigInteger("1");
        } else {
            return new BigInteger("0");
        }
    }

    @Override
    public BigInteger greaterEqual(BigInteger x, BigInteger y) {
        if (x.compareTo(y) >= 0) {
            return new BigInteger("1");
        } else {
            return new BigInteger("0");
        }
    }

    @Override
    public BigInteger lessEqual(BigInteger x, BigInteger y) {
        if (x.compareTo(y) <= 0) {
            return new BigInteger("1");
        } else {
            return new BigInteger("0");
        }
    }

    @Override
    public BigInteger parseType(String s) {
        return new BigInteger(s);
    }

    @Override
    public BigInteger intCast(int x) {
        return new BigInteger(Integer.toString(x));
    }
}
