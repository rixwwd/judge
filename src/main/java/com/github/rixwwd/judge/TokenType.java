package com.github.rixwwd.judge;

public enum TokenType {

	//@formatter:off
	TOKEN_ACTION_REJECT("reject"),
	TOKEN_ACTION_CHALLENGE("challenge"),
	TOKEN_ACTION_AUTHENTICATED("authenticated"),
	TOKEN_QUICK("quick"),
	TOKEN_FIELD(null),
	TOKEN_OP_EQ("eq"),
	TOKEN_OP_NE("ne"),
	TOKEN_OP_LE("le"),
	TOKEN_OP_LT("lt"),
	TOKEN_OP_GE("ge"),
	TOKEN_OP_GT("gt"),
	TOKEN_STRING(null);
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
