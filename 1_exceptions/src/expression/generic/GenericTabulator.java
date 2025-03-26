package expression.generic;

import expression.generic.types.*;
import expression.exceptions.EvaluationException;
import expression.generic.opers.GenericExpr;
import expression.generic.parser.GenericExpressionParser;


public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        GenericExpr<?> expr;

        switch (mode) {
            case "i" -> expr = createParser(expression, new CheckedIntType());
            case "d" -> expr = createParser(expression, new GenericDoubleType());
            case "bi" -> expr = createParser(expression, new GenericBigIntegerType());
            case "u" -> expr = createParser(expression, new UncheckedIntType());
            case "l" -> expr = createParser(expression, new GenericLongType());
            default -> throw new IllegalArgumentException("Unsupported mode: " + mode);
        }



        for (int i = 0; x1 + i <= x2; i++) {
            for (int j = 0; y1 + j <= y2; j++) {
                for (int k = 0; z1 + k <= z2; k++) {
                    try {
                        ans[i][j][k] = expr.evaluate(x1 + i, y1 + j, z1 + k);
                    } catch (EvaluationException e) {
                        ans[i][j][k] = null;
                    }

                }
            }
        }

        return ans;
    }

    private <T extends Number> GenericExpr<T> createParser(String expr, EvaluableType<T> type) throws Exception {
        GenericExpressionParser<T> parser = new GenericExpressionParser<>();
        return parser.parse(expr, type);
    }

}
