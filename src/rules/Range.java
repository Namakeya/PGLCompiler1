package rules;

import java.util.Random;

public class Range {

	public double max=Double.MAX_VALUE/4;
	public double min=Double.MIN_VALUE/4;
	public static Random random=new Random();
	
	public Range() {}
	
	public Range(double min,double max) {
		this.min=min;this.max=max;
	}
	
	public double get() {
		return min+(max-min)*random.nextDouble();
	}
}
