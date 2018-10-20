import java.util.*;


public class GOTMain {
	int WhiteWalker = 0;
	int DragonGlass = 1;
	int Obstacle = 2;
	int FreeCell = 3;
	static int Jon = 5;

	public static void main(String[] args) {


		String[][] testGrid=  {{"W","W","W","D"}, {"W","F","F","F"}, {"F","O","D","F"}, {"W","F","F","J"} };
		String[][] testGrid2=  {{"W","D"}, {"F","J"}};
		String[][] testGrid3=  {{"W","D","W"}, {"F","F","F"},{"D","F","J"}};
		String[][] testGrid4= {{"F","F","F","F","O"},{"D","F","W","F","J"}}; //nxm
		String[][] testGrid5=  {{"W","W","F","F","W"}, {"W","F","F","F","F"}, {"F","W","D","F","W"}, {"W","F","F","O","F"},{"W","F","D","F","J"} };
		String[][] testGrid6=  {{"F","F","F","D","W"}, {"F","F","F","F","F"}, {"F","O","D","F","W"}, {"F","F","F","F","F"},{"W","F","D","F","J"} };// easy?
		String[][] testGrid7=  {{"W","W","W","W"}, {"W","W","W","W"}, {"W","W","D","W"}, {"F","W","F","J"} };
		String[][] testGrid8=  {{"D","W","W","W"}, {"F","F","F","W"}, {"F","D","O","F"}, {"J","F","F","W"} };
		
		String[][] GG = GenerateGrid(5, 4);
		FinalReturn FR= Search(testGrid5,"UC",true);

	}

	public static String[][] GenerateGrid(int m, int n) {

		if (m >= 4 && n >= 4) {

			String[][] grid = new String[m][n];


			for (int i = 0; i <= m - 1; i++) {
				for (int j = 0; j <= n - 1; j++) {

					int v = new Random().nextInt(4);

					if (v == 0) {
						grid[i][j] = "W";
					}
					if (v == 1) {
						grid[i][j] = "D";
					}
					if (v == 2) {
						grid[i][j] = "O";
					}
					if (v == 3) {
						grid[i][j] = "F";
					}

				}

			}
			grid[m - 1][n - 1] = "J";
			return grid;
		}
		return null;

	}

	public static void ShowGrid(SearchTreeNode c) {
		//System.out.println(Arrays.deepToString(c));
		if(c==null) {
			System.out.println("We failed to find a solution for this board");
			return;
		}
		
		
		for (int i = 0; i <= c.state1.length - 1; i++) {
			System.out.print("|");
			for (int j = 0; j <= c.state1[0].length - 1; j++) {
				
				if (c.state1[i][j] == "W") {
					System.out.print(" W |");
				}
				if (c.state1[i][j] == "D") {
					System.out.print(" D |");
				}
				if (c.state1[i][j] == "O") {
					System.out.print(" O |");
				}
				if (c.state1[i][j] == "F") {
					System.out.print(" F |");
				}
				if (c.state1[i][j] == "J") {
					System.out.print(" J |");

				}

			}
			System.out.println();
		}
System.out.println("Jon has  " + c.state2 + " shard(s)");
	}
	
	public static void SimpleShowGrid(String[][] c) {
		//System.out.println(Arrays.deepToString(c));
		if(c==null) {
			System.out.println("We failed to find a solution for this board");
			return;
		}
		
		
		for (int i = 0; i <= c.length - 1; i++) {
			System.out.print("|");
			for (int j = 0; j <= c[0].length - 1; j++) {
				
				if (c[i][j] == "W") {
					System.out.print(" W |");
				}
				if (c[i][j] == "D") {
					System.out.print(" D |");
				}
				if (c[i][j] == "O") {
					System.out.print(" O |");
				}
				if (c[i][j] == "F") {
					System.out.print(" F |");
				}
				if (c[i][j] == "J") {
					System.out.print(" J |");

				}

			}
			System.out.println();
		}

	}
	
	public static FinalReturn Search(String[][] grid, String strategy, Boolean visualize){
		
		if(strategy != "UC" && strategy!="BF" && strategy!="ID" && strategy !="DF" && 
				strategy !="GR1" && strategy != "GR2" && strategy!="AS1" && strategy != "AS2") {
			System.out.println("Please enter a valid strategy \n Options: UC"
					+ " - BF - DF - ID - GR1 - GR2 - AS1 - AS2");
			return null;
		}
		
		SimpleShowGrid(grid); System.out.println();
		SearchTreeNode TestNode = new SearchTreeNode(null, "", 0, 0 , grid,0,0);
		SearchReturnType Result = null;
		ArrayList<SearchTreeNode> STN = new ArrayList<SearchTreeNode>();
		STN.add(TestNode);
		Result = SaveWesteros.Search(STN,0,strategy,1);


		if(visualize) {
			ShowParents(Result.ResSTN);
		}

		System.out.println("Cost: "+ Result.ResSTN.pathCost + " Depth: "+ Result.ResSTN.depth + " Search: " + strategy);
		System.out.println("Number of expanded nodes: " + Result.Expansion);
		
			
		
		ArrayList<String> moves = new ArrayList<String>();
		moves= MakeMovesArray(Result.ResSTN,moves);
		int cost=Result.ResSTN.pathCost;
		int expansion= Result.Expansion;

		return new FinalReturn(moves,cost,expansion);
		
	}
	
	public static void ShowParents(SearchTreeNode STN){
		if(STN.parentNode==null)return;		
		ShowParents(STN.parentNode);
		ShowGrid(STN);
		System.out.println("Move: " + STN.operator);
		System.out.println();
	}
	
	public static ArrayList<String> MakeMovesArray(SearchTreeNode STN, ArrayList<String> ListArray) {
		if(STN.parentNode==null) {
			ListArray.add(STN.operator);
			return ListArray;		
		}
		ListArray.add(STN.operator);
		return MakeMovesArray(STN.parentNode, ListArray);

	}
	
	

}
