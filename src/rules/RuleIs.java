package rules;

import main.Main;
import objects.basic.PGObject;
import objects.basic.PGType;

public class RuleIs extends RuleBase {

	public RuleIs(PGObject sbj,PGType typeS) {
		super(sbj,typeS);
	}

	@Override
	public void apply() {
		//System.out.println(this.subject.getFullName());
		//PGType type=PGType.getType((String) getObjects().get(0));
		PGType type=(PGType)getObjects().get(0);
		((PGObject) getSubject()).setType(type);
		//System.out.println(this.subject.getTreeString());
		//System.out.println(this.type.getFullName());
		Main.logger.fine("Registered "+getSubject().getFullName()+" as "+type.getSimpleName());
	}
/*
	@Override
	public boolean check() {
		return this.subject.getType()==this.type;
	}
*/
}
