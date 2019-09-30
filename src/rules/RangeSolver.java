package rules;

import java.util.ArrayList;
import java.util.List;

import main.Main;
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

	public void solveAll() {
		List<PGRanged> list=new ArrayList<PGRanged>();
		for(PGRanged pgr:this.nodes) {
			if(pgr.getAffectFrom().isEmpty()) {
				list.add(pgr);
			}
		}
		for(PGRanged pgr:this.nodes) {
			Main.logger.fine(pgr.getFullName()+" range: "+pgr.getRange().getMin()+"~"+pgr.getRange().getMax());
			solve(pgr);
		}
	}
	
	public void solve(PGRanged node) {
		node.setValue(node.getRange().get());
		for(PGRanged pgr:node.getAffectTo()) {
			if(!pgr.isValueDetermined()) {
				boolean flag=false;
				for(PGRanged aff:pgr.getAffectFrom()) {
					if(!aff.isValueDetermined()) {
						flag=true;
						break;
					}
				}
				if(!flag) {
					solve(pgr);
				}
			}
		}
	}
}
