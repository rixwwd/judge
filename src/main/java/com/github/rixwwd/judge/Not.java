package com.github.rixwwd.judge;

public class Not implements Expression {

	private Expression expression;

	public Not(Expression expression) {
		this.expression = expression;
	}

	@Override
	public boolean eval(Areq areq) {
		return !this.expression.eval(areq);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("not (");
		sb.append(expression.toString());
		sb.append(")");
		return sb.toString();
	}
}
