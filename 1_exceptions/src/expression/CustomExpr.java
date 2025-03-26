package expression;

public interface CustomExpr extends TripleExpression, Expression {
    boolean equals(Object obj);

    int getPrior(int n);
}
