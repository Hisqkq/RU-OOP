package expressions;

import java.util.Map;

public class Addition implements Expression {
    private final Expression left, right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    public double eval(Map<String, Double> env) {
        return left.eval(env) + right.eval(env);
    }

    public Expression partialEval() {
        Expression leftEval = left.partialEval();
        Expression rightEval = right.partialEval();

        Double leftVal = leftEval.getConstantValue();
        Double rightVal = rightEval.getConstantValue();

        if(leftVal != null && leftVal == 0.0){
            return rightEval;
        } else if (rightVal != null && rightVal == 0.0) {
            return leftEval;
        }
        if (leftVal != null && rightVal != null) {
            double value = leftEval.getConstantValue() + rightEval.getConstantValue();
            return new Constant(value);
        }

        return new Addition(leftEval, rightEval);
    }
}
