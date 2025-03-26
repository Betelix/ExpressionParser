package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        CustomExpr x2 = new Multiply(new Variable("x"), new Variable("x"));
        CustomExpr mult2X = new Multiply(new Const(2), new Variable("x"));
        ExpressionParser parser = new ExpressionParser();
        TripleExpression exp = parser.parse(" \n" +
                "(\n" +
                "\n" +
                "\n" +
                "(y ** x)\n" +
                "        ** y)\n");
        System.out.println(exp.evaluate(672472756, -86938017, -963293676));
    }
}
