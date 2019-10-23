package rules;

import objects.basic.PGDouble;
import objects.basic.PGRanged;

public class RuleBigger extends RuleBase {

	public RuleBigger(PGRanged sbj,PGDouble obj) {
		super(sbj,obj);
	}

	@Override
	public void apply() {
		PGRanged subject=(PGRanged) this.getSubject();
		subject.getRange().setMin((PGDouble) this.getObjects().get(0));
	}



}
