package rules;

import objects.basic.PGString;

public class RuleSEquals extends RuleBase {

	public RuleSEquals(PGString sbj,String obj) {
		super(sbj,obj);
	}

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
