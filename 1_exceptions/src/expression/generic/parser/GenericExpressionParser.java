package expression.generic.parser;

import expression.exceptions.*;
import expression.generic.opers.*;
import expression.generic.types.EvaluableType;


import java.util.*;

public class GenericExpressionParser<S extends Number> implements GenericTripleParser<S> {

    @Override
    public GenericExpr<S> parse(final String source, EvaluableType<S> type) throws Exception {
        return parse(new StringSource(source), type);
    }

    public GenericExpr<S> parse(final CharSource source, EvaluableType<S> type) throws ParsingExceptions {
        return new MathExperParser(source, type).parseExp();
    }

    private class MathExperParser extends BaseParser {
        EvaluableType<S> type;
        static Set<Character> op = new HashSet<>(Arrays.asList('(', '[', '{'));
        static Map<Character, Character> cp = Map.of('(', ')', '[', ']', '{', '}');
        static Set<String> oper = new HashSet<>(Arrays.asList("*", "+", "-", "/", "<", ">"));
        static Set<Character> vars = new HashSet<>(Arrays.asList('x', 'y', 'z'));
        static Set<String> dbOp = new HashSet<>(Arrays.asList("==", "!=", "<=", ">="));

        boolean beginFlag;

        public MathExperParser(final CharSource source, EvaluableType<S> type) {
            super(source);
            this.type = type;
        }

        public GenericExpr<S> parseExp() throws ParsingExceptions {
            final GenericExpr<S> result = parseElement();
            if (eof()) {
                return result;
            }
            char cur = getCurrent();
            if (cp.containsValue(cur)) {
                throw error("No opening parenthesis");
            } else if (between('0', '9')) {
                throw error("Space in number");
            } else if (cur == 'x' || cur == 'y' || cur == 'z') {
                throw error("Operator expected");
            } else {
                throw error("Unsupported symbol - " + getCurrent());
            }
        }

        private GenericExpr<S> parseElement() throws ParsingExceptions {
            beginFlag = true;
            final GenericExpr<S> result = equalCheck();
            skipWhitespace();
            return result;
        }


        private GenericExpr<S> equalCheck() throws ParsingExceptions {
            skipWhitespace();
            GenericExpr<S> left = orderRel();

            while (true) {
                skipWhitespace();
                if (take('=')) {
                    expect('=');
                    left = new GenericEquals<>(left, orderRel(), type);
                } else if (take('!')) {
                    expect('=');
                    left = new GenericNotEqual<>(left, orderRel(), type);
                } else {
                    return left;
                }
            }

        }

        private GenericExpr<S> orderRel() throws ParsingExceptions {
            skipWhitespace();
            GenericExpr<S> left = parseExpr();

            while (true) {
                skipWhitespace();
                if (take('<')) {
                    if (take('=')) {
                        left = new GenericLessEqual<>(left, parseExpr(), type);
                    } else {
                        left = new GenericLess<>(left, parseExpr(), type);
                    }
                } else if (take('>')) {
                    if (take('=')) {
                        left = new GenericGreaterEqual<>(left, parseExpr(), type);
                    } else {
                        left = new GenericGreater<>(left, parseExpr(), type);
                    }
                } else {
                    return left;
                }
            }
        }


        private GenericExpr<S> parseExpr() throws ParsingExceptions {
            skipWhitespace();
            GenericExpr<S> left = parseTerm();

            while (true) {
                skipWhitespace();
                if (take('+')) {
                    left = new GenericAdd<>(left, parseTerm(), type);
                } else if (take('-')) {
                    left = new GenericSubtract<>(left, parseTerm(), type);
                } else {
                    return left;
                }
            }

        }

        private GenericExpr<S> parseTerm() throws ParsingExceptions {
            skipWhitespace();
            GenericExpr<S> left = parsePrimary();

            while (true) {
                skipWhitespace();
                if (take('*')) {
                    left = new GenericMultiply<>(left, parsePrimary(), type);
                } else if (take('/')) {
                    left = new GenericDivide<>(left, parsePrimary(), type);
                } else {
                    return left;
                }
            }
        }


        private GenericExpr<S> parsePrimary() throws ParsingExceptions {
            skipWhitespace();
            GenericExpr<S> res;

            if (take('-')) {
                if (between('0', '9')) {
                    putBackLast();
                    res = new GenericConst<>(parseNumber());
                } else {
                    skipWhitespace();
                    if (between('0', '9') || op.contains(getCurrent()) || getCurrent() == '-' || vars.contains(getCurrent())) {
                        res = new GenericNegate<>(parsePrimary(), type
                        );
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


        private GenericExpr<S> parseValue() throws ParsingExceptions {
            GenericExpr<S> res;
            char x = getCurrent();

            if (op.contains(x)) {
                take();
                boolean tmp = beginFlag;
                res = parseElement();
                beginFlag = tmp;
                if (eof()) {
                    throw error("No closing parenthesis");
                }
                char cur = getCurrent();
                if (!take(cp.get(x))) {
                    if (oper.contains(cur + "") || between('0', '9') || vars.contains(cur) || op.contains(cur) || cp.containsValue(cur) || dbOp.contains(cur + "" + take())) {
                        throw error("Mismatched parenthesis");
                    }
                    throw error("Unsupported symbol \"" + cur + "\"");
                }
            } else if (between('0', '9')) {

                res = new GenericConst<>(parseNumber());
            } else if (between('a', 'z') || between('A', 'Z')) {
                res = parseVar();
            } else {
                String opr = takeBeforeSpace();
                skipWhitespace();
                boolean end = eof() || cp.containsValue(getCurrent());
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

                throw error("Unsupported sequence of symbols - " + opr);
            }

            return res;
        }

        private GenericExpr<S> parseVar() throws ParsingExceptions {
            char var = take();
            if (var == 'x' || var == 'y' || var == 'z') {
                return new GenericVariable<>(var + "", type);
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


        private S parseNumber() throws ParsingExceptions {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);

            try {
                return type.parseType(sb.toString());
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
