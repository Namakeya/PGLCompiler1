package rules;

import objects.PGBase;
import objects.PGType;

public class RuleEquals extends RuleBase{
	
	public RuleEquals(String subjectS,String objectS) {
		this.subjectS=subjectS;
		this.objectS=objectS;
	}
	
	public RuleEquals(String subjectS,PGBase object) {
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
			System.out.println("Type of "+this.subject+" has changed by "+this.object+"  from "+type+" to "+this.subject.getType());
		}
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.equals(this.object);
	}

}
