package objects.basic;

import main.Main;

public class PGString extends PGBase{
	private String var;

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
	public static PGString getOrCreateFromFullpath(String path) {
		String[] names=path.split("\\.");
		PGObject pgb=null;
		for(String s:names) {
			//System.out.println(s);
			if(s.equals("root")) {
				pgb=Main.rootObject;
			}else {
				//System.out.println(s);
				if(pgb==null) {
					Main.logger.severe("Object "+s+" does not exist.");
				}
				PGBase child=pgb.getChild(s);

				if(child==null) {
					//System.out.println(PGType.NO_TYPE);
					child=new PGString(s, "");
					pgb.addChild(child);
					return (PGString) child;
				}else if(child instanceof PGString) {
					return (PGString) child;
				}else if(child instanceof PGObject){
					pgb=(PGObject) child;
					continue;
				}else {
					Main.logger.severe(child.getFullName()+" is not a String! This is "
							+child.getClass().getSimpleName()+" instead.");
				}
			}
		}
		return null;
	}
}
