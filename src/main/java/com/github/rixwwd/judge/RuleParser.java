package com.github.rixwwd.judge;

public class RuleParser {

	private Tokenizer token;

	private Token currentToken;

	private Token getToken() throws SyntaxErrorException {
		Token t;
		if (currentToken != null) {
			t = currentToken;
			currentToken = null;
			return t;
		}

		t = token.getToken();

		return t;
	}

	private void back(Token t) {
		currentToken = t;
	}

	private Rule parseRule() throws SyntaxErrorException {

		Action a = parseAction();
		boolean q = parseQuick();
		Condition c = parseCondition();
		return new Rule(a, q, c);
	}

	private Action parseAction() throws SyntaxErrorException {
		Token action = getToken();

		switch (action.getTokenType()) {
		case TOKEN_ACTION_REJECT:
			return Action.REJECT;
		case TOKEN_ACTION_CHALLENGE:
			return Action.CHALLENGE;
		case TOKEN_ACTION_AUTHENTICATED:
			return Action.AUTHNETICATED;
		default:
			throw new SyntaxErrorException(action.getColumn());
		}

	}

	private boolean parseQuick() throws SyntaxErrorException {
		Token quick = getToken();
		if (quick.getTokenType() == TokenType.TOKEN_QUICK) {
			return true;
		} else {
			back(quick);
			return false;
		}
	}

	private Condition parseCondition() throws SyntaxErrorException {

		String field = parseField();
		Operator op = parseOperator();
		String str = parseStr();
		return new Condition(field, op, str);
	}

	private String parseField() throws SyntaxErrorException {
		Token field = getToken();
		if (field.getTokenType() == TokenType.TOKEN_FIELD) {
			return field.getValue();
		} else {
			throw new SyntaxErrorException(field.getColumn());
		}
	}

	private Operator parseOperator() throws SyntaxErrorException {
		Token op = getToken();

		switch (op.getTokenType()) {
		case TOKEN_OP_EQ:
			return Operator.EQ;
		case TOKEN_OP_NE:
			return Operator.NE;
		case TOKEN_OP_LE:
			return Operator.LE;
		case TOKEN_OP_LT:
			return Operator.LT;
		case TOKEN_OP_GE:
			return Operator.GE;
		case TOKEN_OP_GT:
			return Operator.GT;
		default:
			throw new SyntaxErrorException(op.getColumn());
		}
	}

	private String parseStr() throws SyntaxErrorException {
		Token str = getToken();
		if (str.getTokenType() == TokenType.TOKEN_STRING) {
			// ok
			return str.getValue();
		} else {
			throw new SyntaxErrorException(str.getColumn());
		}
	}

	public Rule parse(String rule) throws SyntaxErrorException {

		token = new Tokenizer(rule);
		Rule r = parseRule();
		return r;
	}
}
