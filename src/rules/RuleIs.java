package rules;

import objects.PGInteger;
import objects.PGObject;
import objects.PGType;
import objects.PGVariable;

public class RuleIs extends RuleBase {
	
	public PGType type;

	@Override
	public void apply() {
		if(type.getSimpleName().contains("Variable_")) {
			this.subject=PGVariable.createInstance(this.subject.getSimpleName(),type);
		}else {
			this.subject=new PGObject(this.subject.getSimpleName(),type);
		}

	}

	@Override
	public boolean check() {
		if(type==PGType.TYPE_INTEGER) {
			return this.subject instanceof 
		}else if(type.getSimpleName().contains("Variable_")) {
			this.subject=new PGVariable<Object>(this.subject.getSimpleName(),null);
		}else {
			this.subject=new PGObject(this.subject.getSimpleName(),type);
		}
		return this.subject.t;
	}

}
