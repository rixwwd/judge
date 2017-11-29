package com.github.rixwwd.judge;

public enum Boolean implements Expression {

	TRUE {

		@Override
		public boolean eval(Areq areq) {
			return true;
		}

		@Override
		public String toString() {
			return "true";
		}

	},
	FALSE {

		@Override
		public boolean eval(Areq areq) {
			return false;
		}

		@Override
		public String toString() {
			return "false";
		}
	}

}
