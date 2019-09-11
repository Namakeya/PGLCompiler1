package objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PGBase {
	private long serial;
	private String simpleName;
	private Map<String,PGBase> children;
	private PGBase parent;
	private static Random random=new Random();
	private PGType type;
	
	public PGBase() {
		serial = random.nextLong();
		children = new HashMap<String,PGBase>();
		parent = null;
	}
	public PGBase(String name_,PGType type){
		this();
		simpleName = name_;
		this.type=type;
	}
	
	public String getFullName() {
		if (parent != null) {
			return parent.getFullName()+"."+this.getSimpleName();
		}
		else {
			return simpleName;
		}
	}
	
	public String getSimpleName() {
		return this.simpleName;
	}


	public long getSerial()
	{
		return serial;
	}
	
	public PGBase getChild(String name) {
		return children.get(name);
	}
	
	public Map<String,PGBase> getChildren()
	{
		return children;
	}

	public PGBase getParent()
	{
		return parent;
	}

	public void _addc(PGBase obj) {
		children.put(obj.getSimpleName(),obj);
	}
	public void _addp(PGBase obj) {
		parent=obj;
	}

	public void addChild(PGBase obj) {
		_addc(obj);
		obj._addp(this);
	}
	public void addParent(PGBase obj) {
		_addp(obj);
		obj._addc(this);
	}
	
	public PGType getType() {
		return type;
	}

}
