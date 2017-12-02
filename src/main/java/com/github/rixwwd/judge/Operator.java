package com.github.rixwwd.judge;

import java.util.List;

public enum Operator {

	EQ {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) == 0;
		}

		@Override
		public String toString() {
			return "eq";
		}

		@Override
		boolean eval(boolean a, boolean b) {
			return a == b;
		}

		@Override
		boolean eval(int a, int b) {
			return a == b;
		}

	},
	NE {

		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) != 0;
		}

		@Override
		public String toString() {
			return "ne";
		}

		@Override
		boolean eval(boolean a, boolean b) {
			return a != b;
		}

		@Override
		boolean eval(int a, int b) {
			return a != b;
		}
	},
	LE {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) <= 0;
		}

		@Override
		public String toString() {
			return "le";
		}

		@Override
		boolean eval(int a, int b) {
			return a <= b;
		}
	},
	LT {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) < 0;
		}

		@Override
		public String toString() {
			return "lt";
		}

		@Override
		boolean eval(int a, int b) {
			return a < b;
		}
	},
	GE {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) >= 0;
		}

		@Override
		public String toString() {
			return "ge";
		}

		@Override
		boolean eval(int a, int b) {
			return a >= b;
		}
	},
	GT {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) > 0;
		}

		@Override
		public String toString() {
			return "gt";
		}

		@Override
		boolean eval(int a, int b) {
			return a > b;
		}
	},
	IN {

		@Override
		boolean eval(Object a, List<Object> b) {

			return b.contains(a);
		}

		@Override
		public String toString() {
			return "in";
		}

	};

	boolean eval(String a, String b) {
		throw new UnsupportedOperationException();
	}

	boolean eval(boolean a, boolean b) {
		throw new UnsupportedOperationException();
	}

	boolean eval(int a, int b) {
		throw new UnsupportedOperationException();
	}

	boolean eval(Object a, List<Object> b) {
		throw new UnsupportedOperationException();
	}
}
