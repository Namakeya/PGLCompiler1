package rules;

import objects.basic.PGBase;
import objects.basic.PGDouble;
import objects.function.FunctionEquals;

public class RuleFEquals extends RuleBase {
	private FunctionEquals subject;
	private PGDouble object;

	public RuleFEquals(FunctionEquals sbj,PGDouble obj) {
		this.subject=sbj;
		this.object=obj;
	}

	@Override
	public PGBase apply() {

		this.subject.setParams(new PGDouble[]{this.object});

		return this.subject;
	}

	@Override
	public boolean check() {
		return true;
	}

}
