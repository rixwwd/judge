package com.github.rixwwd.judge;

public class SyntaxErrorException extends Exception {

	private static final long serialVersionUID = -8634727278404567250L;

	private int line;
	private int col;
	
	public SyntaxErrorException(int col) {
		this(0, col);
	}
	
	public SyntaxErrorException(int line, int col) {
		this.line = line;
		this.col = col;
	}
	
	public int getLine() {
		return this.line;
	}
	public int getColumn() {
		return this.col;
	}
	
	@Override
	public String getMessage() {
		return "Syntax Error at line: " + line + ", col: " + col;
	}
}
