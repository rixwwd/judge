package com.github.rixwwd.judge;

import org.junit.Test;

public class RuleParserTest {

	@Test
	public void test1() throws Exception {
		RuleParser p = new RuleParser();
		Rule r = p.parse(
				"attempt dsTransID ne \"\" or (acquirerBIN eq \"123\" and merchantID eq \"123\") or cardholderName ne \"\"");
		System.out.println(r.toString());
	}

	@Test
	public void test2() throws Exception {
		RuleParser p = new RuleParser();
		Rule r = p.parse(
				"attempt dsTransID ne \"\" and (acquirerBIN eq \"123\" and merchantID eq \"123\") and cardholderName ne \"\"");
		System.out.println(r.toString());
	}
}
