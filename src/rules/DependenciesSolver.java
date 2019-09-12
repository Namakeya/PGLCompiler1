package rules;

import java.util.ArrayList;
import java.util.List;

import objects.PGVariable;

public class DependenciesSolver {
//Just a list now. Will be a solver of values/objects dependencies in further update.
	
	private List<PGVariable> nodes=new ArrayList<PGVariable>();
	
	public void addNode(PGVariable node) {
		//System.out.println("add "+node.getFullName());
		if(!this.nodes.contains(node)) {
			
			this.nodes.add(node);
		}
	}
	
	public List<PGVariable> getNodes(){
		return this.nodes;
	}
}
