package com.github.rixwwd.judge;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Condition {

	private String field;

	private Operator operator;

	private String value;

	public Condition(String field, Operator operator, String value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	public boolean eval(Areq areq) {
		String v = getValue(field, areq);
		return v == null ? false : operator.eval(v, value);
	}

	private String getValue(String field, Areq areq) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, Areq.class);
			Method m = propertyDescriptor.getReadMethod();
			Object obj = m.invoke(areq);
			if (obj == null || obj instanceof String) {
				return (String) obj;
			}
			throw new RuntimeException();
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException();
		}

	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(field);
		sb.append(" " + operator.toString());
		sb.append(" \"" + value + "\"");
		return sb.toString();
	}
}
