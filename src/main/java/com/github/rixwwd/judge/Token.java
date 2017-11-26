package com.github.rixwwd.judge;

public class Token {

	private TokenType tokenType;

	private String value;
	
	private int col;

	public Token(TokenType type, String value, int col) {
		this.tokenType = type;
		this.value = value;
		this.col = col;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public String getValue() {
		return value;
	}
	
	public int getColumn() {
		return this.col;
	}

}
