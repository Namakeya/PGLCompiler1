package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PGObject {
	String simpleName;
	long serial;
	Map<String,PGObject> variables;
	List<PGObject> children;
	PGObject parent;
	static Random random;
	
	public PGObject() {
		serial = random.nextLong();
		variables = new HashMap<String,PGObject> ();
		children = new ArrayList<PGObject>();
		parent = null;
	}
	public PGObject(String name_){
		simpleName = name_;
	}

	public String getObjectName() {
		if (parent != null) {
			return parent.getObjectName()+"."+simpleName;
		}
		else {
			return simpleName;
		}
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
