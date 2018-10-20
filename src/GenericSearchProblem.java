
public abstract class GenericSearchProblem {

	Object[] operators;
	Object initialState;
	Object[] stateSpace;
	Object goal;
	int pathCost;

	public GenericSearchProblem(Object[] operators, Object initialState, Object[] stateSpace, Object goal,
			int pathCost) {
		this.operators = operators;
		this.initialState = initialState;
		this.stateSpace = stateSpace;
		this.goal = goal;
		this.pathCost = pathCost;
	}
}

//a) How would you represent a state in this problem?
//
//		the 2d char array + the number of shards Jon has left on him
//b) What is the initial state?
//		
//		jon starts with 3 shards in the bottom right corner and the rest of the 2d array is randomly generated
//		
//c) What is the goal test?
//		
//		0 walkers left on 2d array
//		
//d) What are the operators of the problem?
//		
//		Right
//		Left
//		Up
//		Down
//		Kill all adjacent
//		Pick up shards 
//		
//e) What is a suitable path cost function?
//		
//		pick up shards when i have less than max shards: +2 
//		Kill when walker is adjacent: +1 for each walker 
//		move in any direction: +20