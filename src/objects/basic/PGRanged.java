package objects.basic;

import rules.Range;

public class PGRanged extends PGDouble{

	private Double value;
	private Range range=new Range();

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
		this.value=value;
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
		return null;
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

}
