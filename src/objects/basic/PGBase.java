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
	
	public void setSimpleName(String simpleName) {
		
		if(this.parent instanceof PGObject) {
			PGObject pgo=(PGObject)this.parent;
			//System.out.println(pgo.getChildren().containsKey(this.simpleName));
			pgo.getChildren().remove(this.simpleName, this);
			pgo.getChildren().put(simpleName, this);
		}
		this.simpleName=simpleName;
		
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
	


	public static PGBase getOrCreateFromFullpath(String path,Class<? extends PGBase> cls) {
		
		
		String[] names=path.split("\\.");
		PGObject pgb=null;
		if(names[0].equals("root")) {
			pgb=Main.rootObject;
		}else if(PGType.getType(names[0])!=null){
			pgb=PGType.getType(names[0]).getTemplate();
			//System.out.println("adding "+path+" to "+pgb.getTypeName());
		}else {
			Main.logger.severe("Unknown root object "+names[0]);
		}
		PGBase child=pgb.getChild(names[1]);
		for(int i=1;i<names.length-1;i++) {
			String s=names[i];
			if(pgb==null) {
				Main.logger.severe("Object "+s+" does not exist.");
			}

			if(child==null) {
				child=new PGObject(s,PGType.NO_TYPE);
				pgb.addChild(child);
				pgb=(PGObject) child;
			}else if(child instanceof PGObject){
				pgb=(PGObject) child;
			}else {
				Main.logger.severe(child.getFullName()+" is not an object. This is "
						+child.getClass().getSimpleName()+" instead.");
			}
			//System.out.println(pgb.getFullName()+"'s child "+names[i+1]);
			child=pgb.getChild(names[i+1]);
		}

		if(child==null) {
			try {
				child=cls.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			pgb.addChild(child);
			child.setSimpleName(names[names.length-1]);
			//System.out.println("created "+child.getFullName()+" to "+pgb.getFullName());
			return child;
		}else if(cls.isInstance(child)) {
			//System.out.println(child.getFullName()+" exists");
			return child;
		}else {
			Main.logger.severe(child.getFullName()+" is not "+cls.getSimpleName()+". This is "
					+child.getClass().getSimpleName()+" instead.");
		}

		return null;
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
