package rules;

import main.Main;
import objects.basic.PGBase;
import objects.basic.PGObject;
import objects.basic.PGType;

public class RuleIs extends RuleBase {

	public PGObject subject;
	public PGType type;
	public String typeS;

	public RuleIs(PGObject sbj,String typeS) {
		this.subject=sbj;
		this.typeS=typeS;
	}

	@Override
	public PGBase apply() {
		//System.out.println(this.subject.getFullName());
		this.type=PGType.getType(typeS);
		this.subject.setType(type);
		//System.out.println(this.type.getFullName());
		Main.logger.fine("Registered "+this.subject.getFullName()+" as "+type.getSimpleName());
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getType()==this.type;
	}

}
