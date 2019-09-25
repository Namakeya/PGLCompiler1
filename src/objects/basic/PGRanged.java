package objects.basic;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import rules.Range;

public class PGRanged extends PGDouble{

	private Double value;
	private Range range=new Range();
	private List<PGRanged> affectTo=new ArrayList<PGRanged>();
	private List<PGRanged> affectFrom=new ArrayList<PGRanged>();

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public PGRanged(String name_) {
		super(name_);
	}

	public void setValue(double value) {
		System.out.println("value set "+this.getFullName());
		this.value=value;
	}

	public boolean isValueDetermined() {
		return value!=null;
	}

	@Override
	public double getValue() {
		if(value==null) {
			value=range.get();
		}
		return value;
	}

	@Override
	public PGBase clone() {
		PGRanged pgr=new PGRanged(this.getSimpleName());
		if(this.range!=null) {
			pgr.range=this.range.clone();
		}
		if(this.value!=null) {
			pgr.value=this.value.doubleValue();
		}
		return pgr;
	}

	@Override
	public boolean equalsWithoutName(Object obj) {
		if(obj instanceof PGRanged) {
			PGRanged pgr=(PGRanged)obj;
			if(this.value!=null && pgr.value!=null) {
				return this.value.doubleValue()==pgr.value.doubleValue();
			}
		}
		return false;
	}

	public static PGRanged getOrCreateFromFullpath(String path) {
		String[] names=path.split("\\.");
		PGObject pgb=null;
		for(String s:names) {
			//System.out.println(s);
			if(s.equals("root")) {
				pgb=Main.rootObject;
			}else {
				//System.out.println(s);
				if(pgb==null) {
					Main.logger.severe("Object "+s+" does not exist.");
				}
				PGBase child=pgb.getChild(s);

				if(child==null) {
					//System.out.println(PGType.NO_TYPE);
					child=new PGRanged(s);
					pgb.addChild(child);
					return (PGRanged) child;
				}else if(child instanceof PGRanged) {
					return (PGRanged) child;
				}else if(child instanceof PGObject){
					pgb=(PGObject) child;
					continue;
				}else {
					Main.logger.severe(child.getFullName()+" is not a Ranged Double! This is "
							+child.getClass().getSimpleName()+" instead.");
				}


			}

		}
		return null;
	}

	public List<PGRanged> getAffectTo() {
		return affectTo;
	}

	public List<PGRanged> getAffectFrom() {
		return affectFrom;
	}


}
