package objects.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PGObject extends PGBase{


	private PGType type;
	private Map<String,PGBase> children = new HashMap<String,PGBase>();
	
	public PGObject() {
		this("",PGType.NO_TYPE);
	}

	public PGObject(String name_,PGType type) {
		super(name_);
		this.type=type;
	}
	public PGBase getChild(String name) {
		return children.get(name);
	}

	public Map<String,PGBase> getChildren()
	{
		return children;
	}

	public void _addc(PGBase obj) {
		children.put(obj.getSimpleName(),obj);
	}

	public void addChild(PGBase obj) {
		if(this instanceof PGType) {
			System.out.println(obj.getFullName()+" was added to "+this.getFullName());
		}
		_addc(obj);
		obj._addp(this);
	}

	public PGType getType() {
		return type;
	}

	/**do not change type except NO_TYPE*/
	public void setType(PGType type) {
		if(type.getTemplate()!=null) {
			
			type.getTemplate().clone(this);
			//System.out.println(this.getFullName()+" type is now "+this.getTypeName());
		}else {
			this.type=type;
		}
	}
	public PGBase clone() {

		return clone(new PGObject(this.getSimpleName(),PGType.NO_TYPE));
	}


	public PGBase clone(PGObject dest) {
		//System.out.println("dest type:"+dest.getTypeName()+" this type:"+this.getTypeName());
		dest.type=this.type;
		for(Entry<String, PGBase> entry:this.children.entrySet()) {
			dest.children.put(entry.getKey(), entry.getValue().clone());
			//System.out.println("put "+entry.getKey());
		}
		//System.out.println("dest type:"+dest.getTypeName());
		return dest;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PGObject) {
			PGObject pgb=(PGObject)obj;
			if(pgb.type==this.type && pgb.getFullName().equals(this.getFullName())) {
				for(Entry<String, PGBase> entry:this.children.entrySet()) {
					if(!entry.getValue().equals(pgb.children.get(entry.getKey()))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGObject) {
			PGObject pgb=(PGObject)obj;
			if(pgb.type==this.type) {
				for(Entry<String, PGBase> entry:this.children.entrySet()) {
					if(!entry.getValue().equals(pgb.children.get(entry.getKey()))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}


	@Override
	protected String getTreeString(int level) {
		String s=super.getTreeString(level);

		for(PGBase pgb:this.getChildren().values()) {

			s+=pgb.getTreeString(level+1);
		}
		return s;
	}

	@Override
	public String getTypeName() {
		return this.getType().getSimpleName();
	}

}
