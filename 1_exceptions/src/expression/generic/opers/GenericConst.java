package expression.generic.opers;

import java.util.Objects;

public class GenericConst<S extends Number> implements GenericExpr<S> {
    private final S val;

    public GenericConst(S x) {
        this.val = x;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GenericConst<?> ob = (GenericConst<?>) obj;
        return Objects.equals(val, ob.val);
    }

    @Override
    public int getPrior(int n) {
        return 30;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return val.toString();
    }


    @Override
    public S evaluate(int x, int y, int z) {
        return val;
    }
}
