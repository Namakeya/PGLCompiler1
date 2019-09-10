package objects;

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
}
