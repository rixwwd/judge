package com.github.rixwwd.judge;

public enum Operator {

	EQ {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) == 0;
		}
	},
	NE {
		
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) != 0;
		}
	},
	LE {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) <= 0;
		}
	},
	LT {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) < 0;
		}
	},
	GE {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) >= 0;
		}
	},
	GT {
		@Override
		boolean eval(String a, String b) {
			return a.compareTo(b) > 0;
		}
	};
	
	abstract boolean eval(String a, String b);
}
