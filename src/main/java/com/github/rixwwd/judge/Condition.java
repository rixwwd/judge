package com.github.rixwwd.judge;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Condition implements Expression {

	private String fieldName;

	private Operator operator;

	private Object value;

	public Condition(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
	}

	public boolean eval(Areq areq) {
		Object fieldValue = getValue(fieldName, areq);

		if (fieldValue == null) {
			return false;
		}

		if ((fieldValue instanceof String) && (value instanceof String)) {
			return operator.eval((String) fieldValue, (String) value);
		} else if ((fieldValue instanceof Integer) && (value instanceof Integer)) {
			return operator.eval((Integer) fieldValue, (Integer) value);
		} else if ((fieldValue instanceof Boolean) && (value instanceof Boolean)) {
			return operator.eval((Boolean) fieldValue, (Boolean) value);
		}

		throw new RuntimeException();
	}

	private Object getValue(String field, Areq areq) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, Areq.class);
			Method m = propertyDescriptor.getReadMethod();
			Object obj = m.invoke(areq);
			return obj;
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		sb.append(fieldName);
		sb.append(" " + operator.toString());
		sb.append(" \"" + value + "\"");
		sb.append(')');
		return sb.toString();
	}
}
