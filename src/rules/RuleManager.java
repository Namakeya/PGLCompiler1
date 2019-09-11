package rules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RuleManager {
	public List<RuleBase> rules=new ArrayList<RuleBase>();
	
	public List<RuleBase> getRules() {
		return rules;
	}
	
	public void addRules(RuleBase rb) {
		this.rules.add(rb);
	}
	
	public void sortRules() {
		Comparator<RuleBase> c= new Comparator<RuleBase>() {

			@Override
			public int compare(RuleBase o1, RuleBase o2) {
				int i1,i2;
				if(o1 instanceof RuleIs) {
					i1=10;
				}else if(o1 instanceof RuleEquals) {
					i1=20;
				}else {
					i1=30;
				}
				if(o2 instanceof RuleIs) {
					i2=10;
				}else if(o2 instanceof RuleEquals) {
					i2=20;
				}else {
					i2=30;
				}
				return i2-i1;
			}
			
		};
		this.rules.sort(c);
	}
	
	public void applyRules() {
		for(RuleBase rb:this.rules) {
			rb.apply();
		}
	}
}
