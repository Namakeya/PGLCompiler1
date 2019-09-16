package objects.basic;

import java.util.Random;

import main.Main;

abstract public class PGBase {
	private long serial;
	private String simpleName;

	private PGBase parent;
	private static Random random=new Random();


	public PGBase() {
		serial = random.nextLong();
		parent = null;
	}
	public PGBase(String name_){
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



	public PGBase getParent()
	{
		return parent;
	}


	public void _addp(PGBase obj) {
		parent=obj;
	}


	public void addParent(PGObject obj) {
		_addp(obj);
		obj._addc(this);
	}


	@Override
	abstract public PGBase clone();


	abstract public boolean equalsWithoutName(Object obj);

	public static PGBase getPGFromFullpath(String path) {
		String[] names=path.split("\\.");
		PGBase pgb=null;
		for(String s:names) {
			//System.out.println(s);
			if(s.equals("root")) {
				pgb=Main.rootObject;
			}else {
				//System.out.println(s);
				if(pgb==null) {
					System.err.println("Object "+s+" does not exist.");
				}
				if(pgb instanceof PGObject) {
					PGObject pgo=(PGObject)pgb;
					PGBase child=pgo.getChild(s);
					/*
					if(child==null) {
						//System.out.println(PGType.NO_TYPE);
						child=new PGDouble(s,0d);
						pgo.addChild(child);
					}
					*/
					pgb=child;
				}
			}

		}
		return pgb;
	}



	public String getTypeName() {
		return this.getClass().getSimpleName();
	}

	public String getTreeString() {
		return getTreeString(0);
	}

	protected String getTreeString(int level) {
		String s="";
		for(int i=0;i<level;i++) {
			s+="-";
		}
		s+=this.getSimpleName()+"("+this.getTypeName()+")\n";
		return s;
	}

}
