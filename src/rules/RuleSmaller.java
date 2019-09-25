package rules;

import main.Main;
import objects.basic.PGBase;
import objects.basic.PGDouble;
import objects.basic.PGRanged;

public class RuleSmaller extends RuleBase{
	private PGRanged subject;
	private PGDouble object;

	public RuleSmaller(PGRanged sbj,PGDouble obj) {
		this.subject=sbj;
		this.object=obj;
	}

	@Override
	public PGBase apply() {
		this.subject.getRange().setMax(this.object);
		Main.dependenciesSolver.addNode(this.subject,this.object);
		return this.subject;
	}

	@Override
	public boolean check() {
		return this.subject.getValue()<this.object.getValue();
	}
}
