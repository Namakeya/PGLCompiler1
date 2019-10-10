package objects.basic;

import java.util.HashMap;
import java.util.Map;

import main.Main;

public class PGType extends PGBase {
	public static PGType NO_TYPE=getType("NoType");
	public static PGType OBJECT_TYPE;
	static {
		OBJECT_TYPE=getType("Object");
		OBJECT_TYPE.setTemplate(new PGObject("",OBJECT_TYPE));
	}
	public static Map<String,PGType> types=new HashMap<String,PGType>();
	private PGObject template;
	private PGType parent;

	public PGType(String name) {
		super(name);
	}

	public static PGType getType(String name) {
		PGType type=types.get(name);
		if(type==null) {
			type=new PGType(name);
			type.setParentType(OBJECT_TYPE);
			addType(type);
			Main.logger.info("type "+name+" does not exist. The type was created as a child of Object.");
		}
		return type;
	}

	public static void addType(PGType type) {
		types.put(type.getSimpleName(), type);
	}

	@Override
	public boolean equals(Object obj) {
		return this==obj;
	}




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

	public PGObject getTemplate() {
		return template;
	}

	public void setTemplate(PGObject template) {
		this.template = template;
	}

	public PGType getParentType() {
		return parent;
	}

	public void setParentType(PGType parent) {
		this.parent = parent;
		this.template=(PGObject) parent.getTemplate().clone();
	}
}
