package objects.basic;

import java.util.HashMap;
import java.util.Map;

public class PGType extends PGBase {
	public static Map<String,PGType> types=new HashMap<String,PGType>();

	public PGType(String name) {
		super(name);
	}

	public static PGType getType(String name) {
		PGType type=types.get(name);
		if(type==null) {
			type=new PGType(name);
			types.put(name, type);
		}
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		return this==obj;
	}


	public static PGType NO_TYPE=getType("NoType");
	public static PGType INTEGER_TYPE=getType("Variable_Integer");
	public static PGType DOUBLE_TYPE=getType("Variable_Double");

	@Override
	public PGBase clone() {
		return new PGType(this.getSimpleName());
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGType) {
			PGType pgt=(PGType) obj;
			if(this.equals(pgt)) {
				return true;
			}
		}
		return false;
	}
}
