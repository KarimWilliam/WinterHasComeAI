import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SaveWesteros extends GenericSearchProblem {

	
	final static int KillCost=1;
	final static int PickipCost=10;
	final static int moveCost = 2;
	final static int AStarMult = 4; // multiplier for the Heuristic function

	public SaveWesteros(String[] operators, Object initialState, Object[] stateSpace, Object goal, int pathCost) {
		super(operators, initialState, stateSpace, goal, pathCost);

	}

	// Return the root of the input child
	public static SearchTreeNode GetRoot(SearchTreeNode STN) {

		if (STN.parentNode == null)
			return STN;
		return GetRoot(STN.parentNode);
	}

//make sure state is not repeated in the ancestors of the node in question
	public static boolean CheckAnc(SearchTreeNode STN, String[][] Grid, int wep) {
		if (STN == null)
			return true;
		String[][] CurGrid = STN.state1;
		if (STN.state2 == wep) {
			if (Arrays.deepEquals(CurGrid, Grid)) {
				return false;
			}
		}
		if (STN.parentNode == null)
			return true;
		return CheckAnc(STN.parentNode, Grid, wep);
	}

	// The First Heuristic function: Global Manhattan
	public static int SetHeuristic1(String[][] Grid, int wep, int i, int j) {

		int Result = 0;
		for (int k = 0; k <= Grid.length - 1; k++) {
			for (int l = 0; l <= Grid[0].length - 1; l++) {

				if (wep > 0) {
					if (Grid[k][l] == "W") {
						Result += Math.abs(k - i) + Math.abs(l - j);
					}
				} else {
					if (Grid[k][l] == "D") {
						Result += Math.abs(k - i) + Math.abs(l - j);
					}
				}

			}

		}
		return Result;
	}

	// the 2nd Heuristic function: Manhattan for closest goal + dynamic goal
	public static int SetHeuristic2(String[][] Grid, int wep, int i, int j) {

		int ResultW = 100;
		int ResultD = 100;
		int cons = 0;
		for (int k = 0; k <= Grid.length - 1; k++) {
			for (int l = 0; l <= Grid[0].length - 1; l++) {

				cons = Math.abs(k - i) + Math.abs(l - j);
				if (ResultW > cons)
					ResultW = cons;
				cons = Math.abs(k - i) + Math.abs(l - j);
				if (ResultD > cons)
					ResultD = cons;

			}

		}
		if (wep == 3 || wep == 2)
			return ResultW;
		if (wep == 0)
			return ResultD;
		if (wep == 1) {
			if (ResultD > ResultW)
				return ResultW;
		} else
			return ResultD;
		return ResultW;
	}

	// STN = parent Node, Count = how many nodes have been evaluated, SType = search
	// strategy
	// TargetDepth = Target Depth for IDS Search
	public static SearchReturnType Search(ArrayList<SearchTreeNode> STN, int count, String SType, int TargetDepth) {

		// if the queue is empty return failure
		if (STN.get(0) == null) {
			System.out.println("No solution found");
			return null;
		}

		String[][] CState = STN.get(0).state1.clone();
		for (int i = 0; i < CState.length; i++) {
			CState[i] = STN.get(0).state1[i].clone();
		}
		int jonJ = 0;
		int jonI = 0;
		SearchTreeNode A = null, B = null, C = null, D = null, E = null, F = null;
		String up = null, down = null, right = null, left = null;

		// if top node on queue has no walkers return that node
		if (Arrays.deepToString(CState).contains("W") == false) {
			SearchReturnType Res = new SearchReturnType(STN.get(0), count);
			return Res;
		}

		// Find board size
		int rowLength = CState.length - 1;
		int colLength = CState[0].length - 1;

		// Find Jon
		for (int i = 0; i <= rowLength; i++) {
			for (int j = 0; j <= colLength; j++) {
				if (CState[i][j] == "J") {
					jonJ = j; // Right and left
					jonI = i; // up and down
				}
			}
		}

		// Find Jon's surroundings and make nodes for directional moves if applicable

		// RIGHT
		if (jonJ < colLength) {
			right = CState[jonI][jonJ + 1];
			if (right == "F") {
				String[][] R = CState.clone();
				for (int i = 0; i < R.length; i++) {
					R[i] = CState[i].clone();
				}
				R[jonI][jonJ] = "F";
				R[jonI][jonJ + 1] = "J";
				A = new SearchTreeNode(STN.get(0), "Right", STN.get(0).depth + 1, STN.get(0).pathCost + moveCost, R,
						STN.get(0).state2, 0);
			}
		}

		// LEFT
		if (jonJ > 0) {
			left = CState[jonI][jonJ - 1];
			if (left == "F") {
				String[][] L = CState.clone();
				for (int i = 0; i < L.length; i++) {
					L[i] = CState[i].clone();
				}
				L[jonI][jonJ] = "F";
				L[jonI][jonJ - 1] = "J";
				B = new SearchTreeNode(STN.get(0), "Left", STN.get(0).depth + 1, STN.get(0).pathCost + moveCost, L,
						STN.get(0).state2, 0);
			}
		}

		// UP
		if (jonI > 0) {
			up = CState[jonI - 1][jonJ];
			if (up == "F") {
				String[][] U = CState.clone();
				for (int i = 0; i < U.length; i++) {
					U[i] = CState[i].clone();
				}
				U[jonI][jonJ] = "F";
				U[jonI - 1][jonJ] = "J";
				C = new SearchTreeNode(STN.get(0), "Up", STN.get(0).depth + 1, STN.get(0).pathCost + moveCost, U,
						STN.get(0).state2, 0);
			}
		}

		// DOWN
		if (jonI < rowLength) {
			down = CState[jonI + 1][jonJ];
			if (down == "F") {
				String[][] Do = CState.clone();
				for (int i = 0; i < Do.length; i++) {
					Do[i] = CState[i].clone();
				}
				Do[jonI][jonJ] = "F";
				Do[jonI + 1][jonJ] = "J";
				D = new SearchTreeNode(STN.get(0), "Down", STN.get(0).depth + 1, STN.get(0).pathCost + moveCost, Do,
						STN.get(0).state2, 0);
			}
		}

		// Make KILL node
		if ((up == "W" || down == "W" || right == "W" || left == "W") && STN.get(0).state2 > 0) {
			String[][] newState = CState.clone();
			for (int i = 0; i < newState.length; i++) {
				newState[i] = CState[i].clone();
			}

			if (up == "W")
				newState[jonI - 1][jonJ] = "F";
			if (down == "W")
				newState[jonI + 1][jonJ] = "F";
			if (right == "W")
				newState[jonI][jonJ + 1] = "F";
			if (left == "W")
				newState[jonI][jonJ - 1] = "F";

			E = new SearchTreeNode(STN.get(0), "Kill", STN.get(0).depth + 1, STN.get(0).pathCost + KillCost, newState,
					STN.get(0).state2 - 1, 0);

			if (Arrays.deepToString(newState).contains("W") == false) {
				SearchReturnType Res = new SearchReturnType(E, count);
				return Res;
			}
		}

		//Make Pickup node
		if ((up == "D" || down == "D" || right == "D" || left == "D") && STN.get(0).state2 < 3) {
			String[][] newState2 = CState.clone();
			for (int i = 0; i < newState2.length; i++) {
				newState2[i] = CState[i].clone();
			}
			F = new SearchTreeNode(STN.get(0), "PickUp", STN.get(0).depth + 1, STN.get(0).pathCost + PickipCost, newState2, 3,
					0);
		}

		// Breadth First Search

		if (SType == "BF") {
			if (A != null && CheckAnc(A.parentNode, A.state1, A.state2))
				STN.add(A);
			if (B != null && CheckAnc(B.parentNode, B.state1, B.state2))
				STN.add(B);
			if (C != null && CheckAnc(C.parentNode, C.state1, C.state2))
				STN.add(C);
			if (D != null && CheckAnc(D.parentNode, D.state1, D.state2))
				STN.add(D);
			if (E != null)
				STN.add(E);
			if (F != null)
				STN.add(F);
			STN.remove(0); // pop the queue
			count++;

		} else if (SType == "UC") {
			STN.remove(0); // pop the queue


			// Uniform Cost Search
			if (E != null) {

				if (STN.isEmpty()) {
					STN.add(E);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || E.pathCost >= STN.get(STN.size()-1).pathCost) {
							STN.add(E);
							break;
						}
						if (E.pathCost < STN.get(i).pathCost) {
							STN.add(i, E);
							break;
						}
					}

				}
			}
			if (F != null) {

				if (STN.isEmpty()) {
					STN.add(F);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || F.pathCost >=STN.get(STN.size()-1).pathCost) {
							STN.add(F);
							break;
						}
						if (F.pathCost < STN.get(i).pathCost) {
							STN.add(i, F);
							break;
						}
					}

				}
			}
			if (A != null && CheckAnc(A.parentNode, A.state1, A.state2)) {

				if (STN.isEmpty()) {
					STN.add(A);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || A.pathCost >= STN.get(STN.size()-1).pathCost) {
							STN.add(A);
							break;
						}
						if (A.pathCost < STN.get(i).pathCost) {
							STN.add(i, A);
							break;
						}
					}

				}
			}

			if (B != null && CheckAnc(B.parentNode, B.state1, B.state2)) {

				if (STN.isEmpty()) {
					STN.add(B);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || B.pathCost >= STN.get(STN.size()-1).pathCost) {
							STN.add(B);
							break;
						}
						if (B.pathCost < STN.get(i).pathCost) {
							STN.add(i, B);
							break;
						}
					}

				}
			}

			if (C != null && CheckAnc(C.parentNode, C.state1, C.state2)) {

				if (STN.isEmpty()) {
					STN.add(C);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || C.pathCost >= STN.get(STN.size()-1).pathCost) {
							STN.add(C);
							break;
						}
						if (C.pathCost < STN.get(i).pathCost) {
							STN.add(i, C);
							break;
						}
					}

				}
			}

			if (D != null && CheckAnc(D.parentNode, D.state1, D.state2)) {

				if (STN.isEmpty()) {
					STN.add(D);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || D.pathCost >=STN.get(STN.size()-1).pathCost) {
							STN.add(D);
							break;
						}
						if (D.pathCost < STN.get(i).pathCost) {
							STN.add(i, D);
							break;
						}
					}

				}
			}
			count++;

			
		// Depth First Search
		} else if (SType == "DF") {

			STN.remove(0); // pop the queue

			if (A != null && CheckAnc(A.parentNode, A.state1, A.state2))
				STN.add(0, A);
			if (B != null && CheckAnc(B.parentNode, B.state1, B.state2))
				STN.add(0,B);
			if (C != null && CheckAnc(C.parentNode, C.state1, C.state2))
				STN.add(0,C);
			if (D != null && CheckAnc(D.parentNode, D.state1, D.state2))
				STN.add(0,D);
			if (E != null)
				STN.add(0,E);
			if (F != null)
				STN.add(0,F);

			count++;

		} else if (SType == "ID") {

			

				SearchTreeNode x= STN.remove(0); // pop the queue

				if (A != null && CheckAnc(A.parentNode, A.state1, A.state2) && TargetDepth>=A.depth)
					STN.add(0, A);
				if (B != null && CheckAnc(B.parentNode, B.state1, B.state2)&& TargetDepth>=B.depth)
					STN.add(0, B);
				if (C != null && CheckAnc(C.parentNode, C.state1, C.state2)&& TargetDepth>=C.depth)
					STN.add(0, C);
				if (D != null && CheckAnc(D.parentNode, D.state1, D.state2)&& TargetDepth>=D.depth)
					STN.add(0, D);
				if (E != null && TargetDepth>=E.depth)
					STN.add(0, E);
				if (F != null && TargetDepth>=F.depth)
					STN.add(0, F);

				count++;
				if (!STN.isEmpty()) {
				return Search(STN, count, SType, TargetDepth); // consider the next node in the front of the queue

			} else {
				TargetDepth++;
				SearchTreeNode root = new SearchTreeNode();
				root = GetRoot(x);
				STN.clear();
				// System.out.println(root.);
				STN.add(root);
				return Search(STN, count, SType, TargetDepth);
			}

			
			// GREEDY SEARCH & A*
			// For killing and picking weapons adjacent to Jon H=0;
		} else if ((SType == "GR1") || (SType == "GR2") || (SType == "AS1" || SType == "AS2")) {
			STN.remove(0); // pop the queue

			if (E != null) {
				if (SType == "AS1" || SType == "AS2")
					E.heuristic = E.pathCost;

				if (STN.isEmpty()) {
					STN.add(E);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || E.heuristic >= STN.get(STN.size() - 1).heuristic) {
							STN.add(E);
							break;
						}
						if (E.heuristic < STN.get(i).heuristic) {
							STN.add(i, E);
							break;
						}
					}

				}
			}
			if (F != null) {

				if (SType == "AS1" || SType == "AS2")
					F.heuristic = F.pathCost;
				if (STN.isEmpty()) {
					STN.add(F);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || F.heuristic >= STN.get(STN.size() - 1).heuristic) {
							STN.add(F);
							break;
						}
						if (F.heuristic < STN.get(i).heuristic) {
							STN.add(i, F);
							break;
						}
					}

				}
			}
			if (A != null && CheckAnc(A.parentNode, A.state1, A.state2)) {

				if (SType == "GR1" || SType == "AS1")
					A.heuristic = SetHeuristic1(A.state1, A.state2, jonI, jonJ + 1);
				else
					A.heuristic = SetHeuristic2(A.state1, A.state2, jonI, jonJ + 1);
				if (SType == "AS1" || SType == "AS2")
					A.heuristic = (A.heuristic * AStarMult) + A.pathCost;

				if (STN.isEmpty()) {
					STN.add(A);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || A.heuristic >= STN.get(STN.size() - 1).heuristic) {
							STN.add(A);
							break;
						}
						if (A.heuristic < STN.get(i).heuristic) {
							STN.add(i, A);
							break;
						}
					}

				}
			}

			if (B != null && CheckAnc(B.parentNode, B.state1, B.state2)) {

				if (SType == "GR1" || SType == "AS1")
					B.heuristic = SetHeuristic1(B.state1, B.state2, jonI, jonJ - 1);
				else
					B.heuristic = SetHeuristic2(B.state1, B.state2, jonI, jonJ - 1);
				if (SType == "AS1" || SType == "AS2")
					B.heuristic = (B.heuristic * AStarMult) + B.pathCost;

				if (STN.isEmpty()) {
					STN.add(B);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || B.heuristic >= STN.get(STN.size() - 1).heuristic) {
							STN.add(B);
							break;
						}
						if (B.heuristic < STN.get(i).heuristic) {
							STN.add(i, B);
							break;
						}
					}

				}
			}

			if (C != null && CheckAnc(C.parentNode, C.state1, C.state2)) {

				if (SType == "GR1" || SType == "AS1")
					C.heuristic = SetHeuristic1(C.state1, C.state2, jonI - 1, jonJ);
				else
					C.heuristic = SetHeuristic2(C.state1, C.state2, jonI - 1, jonJ);
				if (SType == "AS1" || SType == "AS2")
					C.heuristic = (C.heuristic * AStarMult) + C.pathCost;

				if (STN.isEmpty()) {
					STN.add(C);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || C.pathCost >= STN.get(STN.size() - 1).pathCost) {
							STN.add(C);
							break;
						}
						if (C.pathCost < STN.get(i).pathCost) {
							STN.add(i, C);
							break;
						}
					}

				}
			}

			if (D != null && CheckAnc(D.parentNode, D.state1, D.state2)) {

				if (SType == "GR1" || SType == "AS1")
					D.heuristic = SetHeuristic1(D.state1, D.state2, jonI + 1, jonJ);
				else
					D.heuristic = SetHeuristic1(D.state1, D.state2, jonI + 1, jonJ);
				if (SType == "AS1" || SType == "AS2")
					D.heuristic = (D.heuristic * AStarMult) + D.pathCost;

				if (STN.isEmpty()) {
					STN.add(D);
				} else {
					for (int i = 0; i < STN.size(); i++) {
						if (STN.size() == i + 1 || D.heuristic >= STN.get(STN.size() - 1).heuristic) {
							STN.add(D);
							break;
						}
						if (D.heuristic < STN.get(i).heuristic) {
							STN.add(i, D);
							break;
						}
					}

				}
			}
			count++;
		}

		return Search(STN, count, SType, 0); // consider the next node in the front of the queue
	}

}
