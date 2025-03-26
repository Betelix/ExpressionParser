package expression.generic.parser;

import expression.generic.opers.GenericExpr;
import expression.generic.types.EvaluableType;

@FunctionalInterface
public interface GenericTripleParser<S extends Number> {
    GenericExpr<S> parse(String expression, EvaluableType<S> type) throws Exception;
}