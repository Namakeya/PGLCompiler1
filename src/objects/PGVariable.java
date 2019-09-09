package objects;

public class PGVariable<T> extends PGObject {

	private T var;
	
	public PGVariable(T var_){
		this.var=var_;
	}
	
	public T get() {return var;}
	public void set(T var_){this.var=var_;}

	@Override
	public String toString() {
		return var.toString();
	}
}
