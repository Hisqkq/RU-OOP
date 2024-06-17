package visitor;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ast.Formula;

import static ast.FormulaFactory.*;

public class EvaluateVisitorTest {

	private Map<String, Boolean> env;

	@Before
	public void setUp() {
		env = new HashMap<>();
		env.put("P", true);
		env.put("Q", false);
		env.put("R", true);
		env.put("S", false);
	}

	@Test
	public void testEvalTrue() {
		Formula f = TRUE;
		assertEquals(true, evaluate(f, env));
	}

	@Test
	public void testEvalFalse() {
		Formula f = FALSE;
		assertEquals(false, evaluate(f, env));
	}

	@Test
	public void testEvalAtomP() {
		Formula f = atom("P");
		assertEquals(true, evaluate(f, env));
	}

	@Test
	public void testEvalAtomQ() {
		Formula f = atom("Q");
		assertEquals(false, evaluate(f, env));
	}

	@Test
	public void testEvalNot() {
		Formula nf = not(FALSE);
		Formula nt = not(TRUE);
		assertEquals(true, evaluate(nf, env));
		assertEquals(false, evaluate(nt, env));
	}

	@Test
	public void testEvalAnd() {
		Formula ff = and(FALSE, FALSE);
		Formula tf = and(TRUE, FALSE);
		Formula ft = and(FALSE, TRUE);
		Formula tt = and(TRUE, TRUE);
		assertEquals(false, evaluate(ff, env));
		assertEquals(false, evaluate(tf, env));
		assertEquals(false, evaluate(ft, env));
		assertEquals(true, evaluate(tt, env));
	}

	@Test
	public void testEvalOr() {
		Formula ff = or(FALSE, FALSE);
		Formula tf = or(TRUE, FALSE);
		Formula ft = or(FALSE, TRUE);
		Formula tt = or(TRUE, TRUE);
		assertEquals(false, evaluate(ff, env));
		assertEquals(true, evaluate(tf, env));
		assertEquals(true, evaluate(ft, env));
		assertEquals(true, evaluate(tt, env));
	}

	@Test
	public void testEvalImplies() {
		Formula ff = implies(FALSE, FALSE);
		Formula tf = implies(TRUE, FALSE);
		Formula ft = implies(FALSE, TRUE);
		Formula tt = implies(TRUE, TRUE);
		assertEquals(true, evaluate(ff, env));
		assertEquals(false, evaluate(tf, env));
		assertEquals(true, evaluate(ft, env));
		assertEquals(true, evaluate(tt, env));
	}

	@Test
	public void testEvalParens() {
		// (Q => R) /\ S
		Formula f1 = and(implies(atom("Q"), atom("R")), atom("S"));
		// Q => (R /\ S)
		Formula f2 = implies(atom("Q"), and(atom("R"), atom("S")));
		assertEquals(false, evaluate(f1, env));
		assertEquals(true, evaluate(f2, env));
	}
}
