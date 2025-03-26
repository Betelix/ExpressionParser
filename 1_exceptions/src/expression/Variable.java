package expression;

import java.util.Map;
import java.util.Objects;

public class Variable implements CustomExpr {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable exp) {

            return this.name.equals(exp.name);
        }
        return false;
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
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {

        if (this.name.charAt(name.length() - 1) == 'x') {
            return x;
        } else if (this.name.charAt(name.length() - 1) == 'y') {
            return y;
        } else if (this.name.charAt(name.length() - 1) == 'z') {
            return z;
        } else {
            return 0;
        }
    }

}
