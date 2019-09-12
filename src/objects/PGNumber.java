package objects;

public abstract class PGNumber<T extends Number> extends PGVariable<T> {
	public PGNumber(String name_,T var_) {
		super(name_, var_);
	}
	public long getLong() {
		return this.get().longValue();
	}
	public int getInt() {
		return this.get().intValue();
	}
	public float getFloat() {
		return this.get().floatValue();
	}
	public double getDouble() {
		return this.get().doubleValue();
	}
	
	@SuppressWarnings("rawtypes")
	public abstract T add(PGNumber n);
	
	public abstract T add(Number n);
	
	@SuppressWarnings("rawtypes")
	public abstract T sub(PGNumber n);
	
	public abstract T sub(Number n);
	
	@SuppressWarnings("rawtypes")
	public abstract int compareTo(PGNumber n);
	
	
	@SuppressWarnings("rawtypes")
	public static PGNumber createNumberInstance(String name ,Number var) {
		if(var instanceof Integer) {
			return new PGInteger(name,(Integer)var);
		}else if(var instanceof Double){
			return new PGDouble(name,(Double)var);
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static PGNumber createNumberInstance(String name ,PGType type) {
		if(type==PGType.INTEGER_TYPE) {
			return new PGInteger(name,0);
		}else if(type==PGType.DOUBLE_TYPE){
			return new PGDouble(name,0d);
		}else {
			return null;
		}
	}
}
