package main;

import ast.*;

public class Main {

	public static void main(String[] args) {
		Formula formula = FormulaFactory.and(
			FormulaFactory.atom("A"),
			FormulaFactory.atom("B")
		);
		
		String prettyPrinted = FormulaFactory.prettyPrint(formula);
		System.out.println("Pretty printed formula: " + prettyPrinted);
	}
}
