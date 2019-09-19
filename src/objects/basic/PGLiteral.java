package objects.basic;

public class PGLiteral extends PGDouble{

	private Double value;

	public PGLiteral(double value) {
		super("");
		this.value=value;
	}

	public void setValue(double value) {
		this.value=value;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public PGBase clone() {
		PGLiteral pgr=new PGLiteral(this.value);

		return pgr;
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGLiteral) {
			PGLiteral pgr=(PGLiteral)obj;
			if(pgr.value!=null) {
				return this.value.doubleValue()==pgr.value.doubleValue();
			}
		}
		return false;
	}

}
