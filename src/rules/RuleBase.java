package rules;

import java.util.ArrayList;
import java.util.List;

import objects.basic.PGBase;

public abstract class RuleBase {
	private PGBase subject;
	private List<Object> objects;
	private boolean isApplied;

	public RuleBase(PGBase subjects , List<Object> objects) {
		this.subject=subjects;
		this.objects=objects;
		this.setApplied(false);
	}

	public RuleBase(PGBase subject,Object[] objects) {

		List<Object> oblist=new ArrayList<Object>();
		for(Object ob:objects) {
			oblist.add(ob);
		}
		this.subject=subject;
		this.objects=oblist;
		this.setApplied(false);
	}

	public RuleBase(PGBase subject,Object object) {
		this(subject,new Object[] {object});
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

	public List<Object> getObjects() {
		return objects;
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
