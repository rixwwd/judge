package com.github.rixwwd.judge;

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
	};

	abstract boolean eval(String a, String b);
}
