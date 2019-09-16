package rules;

import objects.basic.PGBase;
import objects.basic.PGString;

public class RuleSEquals extends RuleBase {
	private String object;
	private PGString subject;

	public RuleSEquals(PGString sbj,String obj) {
		this.subject=sbj;
		this.object=obj;
	}

	@Override
	public PGBase apply() {
		this.subject.setValue(object);
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getValue().equals(object);
	}

}
