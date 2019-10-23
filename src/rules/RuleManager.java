package rules;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import objects.basic.PGBase;

public class RuleManager {
	public List<RuleBase> rules=new ArrayList<RuleBase>();

	public List<RuleBase> getRules() {
		return rules;
	}

	public void addRules(RuleBase rb) {
		this.rules.add(rb);
		rb.getSubject().getRulesSubjected().add(rb);
		for(Object o:rb.getObjects()) {
			if(o instanceof PGBase) {
				((PGBase) o).getRulesObjected().add(rb);
			}
		}
	}
	public void solveAll() {
		List<RuleBase> list=new ArrayList<RuleBase>();
		int i;
		for(i=0;i<100;i++) {
			if(rules.isEmpty()) {
				break;
			}
			for(RuleBase pgr:this.rules) {
				if(pgr.isReadyToApply()) {
					list.add(pgr);
				}
			}
			rules.removeAll(list);
			for(RuleBase pgr:list) {
				solve(pgr);
			}
			list.clear();
		}
		if(i==100) {
			StringBuffer err=new StringBuffer("Cannot solve all Rules! Remaining rules:\n");
			for(RuleBase rb:rules) {
				err.append(rb.getClass().getSimpleName()+"\n");
			}
			Main.logger.severe(err.toString());

		}
	}

	public void solve(RuleBase node) {

		node.applyRule();

		try {
			System.out.println("solved "+node.getSubject().toString()+" -> "
				+node.getClass().getSimpleName()+" -> "+node.getObjects().get(0).toString());
		}catch (Exception e){
			System.out.println("solved "+node.getClass().getSimpleName());
		}
/*
		for(RuleBase pgr:node.getSubject().getRulesObjected()) {
			if(!pgr.isApplied()) {
				if(pgr.isReadyToApply()) {
					solve(pgr);
				}
			}
		}*/
	}
}
