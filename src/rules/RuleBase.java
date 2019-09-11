package rules;

import objects.PGBase;

public abstract class RuleBase {

	public PGBase subject;
	public PGBase object;
	
	public abstract void apply() ;
	public abstract boolean check();
}
