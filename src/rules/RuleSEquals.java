package rules;

import objects.basic.PGString;

public class RuleSEquals extends RuleBase {

	@Override
	public void apply() {
		((PGString) this.getSubject()).setValue((String) getObjects().get(0));
	}
/*
	@Override
	public boolean check() {
		return this.subject.getValue().equals(object);
	}
*/
}
