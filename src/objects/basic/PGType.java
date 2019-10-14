package objects.basic;

import java.util.HashMap;
import java.util.Map;

import main.Main;

public class PGType extends PGObject {
	public static Map<String,PGType> types=new HashMap<String,PGType>();
	public static PGType NO_TYPE;
	public static PGType OBJECT_TYPE;
	public static PGType TYPE_TYPE;
	static {
		TYPE_TYPE=new PGType(0);
		TYPE_TYPE.setType(TYPE_TYPE);
		OBJECT_TYPE=new PGType("Object");
		OBJECT_TYPE.setTemplate(new PGObject("",OBJECT_TYPE));
		addType(OBJECT_TYPE);
		NO_TYPE=new PGType("NoType");
		addType(NO_TYPE);
	}
	
	private PGObject template;
	
	/**Only for making TYPE_TYPE. The paramator makes no sence.*/
	private PGType(int a) {
		super("TypeType",null);
	}

	public PGType(String name) {
		super(name,TYPE_TYPE);
	}
	
	public PGType() {
		this("");
	}
	
	@Override
	public void addParent(PGObject pgo) {
		super.addParent(pgo);
		if(pgo instanceof PGType) {
			PGType type=(PGType)pgo;
			PGObject temp;
			
			if(this.template==null) {
				temp=(PGObject) type.template.clone();
				
			}else {
				System.out.println(this.template.getFullName());
				temp=(PGObject) type.getTemplate().clone(this.template);
			}
			this.template=null;
			temp.setType(this);
			this.template=temp;
			System.out.println(this.template.getFullName());
		}
	}

	public static PGType getType(String name) {
		PGType type=types.get(name);
		if(type==null) {
			type=new PGType(name);
			type.addParent(OBJECT_TYPE);
			addType(type);
			Main.logger.info("type "+name+" does not exist. The type was created as a child of Object.");
		}
		//System.out.println("request:"+name+" returned:"+type.getFullName());
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

}
