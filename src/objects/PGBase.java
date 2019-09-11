package objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import main.Main;

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
	
	@Override
	public PGBase clone() {
		
		return clone(new PGBase());
	}

	public PGBase clone(PGBase dest) {
		dest.type=this.type;
		for(Entry<String, PGBase> entry:this.children.entrySet()) {
			dest.children.put(entry.getKey(), entry.getValue().clone());
		}
		return dest;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PGBase) {
			PGBase pgb=(PGBase)obj;
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
	
	public static PGBase getPGFromFullpath(String path) {
		String[] names=path.split("\\.");
		PGBase pgb=null;
		for(String s:names) {
			//System.out.println(s);
			if(s.equals("root")) {
				pgb=Main.rootObject;
			}else {
				PGBase child=pgb.getChild(s);
				if(child==null) {
					//System.out.println(PGType.NO_TYPE);
					child=new PGBase(s,PGType.NO_TYPE);
					pgb.addChild(child);
				}
				pgb=child;
			}
			
		}
		return pgb;
	}
	
	public String getTreeString() {
		return getTreeString(0);
	}
	
	protected String getTreeString(int level) {
		String s="";
		for(int i=0;i<level;i++) {
			s+="-";
		}
		s+=this.getSimpleName()+"("+this.getType().getSimpleName()+")\n";
		for(PGBase pgb:this.getChildren().values()) {
			s+=pgb.getTreeString(level+1);
		}
		return s;
	}
}
