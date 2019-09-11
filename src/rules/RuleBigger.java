package rules;

import main.Main;
import objects.PGBase;
import objects.PGNumber;

public class RuleBigger extends RuleBase {

	public RuleBigger(String subjectS,String objectS) {
		this.subjectS=subjectS;
		this.objectS=objectS;
	}
	
	public RuleBigger(String subjectS,PGBase object) {
		this.subjectS=subjectS;
		this.objectS="";
		this.object=object;
	}

	@Override
	public PGBase apply() {
		if(this.subject==null)this.subject=PGBase.getPGFromFullpath(subjectS);
		if(this.object==null)this.object=PGBase.getPGFromFullpath(objectS);
		if(this.subject instanceof PGNumber) {
			if(this.object instanceof PGNumber) {
				((PGNumber)this.subject).getRange().min=((PGNumber)this.object).getDouble();
				Main.dependenciesSolver.addNode(((PGNumber)this.subject));
			}else {
				System.err.println("Rule Bigger was applied to "+this.object.getFullName()
				+" but object was not a number.");
			}
		}else {
			System.err.println("Rule Bigger was applied to "+this.subject.getFullName()
			+" but subject was not a number.");
		}
		return this.subject;
	}

	@Override
	public boolean check() {
		if(this.subject instanceof PGNumber) {
			if(this.object instanceof PGNumber) {
				return ((PGNumber)this.subject).getDouble()>((PGNumber)this.object).getDouble();
			}else {
				System.err.println("Rule Bigger was applied to "+this.object.getFullName()
				+" but object was not a number.");
			}
		}else {
			System.err.println("Rule Bigger was applied to "+this.subject.getFullName()
			+" but subject was not a number.");
		}
		return false;
	}

}
