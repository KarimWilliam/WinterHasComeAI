
public class AbstractTreeNode {
	SearchTreeNode parentNode;
	String operator;
	int depth;
	int pathCost; 
	Object state;

	AbstractTreeNode(SearchTreeNode parentNode,String operator,int depth,int pathCost,Object state) {
		this.parentNode = parentNode;
		this.operator=operator;
		this.depth=depth;
		this.pathCost=pathCost;
		this.state=state;
		
	}



}

