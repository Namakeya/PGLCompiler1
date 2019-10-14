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

	public PGRanged(String name_) {
		super(name_);
	}
	
	public PGRanged() {
		this("");
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}


	public void setValue(double value) {
		Main.logger.fine("value of "+this.getFullName()+" was set to "+value);
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

	public List<PGRanged> getAffectTo() {
		return affectTo;
	}

	public List<PGRanged> getAffectFrom() {
		return affectFrom;
	}


}
