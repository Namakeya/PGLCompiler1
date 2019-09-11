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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PGBase clone() {
		PGVariable copy=new PGVariable("",PGType.NO_TYPE);
		
		return clone(copy);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PGBase clone(PGBase dest) {
		
		if(dest instanceof PGVariable) {
			super.clone(dest);
			((PGVariable)dest).var=this.var;
		}else {
			System.err.append("Failed to Clone! "+dest.toString()+" Type "+
		dest.getClass().getSimpleName()+" cannot be casted to PGVariable.");
		}
		return dest;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PGVariable) {
			PGVariable pgv=(PGVariable)obj;
			return pgv.var.equals(this.var);
		}
		return false;
	}
}
