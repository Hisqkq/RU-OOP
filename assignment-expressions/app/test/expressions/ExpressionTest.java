package expressions;

import static expressions.ExpressionFactory.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {

	private Map<String, Double> env;
	private Expression e;
	private Expression e2;

	@Before
	public void startup() {
		env = new HashMap<>();
		env.put("pi", 3.1415);
		env.put("x", 2.5);
		env.put("pumpkin", -13.0);
	}

	@Test
	public void testVarToString() {
		e = var("x");
		assertEquals("x", e.toString());
	}

	@Test
	public void testConToString() {
		e = con(13.37);
		assertEquals("13.37", e.toString());
	}

	@Test
	public void testAddToString() {
		e = add(var("pumpkin"), con(34.0));
		assertEquals("(pumpkin+34.0)", e.toString());
	}

	@Test
	public void testAddMulNegToString() {
		e = add(con(2.0), mul(neg(con(5.0)), con(8.0)));
		assertEquals("(2.0+(-5.0*8.0))", e.toString());
	}

	@Test
	public void testVarEvaluation() {
		e = var("pi");
		assertEquals(3.1415, e.eval(env), 0.00001);
	}

	@Test
	public void testConEvaluation() {
		e = con(13.37);
		assertEquals(13.37, e.eval(env), 0.00001);
	}

	@Test
	public void testAddEvaluation() {
		e = add(var("pumpkin"), con(34.0));
		assertEquals(21.0, e.eval(env), 0.00001);
	}

	@Test
	public void testAddMulNegEvaluation() {
		e = add(con(2.0), mul(neg(con(5.0)), con(8.0)));
		assertEquals(-38.0, e.eval(env), 0.00001);
	}

	@Test
	public void testConPartialEval() {
		e = con(-17.0);
		e2 = e.partialEval();
		assertEquals(e.eval(env), e2.eval(env), 0.00001);
	}

	@Test
	public void testVarPartialEval() {
		e = var("x");
		e2 = e.partialEval();
		assertEquals(e.eval(env), e2.eval(env), 0.00001);
	}

	@Test
	public void testAddPartialEval() {
		e = add(con(2.0), con(8.0));
		e2 = e.partialEval();
		assertEquals("(2.0+8.0)", e.toString());
		assertEquals("10.0", e2.toString());
	}

	@Test
	public void testAddMulConPartialEval() {
		e = add(mul(con(2.0), con(7.0)), var("x"));
		e2 = e.partialEval();
		assertEquals("((2.0*7.0)+x)", e.toString());
		assertEquals("(14.0+x)", e2.toString());
		assertEquals(e.eval(env), e2.eval(env), 0.00001);
	}

	@Test
	public void testAddZeroPartialEvalLeft() {
		e = add(con(0.0), var("pi"));
		e2 = e.partialEval();
		assertEquals("(0.0+pi)", e.toString());
		assertEquals("pi", e2.toString());
	}

	@Test
	public void testAddZeroPartialEvalRight() {
		e = add(var("pi"), con(0.0));
		e2 = e.partialEval();
		assertEquals("(pi+0.0)", e.toString());
		assertEquals("pi", e2.toString());
	}

	@Test
	public void testMulZeroPartialEvalLeft() {
		e = mul(con(0.0), var("pi"));
		e2 = e.partialEval();
		assertEquals("(0.0*pi)", e.toString());
		assertEquals("0.0", e2.toString());
	}

	@Test
	public void testMulZeroPartialEvalRight() {
		e = mul(var("pi"), con(0.0));
		e2 = e.partialEval();
		assertEquals("(pi*0.0)", e.toString());
		assertEquals("0.0", e2.toString());
	}

	@Test
	public void testNegPartialEval() {
		e = neg(con(3.0));
		e2 = e.partialEval();
		// even though the expression is simplified, their strings are the same
		assertEquals("-3.0", e.toString());
		assertEquals("-3.0", e2.toString());
	}
}