import java.util.ArrayList;

public class FinalReturn {

	ArrayList<String> moves;
	int pathcost;
	int expansion;

	FinalReturn(ArrayList<String> moves, int pathcost, int expansion) {
		this.pathcost = pathcost; //the cost of reaching this goal node.
		this.expansion = expansion; //The number of nodes considered during the search.
		this.moves = moves; // an ArrayList containing all the operations needed to reach the goal.
	}

}
