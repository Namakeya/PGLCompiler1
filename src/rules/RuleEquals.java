package rules;

import objects.PGBase;
import objects.PGType;

public class RuleEquals extends RuleBase{
	
	public RuleEquals(PGBase subject,PGBase object) {
		this.subject=subject;
		this.object=object;
	}

	@Override
	public PGBase apply() {
		PGType type=this.subject.getType();
		this.object.clone(this.subject);
		if(type != PGType.NO_TYPE && !type.equals(this.subject.getType())) {
			System.out.println("Type of "+this.subject+" has changed by "+this.object+"  from "+type+" to "+this.subject.getType());
		}
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.equals(this.object);
	}

}
