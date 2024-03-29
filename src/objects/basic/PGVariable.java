package objects.basic;
/*package objects;

import main.Main;
import rules.Range;

public class PGVariable<T> extends PGObject {

	private T var;
	private boolean isConcrete=false;
	private Range range=new Range();
	private PGVariable equalFrom,equalTo;

	public PGVariable(String name,T var){
		super(name, PGType.getType("Variable_"+var.getClass().getSimpleName()));
		this.var=var;
	}

	public PGVariable(String name,PGType type){
		super(name, type);
	}

	public T get() {
		if(equalFrom!=null) {
			try {
				return (T) equalFrom.get();
			}catch(ClassCastException e) {
				Main.logger.severe(this.getFullName()+" cannot casted from "
						+var.getClass().getSimpleName()+" to "+equalFrom.get().getClass().getSimpleName());
				e.printStackTrace();
			}
		}
		return var;
	}
	public void set(T var_){
		this.var=var_;
	}

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

		return pgn==null?new PGVariable<Object>(name,type):pgn;
	}

	@SuppressWarnings({ "rawtypes" })
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
			return pgv.var.equals(this.var) && super.equals(obj);
		}
		return false;
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGVariable) {
			PGVariable pgv=(PGVariable)obj;
			return pgv.var.equals(this.var);
		}
		return false;
	}

	public boolean isConcrete() {
		return isConcrete;
	}

	public void setConcrete(boolean isConcrete) {
		this.isConcrete = isConcrete;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

}
*/