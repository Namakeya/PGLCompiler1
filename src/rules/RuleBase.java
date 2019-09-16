package rules;

import objects.basic.PGBase;

public abstract class RuleBase {
	public abstract PGBase apply() ;
	public abstract boolean check();
}
