package rules;

import objects.PGBase;
import objects.PGObject;
import objects.PGType;
import objects.PGVariable;

public class RuleIs extends RuleBase {
	
	public PGType type;
	
	public RuleIs(PGBase subject,PGType type) {
		this.subject=subject;
		this.type=type;
	}

	@Override
	public PGBase apply() {
		if(type.getSimpleName().contains("Variable_")) {
			this.subject=PGVariable.createInstance(this.subject.getSimpleName(),type);
		}else {
			this.subject=new PGObject(this.subject.getSimpleName(),type);
		}
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getType()==this.type;
	}

}
