package com.github.rixwwd.judge;

public class And implements Expression {

	private Expression left;
	private Expression right;

	public And(Expression l, Expression r) {
		this.left = l;
		this.right = r;
	}

	@Override
	public boolean eval(Areq areq) {
		return left.eval(areq) && right.eval(areq);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		sb.append(left.toString());
		sb.append(" and ");
		sb.append(right.toString());
		sb.append(')');
		return sb.toString();
	}
}
