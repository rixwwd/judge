package com.github.rixwwd.judge;

import java.io.EOFException;

public class Tokenizer {

	private static String PARENTHESIS_L = "(";
	private static String PARENTHESIS_R = ")";

	private char[] source;
	private int index;

	public Tokenizer(String source) {
		this.source = source.toCharArray();
		this.index = 0;
	}

	private char getChar() throws EOFException {
		if (index < source.length) {
			return source[index++];
		}
		throw new EOFException();
	}

	private void back() {
		index--;
	}

	private static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private static boolean isAlphabetL(char c) {
		return c >= 'a' && c <= 'z';
	}

	private static boolean isAlphabetU(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private static boolean isQuote(char c) {
		return c == '"';
	}

	private static boolean isSpace(char c) {
		return c == ' ';
	}

	private static boolean isHash(char c) {
		return c == '#';
	}

	private static boolean isParenthesisLeft(char c) {
		return c == '(';
	}

	private static boolean isParenthesisRight(char c) {
		return c == ')';
	}

	private Token handleKeyword(String keyword, int col) {

		TokenType t = TokenType.of(keyword);
		if (t != null) {
			return new Token(t, keyword, col);
		}

		return new Token(TokenType.TOKEN_FIELD, keyword, col);
	}

	public Token getToken() throws SyntaxErrorException {

		int startIndex = 0;
		StringBuilder builder = null;
		boolean parsed = false;

		try {
			for (; index < source.length;) {
				char c = getChar();

				// 空白スキップ
				while (isSpace(c)) {
					parsed = true;
					c = getChar();
				}
				parsed = false;

				if (isAlphabetL(c) || isAlphabetU(c)) {
					// キーワード
					startIndex = index;
					builder = new StringBuilder();
					builder.append(c);
					parsed = true;
					c = getChar();
					while (isAlphabetL(c) || isAlphabetU(c) || isDigit(c)) {
						builder.append(c);
						c = getChar();
					}
					back();
					return handleKeyword(builder.toString(), startIndex);
				} else if (isQuote(c)) {
					// 文字列
					startIndex = index;
					builder = new StringBuilder();
					c = getChar();
					while (!isQuote(c)) {
						builder.append(c);
						c = getChar();
					}
					return new Token(TokenType.TOKEN_STRING, builder.toString(), startIndex);
				} else if (isParenthesisLeft(c)) {
					return new Token(TokenType.TOKEN_PARENTHESIS_L, PARENTHESIS_L, index);
				} else if (isParenthesisRight(c)) {
					return new Token(TokenType.TOKEN_PARENTHESIS_R, PARENTHESIS_R, index);
				} else if (isHash(c)) {
					// コメント
					index = source.length;
					break;
				}
			}
		} catch (EOFException e) {
			if (parsed) {
				if (builder != null) {
					return handleKeyword(builder.toString(), startIndex);
				}
			} else {
				throw new SyntaxErrorException(index);
			}
		}
		return new Token(TokenType.TOKEN_END_OF_RULE, null, index);
	}

}
