package rules;

import main.Main;
import objects.basic.PGBase;
import objects.basic.PGDouble;
import objects.basic.PGRanged;

public class RuleBigger extends RuleBase {
	private PGRanged subject;
	private PGDouble object;
	private String objName;

	public RuleBigger(PGRanged sbj,PGDouble obj) {
		this.subject=sbj;
		this.object=obj;
	}

	public RuleBigger(PGRanged sbj,String objName) {
		this.subject=sbj;
		this.objName=objName;
	}

	@Override
	public PGBase apply() {
		if(this.object==null&& this.objName!=null) {
			PGBase pgb= PGBase.getPGFromFullpath(objName);
			if(pgb instanceof PGDouble) {
				this.object=(PGDouble)pgb;
			}else {
				Main.logger.severe(objName+" is "+pgb.getTypeName()+" instead of number!"
						+"This sentence will be ignored.");
				return this.subject;
			}
		}

		this.subject.getRange().setMin(this.object);
		Main.dependenciesSolver.addNode(this.subject,this.object);
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getValue()>this.object.getValue();
	}

}
