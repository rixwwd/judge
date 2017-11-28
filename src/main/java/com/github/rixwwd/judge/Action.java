package com.github.rixwwd.judge;

public enum Action {

	REJECT {
		@Override
		public String toString() {
			return "reject";
		}
	},
	CHALLENGE {
		@Override
		public String toString() {
			return "challenge";
		}
	},
	AUTHNETICATED {
		@Override
		public String toString() {
			return "authenticated";
		}
	},
	CANCEL {
		@Override
		public String toString() {
			return "cancel";
		}
	},
	UNAVAILABLE {
		@Override
		public String toString() {
			return "unavailable";
		}
	},
	ATTEMPT {
		@Override
		public String toString() {
			return "attempt";
		}
	}
}
