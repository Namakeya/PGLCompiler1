package rules;

import objects.basic.PGDouble;
import objects.basic.PGRanged;

public class RuleSmaller extends RuleBase{

	public RuleSmaller(PGRanged sbj,PGDouble obj) {
		super(sbj,obj);
	}

	@Override
	public void apply() {
		PGRanged subject=(PGRanged) this.getSubject();
		subject.getRange().setMax((PGDouble) this.getObjects().get(0));
	}


}
