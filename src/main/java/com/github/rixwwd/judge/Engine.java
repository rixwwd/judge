package com.github.rixwwd.judge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Engine {

	private List<Rule> rules;

	public Engine() {

	}

	public static Engine compile(String str) throws SyntaxErrorException {

		int lineNumber = 0;
		String[] lines = str.split("\r?\n");
		List<Rule> rules = new ArrayList<>(lines.length);
		for (String l : lines) {
			RuleParser rp = new RuleParser();
			try {
				Rule r = rp.parse(l);
				if (r != null) {
					rules.add(r);
				}
			} catch (SyntaxErrorException e) {
				throw new SyntaxErrorException(lineNumber, e.getColumn());
			}
			lineNumber++;
		}

		Engine engine = new Engine();
		engine.rules = Collections.unmodifiableList(rules);
		return engine;
	}

	public Action eval(String line, Areq areq) {
		RuleParser rp = new RuleParser();
		Rule r;
		try {
			r = rp.parse(line);
		} catch (SyntaxErrorException e) {
			throw new RuntimeException(e);
		}

		boolean ans = r == null ? false : r.eval(areq);

		return ans ? r.getAction() : null;

	}

	public Action eval(Areq areq) {

		Action action = null;

		for (Rule r : rules) {
			if (r.eval(areq)) {
				action = r.getAction();
				if (r.isQuick()) {
					break;
				}
			}
		}

		return action;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Engine [rules=");
		builder.append(rules);
		builder.append("]");
		return builder.toString();
	}
}
