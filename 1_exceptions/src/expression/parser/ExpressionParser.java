package expression.parser;

import expression.*;
import expression.exceptions.TripleParser;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(final String source) {
        return parse(new StringSource(source));
    }

    public static TripleExpression parse(final CharSource source) {
        return new MathExperParser(source).parseExp();
    }

    private static class MathExperParser extends BaseParser {
        public MathExperParser(final CharSource source) {
            super(source);
        }

        public CustomExpr parseExp() {
            final CustomExpr result = parseElement();
            if (eof()) {
                return result;
            }
            throw error("End of Expr expected");
        }

        private CustomExpr parseElement() {
            final CustomExpr result = parseExpr();
            skipWhitespace();
            return result;
        }

        private CustomExpr parseExpr() {
            skipWhitespace();
            CustomExpr left = parseTerm();

            while (true) {
                skipWhitespace();
                if (take('+')) {
                    left = new Add(left, parseTerm());
                } else if (take('-')) {
                    left = new Subtract(left, parseTerm());
                } else {
                    return left;
                }
            }

        }

        private CustomExpr parseTerm() {
            skipWhitespace();
            CustomExpr left = parsePrimary();

            while (true) {
                skipWhitespace();
                if (take('*')) {
                    left = new Multiply(left, parsePrimary());
                } else if (take('/')) {
                    left = new Divide(left, parsePrimary());
                } else {
                    return left;
                }
            }
        }

        private CustomExpr parsePrimary() {
            skipWhitespace();
            CustomExpr res;

            if (take('-')) {
                if (between('0', '9')) {
                    putBackLast();
                    res = new Const((int) (parseNumber()));
                } else {
                    res = new Negate(parsePrimary());
                }

            } else {
                res = parseValue();
            }


            return res;
        }


        private CustomExpr parseValue() {
            CustomExpr res;

            if (take('(')) {
                res = parseElement();
                expect(')');
            } else if (between('0', '9')) {
                res = new Const(parseNumber());
            } else if (between('a', 'z') || between('A', 'Z')) {
                res = parseVar();
            } else {
                // более разумная ошибка
                throw error("Primary expected");
            }

            return res;
        }

        private CustomExpr parseVar() {
            StringBuilder sb = new StringBuilder();

            do {
                sb.append(take());
            } while (between('a', 'z') || between('A', 'Z'));

            return new Variable(sb.toString());

        }

        private void skipWhitespace() {
            while (Character.isWhitespace(ch)) {
                take();
            }
        }

        private long parseNumber() {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);

            try {
                return Integer.parseInt(sb.toString()); // use int
            } catch (final NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private void takeInteger(final StringBuilder sb) {
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }
    }
}
