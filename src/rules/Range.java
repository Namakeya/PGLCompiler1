package rules;

import java.util.Random;

public class Range {

	public double max;
	public double min;
	public static Random random=new Random();
	
	public Range(double min,double max) {
		this.min=min;this.max=max;
	}
	
	public double get() {
		return min+(max-min)*random.nextDouble();
	}
}
