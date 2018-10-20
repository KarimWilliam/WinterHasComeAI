
public class SearchTreeNode {
	SearchTreeNode parentNode;
	String operator;
	int depth;
	int pathCost; 
	String[][] state1;
	int state2;
	int heuristic;
	SearchTreeNode(SearchTreeNode parentNode,String operator,int depth,int pathCost,String[][] state1,int state2, int heuristic) {
		// state2 is the number of shards Jon has.
		this.parentNode = parentNode;
		this.operator=operator;
		this.depth=depth;
		this.pathCost=pathCost;
		this.state1=state1;
		this.state2=state2;
		this.heuristic= heuristic;
		
	}

	public SearchTreeNode() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
	}
}

