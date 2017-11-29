package com.github.rixwwd.judge;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTest {

	@Test
	public void test() throws Exception {
		Tokenizer token = new Tokenizer("reject quick acctNumber eq \"9999999999999999\" # comment   ");

		Token t1 = token.getToken();
		assertThat(t1.getTokenType(), is(TokenType.TOKEN_ACTION_REJECT));
		Token t2 = token.getToken();
		assertThat(t2.getTokenType(), is(TokenType.TOKEN_QUICK));
		Token t3 = token.getToken();
		assertThat(t3.getTokenType(), is(TokenType.TOKEN_FIELD));
		assertThat(t3.getValue(), is("acctNumber"));
		Token t4 = token.getToken();
		assertThat(t4.getTokenType(), is(TokenType.TOKEN_OP_EQ));
		Token t5 = token.getToken();
		assertThat(t5.getTokenType(), is(TokenType.TOKEN_STRING));
		assertThat(t5.getValue(), is("9999999999999999"));
		assertThat(token.getToken().getTokenType(), is(TokenType.TOKEN_END_OF_RULE));
	}

}
