package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PGObject {
	private String simpleName;
	private long serial;
	private Map<String,PGObject> variables;
	private List<PGObject> children;
	private PGObject parent;
	private static Random random=new Random();
	
	public PGObject() {
		serial = random.nextLong();
		variables = new HashMap<String,PGObject> ();
		children = new ArrayList<PGObject>();
		parent = null;
	}
	public PGObject(String name_){
		this();
		simpleName = name_;
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

	public PGObject getVariable(String name)
	{
		if (variables.containsKey(name)) {
			return variables.get(name);
		}
		else {
			return null;
		}
	}

	public void setVariable(String name, PGObject obj)
	{
		variables.put(name, obj);
	}


	public List<PGObject> getChildren()
	{
		return children;
	}

	public PGObject getParent()
	{
		return parent;
	}

	public void _addc(PGObject obj) {
		children.add(obj);
	}
	public void _addp(PGObject obj) {
		parent=obj;
	}

	public void addChild(PGObject obj) {
		_addc(obj);
		obj._addp(this);
	}
	public void addParent(PGObject obj) {
		_addp(obj);
		obj._addc(this);
	}
}
