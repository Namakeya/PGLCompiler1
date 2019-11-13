package rules;

import objects.basic.PGDouble;
import objects.function.FunctionEquals;

public class RuleFEquals extends RuleBase {

	@Override
	public void apply() {

		((FunctionEquals)getSubject()).setParams(new PGDouble[]{(PGDouble) getObjects().get(0)});

	}


}
