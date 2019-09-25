package objects.function;

import objects.basic.PGBase;

public class FunctionPlus  extends PGFunction {

	public FunctionPlus() {
		super("");
	}

	@Override
	public int numOfParameters() {
		return 2;
	}

	@Override
	public double getValue() {
		return this.getParams()[0].getValue()+this.getParams()[1].getValue();
	}

	@Override
	public PGBase clone() {
		FunctionEquals fe=new FunctionEquals(this.getSimpleName());
		fe.setParams(this.getParams().clone());
		return fe;
	}


}
