package com.github.rixwwd.judge;

public enum TokenType {

	//@formatter:off
	TOKEN_ACTION_REJECT("reject"),
	TOKEN_ACTION_CHALLENGE("challenge"),
	TOKEN_ACTION_AUTHENTICATED("authenticated"),
	TOKEN_ACTION_CANCEL("cancel"),
	TOKEN_ACTION_UNAVAILABLE("unavailable"),
	TOKEN_ACTON_ATTEMPT("attempt"),
	TOKEN_QUICK("quick"),
	TOKEN_FIELD(null),
	TOKEN_OP_EQ("eq"),
	TOKEN_OP_NE("ne"),
	TOKEN_OP_LE("le"),
	TOKEN_OP_LT("lt"),
	TOKEN_OP_GE("ge"),
	TOKEN_OP_GT("gt"),
	TOKEN_OP_AND("and"),
	TOKEN_OP_OR("or"),
	TOKEN_OP_NOT("not"),
	TOKEN_PARENTHESIS_L(null),
	TOKEN_PARENTHESIS_R(null),
	TOKEN_BOOLEAN_TRUE("true"),
	TOKEN_BOOLEAN_FALSE("false"),
	TOKEN_STRING(null),
	TOKEN_INTEGER(null),
	TOKEN_END_OF_RULE(null);
	//@formatter:on

	private String str;

	private TokenType(String str) {
		this.str = str;
	}

	public static TokenType of(String str) {
		for (TokenType t : values()) {
			if (str != null && str.equals(t.str))
				return t;
		}
		return null;
	}
}
