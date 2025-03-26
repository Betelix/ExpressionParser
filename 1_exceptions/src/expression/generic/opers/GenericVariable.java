package expression.generic.opers;


import expression.generic.types.EvaluableType;

import java.util.Objects;

public class GenericVariable<S extends Number> implements GenericExpr<S> {
    private final String name;
    EvaluableType<S> type;

    public GenericVariable(String name, EvaluableType<S> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GenericVariable<?> ob = (GenericVariable<?>) obj;


        return Objects.equals(name, ob.name);
    }

    @Override
    public int getPrior(int n) {
        return 30;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public S evaluate(int x, int y, int z) {

        if (this.name.charAt(name.length() - 1) == 'x') {
            return type.intCast(x);
        } else if (this.name.charAt(name.length() - 1) == 'y') {
            return type.intCast(y);
        } else {
            return type.intCast(z);
        }
    }
}
