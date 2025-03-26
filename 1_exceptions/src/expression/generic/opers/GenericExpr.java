package expression.generic.opers;

import expression.ToMiniString;

public interface GenericExpr<T extends Number> extends ToMiniString {
    T evaluate(int x, int y, int z);

    boolean equals(Object obj);

    int getPrior(int n);
}
