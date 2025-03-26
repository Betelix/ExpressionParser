package expression.exceptions;

public class Main {
    public static void main(String[] ar) {
        ExpressionParser ex = new ExpressionParser();
        try {
            ex.parse("1 + 3");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
