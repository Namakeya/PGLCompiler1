package objects;

public class PGVariable<T> extends PGObject {

	private T var;
	
	public PGVariable(String name,T var){
		super(name, PGType.getType("Variable_"+var.getClass().getSimpleName()));
		this.var=var;
	}
	
	public T get() {return var;}
	public void set(T var_){this.var=var_;}

	@Override
	public String toString() {
		return var.toString();
	}
}
