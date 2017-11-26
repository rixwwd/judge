package com.github.rixwwd.judge;

public class Rule {

	private Action action;

	private boolean quick;

	private Condition condition;

	public Rule(Action a, boolean quick, Condition c) {
		this.action = a;
		this.quick = quick;
		this.condition = c;
	}

	public boolean eval(Areq areq) {
		return condition.eval(areq);
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
		sb.append(" " + condition.toString());

		return sb.toString();
	}
}
