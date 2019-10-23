package rules;

import objects.basic.PGType;

public class RuleExtends extends RuleBase {

	public RuleExtends(PGType sbj,PGType typeS) {
		super(sbj,typeS);
	}

	@Override
	public void apply() {
		//System.out.println(this.subject.getFullName());
		//PGType type=PGType.getType((String) getObjects().get(0));
		PGType type=(PGType)getObjects().get(0);
		getSubject().addParent(type);
		//System.out.println(this.type.getFullName());

	}
/*
	@Override
	public boolean check() {
		return this.subject.getParent()==this.type;
	}
*/
}
