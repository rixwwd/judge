package com.github.rixwwd.judge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RuleParserTest {

	@Test
	public void test() throws Exception {
		RuleParser p = new RuleParser();
		Rule r = p.parse("attempt dsTransID ne \"\" or (acquirerBIN eq \"123\" and merchantID eq \"123\")");
		System.out.println(r.toString());
	}

}
