package expressions;

import java.util.Map;

public class Constant implements Expression {
    private final Double value;

    public Constant(Double value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }

    public double eval(Map<String, Double> env) {
        return value;
    }

    public Expression partialEval() {
        return this;
    }

    public Double getConstantValue() {
        return value;
    }
}
