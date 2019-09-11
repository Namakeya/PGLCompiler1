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
	
	@SuppressWarnings("rawtypes")
	public static PGVariable createInstance(String name,Object var) {
		if(var instanceof Number) {
			PGNumber pgn=PGNumber.createNumberInstance(name,(Number)var);
			
			return pgn==null?new PGVariable<Number>(name,(Number)var):pgn;
		}else {
			return new PGVariable<Object>(name,var);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static PGVariable createInstance(String name,PGType type) {
		PGNumber pgn=PGNumber.createNumberInstance(name,type);
			
		return pgn==null?new PGVariable<Object>(name,null):pgn;
	}
}
