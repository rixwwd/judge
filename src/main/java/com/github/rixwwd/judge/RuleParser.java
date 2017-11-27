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
		Expression e = parseExpression();
		parseEndOfRule();
		return new Rule(a, q, e);
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
		case TOKEN_ACTION_CANCEL:
			return Action.CANCEL;
		case TOKEN_ACTION_UNAVAILABLE:
			return Action.UNAVAILABLE;
		case TOKEN_ACTON_ATTEMPT:
			return Action.ATTEMPT;
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

	private Expression parseExpression() throws SyntaxErrorException {
		Expression l = parseExpression0();
		Token or = getToken();
		if (TokenType.TOKEN_OP_OR == or.getTokenType()) {
			Expression r = parseExpression0();
			return new Or(l, r);
		}

		back(or);
		return l;
	}

	private Expression parseExpression0() throws SyntaxErrorException {
		Expression l = parseExpression1();
		Token and = getToken();
		if (TokenType.TOKEN_OP_AND == and.getTokenType()) {
			Expression r = parseExpression1();
			return new And(l, r);
		}
		back(and);
		return l;
	}

	private Expression parseExpression1() throws SyntaxErrorException {
		Token not = getToken();
		boolean notFlag;
		if (TokenType.TOKEN_OP_NOT == not.getTokenType()) {
			notFlag = true;
		} else {
			notFlag = false;
			back(not);
		}
		Expression e = parseExpression2();

		return notFlag ? new Not(e) : e;
	}

	private Expression parseExpression2() throws SyntaxErrorException {

		Token parenthesisL = getToken();
		if (parenthesisL.getTokenType() != TokenType.TOKEN_PARENTHESIS_L) {
			back(parenthesisL);
			return parseCondition();
		}
		Expression e = parseExpression();
		Token parenthesisR = getToken();
		if (parenthesisR.getTokenType() != TokenType.TOKEN_PARENTHESIS_R) {
			throw new SyntaxErrorException(parenthesisR.getColumn());
		}

		return e;
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

	private void parseEndOfRule() throws SyntaxErrorException {
		Token eor = getToken();
		if (eor.getTokenType() != TokenType.TOKEN_END_OF_RULE) {
			throw new SyntaxErrorException(eor.getColumn());
		}
	}

	public Rule parse(String rule) throws SyntaxErrorException {

		token = new Tokenizer(rule);
		Rule r = parseRule();
		return r;
	}
}
