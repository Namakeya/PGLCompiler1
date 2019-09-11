package objects;

public class PGDouble extends PGNumber<Double> {

	public PGDouble(String name_, double var_) {
		super(name_, var_);
	}

	@Override
	public Double add(PGNumber n) {
		return this.getDouble()+n.getDouble();
	}

	@Override
	public Double add(Number n) {
		return this.getDouble()+n.doubleValue();
	}

	@Override
	public Double sub(PGNumber n) {
		return this.getDouble()-n.getDouble();
	}

	@Override
	public Double sub(Number n) {
		return this.getDouble()-n.doubleValue();
	}

	@Override
	public int compareTo(PGNumber n) {
		return this.getDouble()>n.getDouble()?1:this.getDouble()<n.getDouble()?-1:0;
	}

}
