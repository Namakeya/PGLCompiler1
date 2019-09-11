package objects;

public class PGInteger extends PGNumber<Integer> {

	public PGInteger(String name_,Integer var_) {
		super(name_,var_);
	}

	@SuppressWarnings("rawtypes")
	public Integer add(PGNumber n) {
		return this.getInt()+n.getInt();
	}
	
	public Integer add(Number n) {
		return this.getInt()+n.intValue();
	}
	
	@SuppressWarnings("rawtypes")
	public Integer sub(PGNumber n) {
		return this.getInt()-n.getInt();
	}
	
	public Integer sub(Number n) {
		return this.getInt()-n.intValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compareTo(PGNumber n) {
		return this.getInt()>n.getInt()?1:this.getInt()<n.getInt()?-1:0;
	}
}
