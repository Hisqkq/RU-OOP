package expressions;

import java.util.HashMap;
import java.util.Map;

public interface Expression {
	public String toString();
	public double eval(Map<String, Double> env);
	public abstract Expression partialEval();

	// Return a value only if it is a Constant
	default public Double getConstantValue()
	{
		return null;
	}
}
