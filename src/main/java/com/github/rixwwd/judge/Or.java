package com.github.rixwwd.judge;

public class Or implements Expression {

	private Expression left;
	private Expression[] expressions;

	public Or(Expression l, Expression... expressions) {
		this.left = l;
		this.expressions = expressions;
	}

	@Override
	public boolean eval(Areq areq) {
		boolean x = left.eval(areq);
		for (Expression e : expressions) {
			x = x || e.eval(areq);
		}
		return x;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		sb.append(left.toString());
		if (expressions != null) {
			for (Expression e : expressions) {
				sb.append(" or ");
				sb.append(e.toString());
			}
		}
		sb.append(')');
		return sb.toString();
	}
}
