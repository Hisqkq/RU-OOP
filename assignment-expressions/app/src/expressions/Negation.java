package expressions;

import java.util.Map;

public class Negation implements Expression {
    private final Expression expr;

    public Negation(Expression expr) {
        this.expr = expr;
    }

    public String toString() {
        return "-" + expr.toString();
    }

    public double eval(Map<String, Double> env) {
        return -1 * expr.eval(env);
    }

    public Expression partialEval() {
        Expression exprEval = expr.partialEval();

        if (exprEval.getConstantValue() != null) {
            double value = -1 * exprEval.getConstantValue();
            return new Constant(value);
        }

        return new Negation(exprEval);
    }
}
