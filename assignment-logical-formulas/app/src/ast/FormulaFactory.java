package ast;

import java.util.Map;

public class FormulaFactory {

	public static Formula atom(String atomId) {
		return new Atom(atomId);
	}

	public static Formula and(Formula leftOp, Formula rightOp) {
		return new BinaryOperator(BinOp.AndOp, leftOp, rightOp);
	}

	public static Formula or(Formula leftOp, Formula rightOp) {
		return new BinaryOperator(BinOp.OrOp, leftOp, rightOp);
	}

	public static Formula implies(Formula leftOp, Formula rightOp) {
		return new BinaryOperator(BinOp.ImpliesOp, leftOp, rightOp);
	}

	public static Formula not(Formula notOp) {
		return new Not(notOp);
	}

	public static final Formula TRUE = Constant.TRUE; 

	public static final Formula FALSE = Constant.FALSE;

	public static String prettyPrint(Formula f) {
		PrintVisitor visitor = new PrintVisitor();
		f.accept(visitor, 0);
		return visitor.getResult();
	}

	public static Boolean evaluate(Formula f, Map<String, Boolean> env) {
		EvaluateVisitor evaluator = new EvaluateVisitor(env);
		return f.accept(evaluator, null);
	}
}
