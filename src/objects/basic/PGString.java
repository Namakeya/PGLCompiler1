package objects.basic;

public class PGString extends PGBase{
	private String var;
	
	public PGString() {
		this("","");
	}

	public PGString(String name_, String var_) {
		super(name_);
		setValue(var_);
	}

	@Override
	public PGBase clone() {
		return new PGString(this.getSimpleName(),this.var);
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGString) {
			PGString pgd=(PGString)obj;
			return pgd.getValue().equals(this.getValue());
		}
		return false;
	}
	public String getValue() {
		return var;
	}
	public void setValue(String var) {
		this.var = var;
	}
	
}
