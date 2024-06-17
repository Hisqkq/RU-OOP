package expressions;

import java.util.Map;

public class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public double eval(Map<String, Double> env) {
        return env.get(name);
    }

    public Expression partialEval() {
        return this;
    }
}
