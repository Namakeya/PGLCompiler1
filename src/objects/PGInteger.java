package objects;

public class PGInteger extends PGNumber<Integer> {

	public PGInteger(Integer var_) {
		super(var_);
	}

	@SuppressWarnings("rawtypes")
	public int add(PGNumber n) {
		return this.getInt()+n.getInt();
	}
	
	public int add(Number n) {
		return this.getInt()+n.intValue();
	}
	
	@SuppressWarnings("rawtypes")
	public int sub(PGNumber n) {
		return this.getInt()-n.getInt();
	}
	
	public int sub(Number n) {
		return this.getInt()-n.intValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compareTo(PGNumber n) {
		return this.getInt()>n.getInt()?1:this.getInt()<n.getInt()?-1:0;
	}
}
