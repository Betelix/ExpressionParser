package expression.generic.types;

public class GenericDoubleType implements EvaluableType<Double> {
    
    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double greater(Double x, Double y) {
        if (x.compareTo(y) > 0) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    @Override
    public Double less(Double x, Double y) {
        if (x.compareTo(y) < 0) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    private void check(Double x, Double y) {
        if (x.equals(-0.0)) {
            x = 0.0;
        }
        if (y.equals(-0.0)) {
            y = 0.0;
        }
    }

    @Override
    public Double notEqual(Double x, Double y) {
        if (!x.equals(y)) {
            return 1.0;

        } else {
            return 0.0;
        }
    }

    @Override
    public Double equal(Double x, Double y) {
        if (x.equals(y)) {
            return 1.0;

        } else {
            return 0.0;
        }
    }

    @Override
    public Double greaterEqual(Double x, Double y) {
        if (x.compareTo(y) >= 0) {
            return 1.0;

        } else {
            return 0.0;
        }
    }

    @Override
    public Double lessEqual(Double x, Double y) {
        if (x.compareTo(y) <= 0) {
            return 1.0;

        } else {
            return 0.0;
        }
    }

    @Override
    public Double parseType(String s) {
        return Double.parseDouble(s);
    }

    @Override
    public Double intCast(int x) {
        return (double) x;
    }
}
