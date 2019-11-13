package rules;

import java.util.ArrayList;
import java.util.List;

import objects.basic.PGBase;

public abstract class RuleBase {
	private PGBase subject;
	private List<Object> objects;
	private boolean isApplied;

	public RuleBase() {
		this.setApplied(false);
	}

	public void applyRule() {
		apply();
		this.setApplied(true);
	}
	protected abstract void apply() ;
	//public abstract boolean check();

	public PGBase getSubject() {
		return subject;
	}

	public void setSubject(PGBase subject) {
		this.subject=subject;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects=objects;
	}

	public void setObjects(Object[] objects) {
		List<Object> oblist=new ArrayList<Object>();
		for(Object ob:objects) {
			oblist.add(ob);
		}
		this.objects=oblist;
	}

	public void setObject(Object object) {
		List<Object> oblist=new ArrayList<Object>();
		oblist.add(object);
		setObjects(oblist);
	}

	public boolean isReadyToApply() {
		for(Object o:this.objects) {
			if(o instanceof PGBase) {
				if(!((PGBase) o).isSolved()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isApplied() {
		return isApplied;
	}

	public void setApplied(boolean isApplied) {
		this.isApplied = isApplied;
	}

}
