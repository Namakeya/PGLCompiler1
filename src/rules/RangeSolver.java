package rules;

import java.util.ArrayList;
import java.util.List;

import objects.basic.PGRanged;

public class RangeSolver {
//Solve range to concrete value.
private List<PGRanged> nodes=new ArrayList<PGRanged>();

	public void addNode(PGRanged node) {
		if(!this.nodes.contains(node)) {
			this.nodes.add(node);
		}
	}

	public List<PGRanged> getNodes(){
		return this.nodes;
	}

	public void setNodes( List<PGRanged> nodes) {
		this.nodes=nodes;
	}

	public void solve() {
		for(PGRanged pgv:this.nodes) {
			System.out.println(pgv.getFullName()+" "+pgv.getRange().getMin()+"~"+pgv.getRange().getMax());
			pgv.setValue(pgv.getRange().get());
		}
	}
}
