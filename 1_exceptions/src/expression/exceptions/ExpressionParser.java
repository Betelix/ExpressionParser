package expression.exceptions;

import expression.*;

import java.util.*;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(final String source) throws Exception {
        return parse(new StringSource(source));
    }

    public static TripleExpression parse(final CharSource source) throws ParsingExceptions {
        return new MathExperParser(source).parseExp();
    }

    private static class MathExperParser extends BaseParser {
        static Map<Character, Character> parens = Map.of('(', ')', '[', ']', '{', '}');
        static Set<String> oper = new HashSet<>(Arrays.asList("*", "+", "-", "/", "<", ">"));
        static Set<Character> vars = new HashSet<>(Arrays.asList('x', 'y', 'z'));
        static Set<String> dbOp = new HashSet<>(Arrays.asList("==", "!=", "<=", ">="));

        boolean beginFlag;
        public MathExperParser(final CharSource source) {
            super(source);

        }

        public CustomExpr parseExp() throws ParsingExceptions {
            final CustomExpr result = parseElement();
            if (eof()) {
                return result;
            }
            char cur = getCurrent();
            if (parens.containsValue(cur)) {
                throw error("No opening parenthesis");
            } else if (between('0', '9')){
                throw error("Space in number");
            } else if (cur == 'x' || cur == 'y' || cur == 'z') {
                throw error("Operator expected");
            } else {
                throw error("Unsupported sequence of symbols \"" + takeBeforeSpace() + "\"");
            }
        }

        private CustomExpr parseElement() throws ParsingExceptions {
            beginFlag = true;
            final CustomExpr result = parseLayer(0);
            skipWhitespace();
            return result;
        }

        private CustomExpr parseLayer(int n) throws ParsingExceptions {
            skipWhitespace();
            if (n == 4) {
                return parsePrimary();
            }
            CustomExpr left = parseLayer(n + 1);

            while (true) {
                skipWhitespace();
                if (n == 0) {
                    if (take('=')) {
                        expect('=');
                        left = new Equal(left, parseLayer(n + 1));
                    } else if (take('!')) {
                        expect('=');
                        left = new NotEqual(left, parseLayer(n + 1));
                    } else {
                        return left;
                    }
                } else if (n == 1) {
                    if (take('<')) {
                        if (take('=')) {
                            left = new LessEqual(left, parseLayer(n + 1));
                        } else {
                            left = new Less(left, parseLayer(n + 1));
                        }
                    } else if (take('>')) {
                        if (take('=')) {
                            left = new GreaterEqual(left, parseLayer(n + 1));
                        } else {
                            left = new Greater(left, parseLayer(n + 1));
                        }
                    } else {
                        return left;
                    }
                } else if (n == 2) {
                    if (take('+')) {
                        left = new CheckedAdd(left, parseLayer(n + 1));
                    } else if (take('-')) {
                        left = new CheckedSubtract(left, parseLayer(n + 1));
                    } else {
                        return left;
                    }
                } else if (n == 3) {
                    if (take('*')) {
                        left = new CheckedMultiply(left, parsePrimary());
                    } else if (take('/')) {
                        left = new CheckedDivide(left, parsePrimary());
                    } else {
                        return left;
                    }
                }
            }
        }

        private CustomExpr parsePrimary() throws ParsingExceptions {
            skipWhitespace();
            CustomExpr res;

            if (take('-')) {
                if (between('0', '9')) {
                    putBackLast();
                    res = new Const((int) (parseNumber()));
                } else {
                    skipWhitespace();
                    if (between('0', '9') || parens.containsKey(getCurrent()) || getCurrent() == '-' || vars.contains(getCurrent())) {
                        res = new CheckedNegate(parsePrimary());
                    } else {
                        throw error("Bare -");
                    }
                }

            } else {
                res = parseValue();
            }

            beginFlag = false;
            return res;
        }


        private CustomExpr parseValue() throws ParsingExceptions {
            CustomExpr res;
            char x = getCurrent();

            if (parens.containsKey(x)) {
                take();
                boolean tmp = beginFlag;
                res = parseElement();
                beginFlag = tmp;
                if (eof()) {
                    throw error("No closing parenthesis");
                }
                char cur = getCurrent();
                if (!take(parens.get(x))) {
                    if (oper.contains(cur + "") || between('0', '9') || vars.contains(cur) || parens.containsKey(cur) || parens.containsValue(cur) || dbOp.contains(cur + "" + take())) {
                        throw error("Mismatched parenthesis");
                    }
                    throw error("Unsupported sequence of symbols \"" + cur + takeBeforeSpace() +"\"");
                }
            } else if (between('0', '9')) {

                res = new Const(parseNumber());
            } else if (between('a', 'z') || between('A', 'Z')) {
                res = parseVar();
            } else {
                char cur = getCurrent();
                String opr = takeBeforeSpace();
                skipWhitespace();
                boolean end = eof() || parens.containsValue(getCurrent()) || parens.containsValue(cur);
                if (oper.contains(opr) || dbOp.contains(opr)) {
                    if (beginFlag) {
                        if (end) {
                            throw error("Bare " + opr);
                        } else {
                            throw error("No first argument");
                        }
                    } else {
                        throw error("No middle argument");
                    }
                } else if (end) {
                    if (beginFlag) {
                        throw error("Empty parenthesis or empty input");
                    } else {
                        throw error("No last argument");
                    }
                }

                throw error("Unsupported sequence of symbols \"" + opr + "\"");
            }

            return res;
        }

        private CustomExpr parseVar() throws ParsingExceptions {
            char var = take();
            if (var == 'x' || var == 'y' || var == 'z') {
                return new Variable(var + "");
            }
            throw new ParsingExceptions("Invalid var name");

        }

        private void skipWhitespace() {
            while (Character.isWhitespace(getCurrent())) {
                take();
            }
        }

        private String takeBeforeSpace() {
            StringBuilder sb = new StringBuilder();
            while (!(Character.isWhitespace(getCurrent()) || eof())) {
                sb.append(getCurrent());
                take();
            }

            return sb.toString();
        }


        private long parseNumber() throws ParsingExceptions {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);

            try {
                return Integer.parseInt(sb.toString()); // use int
            } catch (final NumberFormatException e) {
                if (sb.charAt(0) == '-') {
                    throw error("Constant overflow 1");
                } else {
                    throw error("Constant overflow 2");
                }

            }
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private void takeInteger(final StringBuilder sb) throws ParsingExceptions {
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
