package com.github.rixwwd.judge;

public class Rule {

	private Action action;

	private boolean quick;

	private Expression expression;

	public Rule(Action a, boolean quick, Expression e) {
		this.action = a;
		this.quick = quick;
		this.expression = e;
	}

	public boolean eval(Areq areq) {
		return expression.eval(areq);
	}

	public Action getAction() {
		return action;
	}

	public boolean isQuick() {
		return quick;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(action);
		if (quick) {
			sb.append(" quick");
		}
		sb.append(" " + expression.toString());

		return sb.toString();
	}
}
