package rules;

import java.util.Random;

import objects.basic.PGDouble;

public class Range {

	private PGDouble max;
	private PGDouble min;
	private static Random random=new Random();

	public Range() {}

	public Range(PGDouble min,PGDouble max) {
		this.min=min;
		this.max=max;
	}

	public void setMin(PGDouble min) {
		this.min=min;
	}

	public void setMax(PGDouble max) {
		this.min=max;
	}

	public double get() {
		double M,m;
		if(max==null) {
			M=0d;
		}else{
			M=max.getValue();
		}
		if(min==null) {
			m=0d;
		}else{
			m=min.getValue();
		}
		if(M==m) {
			return max.getValue();
		}else {
			return m+(M-m)*random.nextDouble();
		}

	}

	public Range clone() {
		return new Range(this.max,this.min);
	}
}
