package rules;

import objects.PGBase;
import objects.PGObject;
import objects.PGType;
import objects.PGVariable;

public class RuleIs extends RuleBase {
	
	public String typeS;
	public PGType type;
	
	public RuleIs(String subjectS,String typeS) {
		this.subjectS=subjectS;
		this.typeS=typeS;
	}

	@Override
	public PGBase apply() {
		this.type=PGType.getType(typeS);
		this.subject=PGBase.getPGFromFullpath(subjectS);
		PGBase parent=this.subject.getParent();
		System.out.println(this.type.getFullName());
		//this.object=PGBase.getPGFromFullpath(objectS);
		if(type.getSimpleName().contains("Variable_")) {
			this.subject=PGVariable.createInstance(this.subject.getSimpleName(),type);
		}else {
			this.subject=new PGObject(this.subject.getSimpleName(),type);
		}
		
		parent.addChild(subject);
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getType()==this.type;
	}

}
