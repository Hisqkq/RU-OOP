package visitor;

import static org.junit.Assert.*;
import org.junit.Test;

import ast.Formula;
import static ast.FormulaFactory.*;

public class PrintVisitorTest {

	@Test
	public void testPrintAtom() {
		Formula f = atom("P");
		assertEquals("P", prettyPrint(f));
	}

	@Test
	public void testPrintTrue() {
		Formula f = TRUE;
		assertEquals("True", prettyPrint(f));
	}

	@Test
	public void testPrintFalse() {
		Formula f = FALSE;
		assertEquals("False", prettyPrint(f));
	}

	@Test
	public void testPrintNot() {
		Formula f = not(TRUE);
		assertEquals("!True", prettyPrint(f));
	}

	@Test
	public void testPrintAnd() {
		Formula f = and(TRUE, FALSE);
		assertEquals("True/\\False", prettyPrint(f));
	}

	@Test
	public void testPrintOr() {
		Formula f = or(TRUE, FALSE);
		assertEquals("True\\/False", prettyPrint(f));
	}

	@Test
	public void testPrintImplies() {
		Formula f = implies(TRUE, FALSE);
		assertEquals("True=>False", prettyPrint(f));
	}

	@Test
	public void testPrintImpliesAnd() {
		Formula f = implies(TRUE, and(atom("P"), FALSE));
		assertEquals("True=>P/\\False", prettyPrint(f));
	}

	@Test
	public void testPrintAndImplies() {
		Formula f = and(implies(TRUE, atom("P")), FALSE);
		assertEquals("(True=>P)/\\False", prettyPrint(f));
	}

	@Test
	public void testPrintAndOr() {
		Formula f = and(or(atom("Q"), atom("P")), FALSE);
		assertEquals("(Q\\/P)/\\False", prettyPrint(f));
	}

	@Test
	public void testPrintOrAnd() {
		Formula f = or(atom("Q"), and(atom("P"), FALSE));
		assertEquals("Q\\/P/\\False", prettyPrint(f));
	}

	@Test
	public void testPrintAndAnd() {
		Formula f = and(and(atom("R"), atom("Q")), and(atom("P"), FALSE));
		assertEquals("(R/\\Q)/\\(P/\\False)", prettyPrint(f));
	}

	@Test
	public void testPrintImpliesNot() {
		Formula f = implies(not(atom("P")), atom("Q"));
		assertEquals("!P=>Q", prettyPrint(f));
	}

	@Test
	public void testPrintNotImplies() {
		Formula f = not(implies(atom("P"), atom("Q")));
		assertEquals("!(P=>Q)", prettyPrint(f));
	}

	@Test
	public void testPrintBigFormula() {
		Formula f = implies(or(atom("P"), atom("S")), not(and(atom("R"), atom("Q"))));
		assertEquals("P\\/S=>!(R/\\Q)", prettyPrint(f));
	}
}
