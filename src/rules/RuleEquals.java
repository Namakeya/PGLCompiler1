/*package rules;

import main.Main;
import objects.basic.PGBase;
import objects.basic.PGDouble;
import objects.basic.PGType;

public class RuleEquals extends RuleBase{

	public RuleEquals(String subjectS,String objectS) {
		this.subjectS=subjectS;
		this.objectS=objectS;
	}

	public RuleEquals(String subjectS,PGDouble object) {
		this.subjectS=subjectS;
		this.objectS="";
		this.object=object;
	}

	@Override
	public PGBase apply() {
		if(this.subject==null)this.subject=PGBase.getPGFromFullpath(subjectS);
		if(this.object==null)this.object=PGBase.getPGFromFullpath(objectS);


		PGType type=this.subject.getType();

		this.object.clone(this.subject);
		if(type != PGType.NO_TYPE && !type.equals(this.subject.getType())) {
			Main.logger.fine("Type of "+this.subject.getFullName()+" has changed by "+this.object.getFullName()+"  from "+type.getFullName()+" to "+this.subject.getType().getFullName());
		}
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.equalsWithoutName(this.object);
	}

}
*/