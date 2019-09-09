package objects;

public abstract class PGNumber<T extends Number> extends PGVariable<T> {
	public PGNumber(T var_) {
		super(var_);
	}
	public long getLong() {
		return (long)this.get();
	}
	public int getInt() {
		return (int)this.get();
	}
	public short getShort() {
		return (short)this.get();
	}
	public byte getByte() {
		return (byte)this.get();
	}
	public float getFloat() {
		return (float)this.get();
	}
	public double getDouble() {
		return (double)this.get();
	}
	
	@SuppressWarnings("rawtypes")
	public abstract int compareTo(PGNumber n);
}
