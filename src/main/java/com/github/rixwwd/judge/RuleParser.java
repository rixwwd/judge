package com.github.rixwwd.judge;

import java.util.ArrayList;
import java.util.List;

public class RuleParser {

	private static final Expression TRUE = new TrueExpression();
	private static final Expression FALSE = new FalseExpression();

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
		List<Expression> expressions = new ArrayList<>();

		Token or = getToken();
		while (TokenType.TOKEN_OP_OR == or.getTokenType()) {
			Expression e = parseExpression0();
			expressions.add(e);
			or = getToken();
		}
		back(or);

		if (expressions.size() > 0) {
			return new Or(l, expressions.toArray(new Expression[] {}));
		}

		return l;
	}

	private Expression parseExpression0() throws SyntaxErrorException {
		Expression l = parseExpression1();
		List<Expression> expressions = new ArrayList<>();

		Token and = getToken();
		while (TokenType.TOKEN_OP_AND == and.getTokenType()) {
			Expression e = parseExpression1();
			expressions.add(e);
			and = getToken();
		}
		back(and);

		if (expressions.size() > 0) {
			return new And(l, expressions.toArray(new Expression[] {}));
		}
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

	private Expression parseCondition() throws SyntaxErrorException {

		Expression b = parseBoolean();
		if (b != null) {
			return b;
		}
		String field = parseField();
		Operator op = parseOperator();
		Object val = parseValue();
		return new Condition(field, op, val);
	}

	private String parseField() throws SyntaxErrorException {
		Token field = getToken();
		if (field.getTokenType() == TokenType.TOKEN_FIELD) {
			return field.getStringValue();
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

	private Object parseValue() throws SyntaxErrorException {
		Token v = getToken();
		switch (v.getTokenType()) {
		case TOKEN_STRING:
			return v.getStringValue();
		case TOKEN_INTEGER:
			return v.getIntegerValue();
		case TOKEN_BOOLEAN_TRUE:
			return Boolean.TRUE;
		case TOKEN_BOOLEAN_FALSE:
			return Boolean.FALSE;
		default:
			back(v);
		}
		return null;
	}

	private Expression parseBoolean() throws SyntaxErrorException {
		Token b = getToken();
		if (b.getTokenType() == TokenType.TOKEN_BOOLEAN_TRUE) {
			return TRUE;
		} else if (b.getTokenType() == TokenType.TOKEN_BOOLEAN_FALSE) {
			return FALSE;
		}
		back(b);
		return null;
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

	private static final class TrueExpression implements Expression {

		@Override
		public boolean eval(Areq areq) {
			return true;
		}

		@Override
		public String toString() {
			return "true";
		}

	}

	private static final class FalseExpression implements Expression {

		@Override
		public boolean eval(Areq areq) {
			return false;
		}

		@Override
		public String toString() {
			return "false";
		}

	}
}
