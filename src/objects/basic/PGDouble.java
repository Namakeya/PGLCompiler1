package objects.basic;

public abstract class PGDouble extends PGBase {


	public PGDouble(String name_) {
		super(name_);
	}

	abstract public double getValue();

/*
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

	@Override
	public PGBase clone() {
		return new PGDouble(this.getSimpleName(),this.var);
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGDouble) {
			PGDouble pgd=(PGDouble)obj;
			return pgd.getValue()==this.getValue();
		}
		return false;
	}
	public double getValue() {
		return var;
	}
	public void setValue(double var) {
		this.var = var;
	}
*/
}
