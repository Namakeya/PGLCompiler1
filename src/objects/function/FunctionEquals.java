package objects.function;

import objects.basic.PGBase;

public class FunctionEquals extends PGFunction {

	public FunctionEquals(String name_) {
		super(name_);
	}

	@Override
	int numOfParameters() {
		return 1;
	}

	@Override
	public double getValue() {
		return this.getParams()[0].getValue();
	}

	@Override
	public PGBase clone() {
		FunctionEquals fe=new FunctionEquals(this.getSimpleName());
		fe.setParams(this.getParams().clone());
		return fe;
	}

}
