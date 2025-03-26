package expression.generic;

import expression.generic.opers.GenericExpr;
import expression.generic.parser.GenericExpressionParser;
import expression.generic.types.EvaluableType;
import expression.generic.types.GenericDoubleType;

public class Main {
    public static void main(String[] args) throws Exception {
        GenericExpressionParser<Double> parser = new GenericExpressionParser<>();
        EvaluableType<Double> type = new GenericDoubleType();
        GenericExpr<Double> expr = parser.parse("[{{1008201157 < z} <= {z / y}} < {[825873055 != x] + {z < y}}]", type);

        System.out.println(expr.evaluate(-8, -6, 0));
    }
}
