package rules;

import java.util.ArrayList;
import java.util.List;

import objects.basic.PGDouble;
import objects.basic.PGRanged;
import objects.function.PGFunction;

public class DependenciesSolver {

	private List<Node> nodes=new ArrayList<Node>();

	public void addNode(PGRanged subject,PGDouble object) {
		//System.out.println("add "+node.getFullName());
		Node node=new Node(subject,object);
		if(!this.nodes.contains(node)) {

			this.nodes.add(node);
		}
	}

	/**solve dependencies among PGRanged.
	 * @param sbj target
	 * @param pgd element relative to target.
	 * ex) a < target < b then run solve(target,a) and solve(target,b)
	 */
	public void solve(PGRanged sbj,PGDouble pgd) {
		if(pgd instanceof PGRanged) {
			PGRanged pgr=(PGRanged) pgd;
			sbj.getAffectFrom().add(pgr);
			pgr.getAffectTo().add(sbj);
		}else if(pgd instanceof PGFunction) {
			PGFunction pgf=(PGFunction)pgd;
			for(int i=0;i<pgf.numOfParameters();i++) {
				solve(sbj,pgf.getParams()[i]);
			}
		}else {//pgd instanceof PGLiteral
			return;
		}
	}

	public void solveAll() {
		for(Node node:nodes) {
			solve(node.subject,node.object);
			for(PGRanged pgr:node.subject.getAffectFrom()) {
				System.out.println(pgr.getFullName());
			}
			System.out.println("->"+node.subject.getFullName());
		}
	}

	public List<Node> getNodes(){
		return this.nodes;
	}

	static public class Node
	{
		public Node(PGRanged subject,PGDouble object) {
			this.subject=subject;
			this.object=object;
		}
		public PGRanged subject;
		public PGDouble object;
	}
}
