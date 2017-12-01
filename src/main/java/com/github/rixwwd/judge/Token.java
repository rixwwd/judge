package com.github.rixwwd.judge;

public class Token {

	private TokenType tokenType;

	private String stringValue;

	private Integer integerValue;

	private Boolean booleanValue;

	private int col;

	public Token(TokenType type, int col) {
		this.tokenType = type;
		this.col = col;
	}

	public Token(TokenType type, String value, int col) {
		this.tokenType = type;
		this.stringValue = value;
		this.col = col;
	}

	public Token(TokenType type, Integer value, int col) {
		this.tokenType = type;
		this.integerValue = value;
		this.col = col;
	}

	public Token(TokenType type, Boolean value, int col) {
		this.tokenType = type;
		this.booleanValue = value;
		this.col = col;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public String getStringValue() {
		return stringValue;
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public int getColumn() {
		return this.col;
	}

}
