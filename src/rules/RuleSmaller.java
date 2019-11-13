package rules;

import objects.basic.PGDouble;
import objects.basic.PGRanged;

public class RuleSmaller extends RuleBase{

	@Override
	public void apply() {
		PGRanged subject=(PGRanged) this.getSubject();
		subject.getRange().setMax((PGDouble) this.getObjects().get(0));
	}


}
