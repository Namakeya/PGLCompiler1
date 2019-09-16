package rules;

import objects.basic.PGBase;
import objects.basic.PGDouble;
import objects.function.FunctionEquals;

public class RuleFEquals extends RuleBase {
	private FunctionEquals subject;
	private PGDouble object;
	private String objName;

	public RuleFEquals(FunctionEquals sbj,PGDouble obj) {
		this.subject=sbj;
		this.object=obj;
	}

	public RuleFEquals(FunctionEquals sbj,String objName) {
		this.subject=sbj;
		this.objName=objName;
	}

	@Override
	public PGBase apply() {
		if(this.object==null&& this.objName!=null) {
			PGBase.getPGFromFullpath(objName);
		}
		this.subject.setParams(new PGDouble[]{this.object});

		return this.subject;
	}

	@Override
	public boolean check() {
		return true;
	}

}
