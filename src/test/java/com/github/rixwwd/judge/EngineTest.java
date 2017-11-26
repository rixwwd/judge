package com.github.rixwwd.judge;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class EngineTest {

	@Test
	public void rejectTest1() throws Exception {
		String str = "reject quick acctNumber eq \"9999999999999999\"";
		Engine engine = Engine.compile(str);
		Areq areq = new Areq();
		areq.setAcctNumber("9999999999999999");
		assertThat(engine.eval(areq), is(Action.REJECT));
	}

	@Test
	public void rejectTest2() throws Exception {
		String str = "reject acctNumber eq \"9999999999999999\"\n"
				+ "challenge acquirerBIN eq \"test\"";
		Engine engine = Engine.compile(str);
		Areq areq = new Areq();
		areq.setAcctNumber("9999999999999999");
		assertThat(engine.eval(areq), is(Action.REJECT));
	}
	
	@Test
	public void rejectTest3() throws Exception {
		String str = "reject acctNumber eq \"9999999999999999\"\n"
				+ "challenge acquirerBIN eq \"test\"";
		Engine engine = Engine.compile(str);
		Areq areq = new Areq();
		areq.setAcctNumber("9999999999999999");
		areq.setAcquirerBIN("test");
		assertThat(engine.eval(areq), is(Action.CHALLENGE));
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void syntaxErrorTest1() throws Exception {
		String str = "reject acctNumber eq \"9999999999999999\"\n"
				+ "challenge acquirerBIN add \"test\"";
		Engine.compile(str);
	}
}