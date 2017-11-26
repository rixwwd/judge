package com.github.rixwwd.judge;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperatorTest {

	@Test
	public void eqTest1() {
		assertTrue(Operator.EQ.eval("123", "123"));
		assertFalse(Operator.EQ.eval("123", "abc"));
	}

	@Test
	public void neTest1() {
		assertTrue(Operator.NE.eval("123", "abc"));
		assertFalse(Operator.NE.eval("123", "123"));
	}

	@Test
	public void leTest1() {
		assertTrue(Operator.LE.eval("123", "123"));
		assertTrue(Operator.LE.eval("123", "1234"));
		assertFalse(Operator.LE.eval("1234", "123"));
	}
	
	@Test
	public void ltTest1() {
		assertFalse(Operator.LT.eval("123", "123"));
		assertTrue(Operator.LT.eval("123", "1234"));
		assertFalse(Operator.LT.eval("1234", "123"));
	}
	

	@Test
	public void geTest1() {
		assertTrue(Operator.GE.eval("123", "123"));
		assertFalse(Operator.GE.eval("123", "1234"));
		assertTrue(Operator.GE.eval("1234", "123"));
	}
	
	@Test
	public void gtTest1() {
		assertFalse(Operator.GT.eval("123", "123"));
		assertFalse(Operator.GT.eval("123", "1234"));
		assertTrue(Operator.GT.eval("1234", "123"));
	}
	
}
