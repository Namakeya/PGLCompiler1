package rules;

import main.Main;
import objects.basic.PGBase;
import objects.basic.PGType;

public class RuleExtends extends RuleBase {

	public PGType subject;
	public PGType type;
	public String typeS;

	public RuleExtends(PGType sbj,String typeS) {
		this.subject=sbj;
		this.typeS=typeS;
	}

	@Override
	public PGBase apply() {
		//System.out.println(this.subject.getFullName());
		this.type=PGType.getType(typeS);
		this.subject.addParent(type);
		//System.out.println(this.type.getFullName());
		Main.logger.fine("Registered "+this.subject.getFullName()+" as "+type.getSimpleName());
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getParent()==this.type;
	}

}
