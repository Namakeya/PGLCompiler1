package objects.function;

import main.Main;
import objects.basic.PGBase;
import objects.basic.PGObject;

public class FunctionEquals extends PGFunction {

	public FunctionEquals(String name_) {
		super(name_);
	}

	@Override
	public int numOfParameters() {
		return 1;
	}

	@Override
	public double getValue() {
		return this.getParams()[0].getValue();
	}

	@Override
	public PGBase clone() {
		FunctionEquals fe=new FunctionEquals(this.getSimpleName());
		fe.setParams(this.getParams().clone());
		return fe;
	}
	public static FunctionEquals getOrCreateFromFullpath(String path) {
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
					child=new FunctionEquals(s);
					pgb.addChild(child);
					return (FunctionEquals) child;
				}else if(child instanceof FunctionEquals) {
					return (FunctionEquals) child;
				}else if(child instanceof PGObject){
					pgb=(PGObject) child;
					continue;
				}else {
					Main.logger.severe(child.getFullName()+" is not a FunctionEquals! This is "
							+child.getClass().getSimpleName()+" instead.");
				}
			}
		}
		return null;
	}
}
