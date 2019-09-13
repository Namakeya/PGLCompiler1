package rules;

import objects.PGBase;

public abstract class RuleBase {
	

	public String subjectS;
	public String objectS;
	
	public PGBase subject;
	public PGBase object;
	
	public abstract PGBase apply() ;
	public abstract boolean check();
}
