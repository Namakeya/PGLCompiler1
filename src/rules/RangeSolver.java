package rules;

import java.util.ArrayList;
import java.util.List;

import objects.PGNumber;
import objects.PGVariable;

public class RangeSolver {
//Solve range to concrete value.
private List<PGVariable> nodes=new ArrayList<PGVariable>();
	
	public void addNode(PGVariable node) {
		this.nodes.add(node);
	}
	
	public List<PGVariable> getNodes(){
		return this.nodes;
	}
	
	public void setNodes( List<PGVariable> nodes) {
		this.nodes=nodes;
	}
	
	public void solve() {
		for(PGVariable pgv:this.nodes) {
			if(pgv instanceof PGNumber) {
				PGNumber pgn=((PGNumber)pgv);
				pgn.set(pgn.getRange().get());
			}
		}
	}
}
