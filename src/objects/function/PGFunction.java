package objects.function;

import java.util.Arrays;

import objects.basic.PGDouble;

public abstract class PGFunction extends PGDouble {

	private PGDouble[] params;

	public PGFunction(String name_) {
		super(name_);
		params=new PGDouble[numOfParameters()];
	}

	abstract int numOfParameters();


	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj.getClass().equals(this.getClass())) {
			PGFunction pgf=(PGFunction) obj;
			return Arrays.equals(pgf.params, this.params);
		}
		return false;
	}

	public PGDouble[] getParams() {
		return params;
	}

	public void setParams(PGDouble[] params) {
		this.params = params;
	}

}
