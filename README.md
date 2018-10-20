# WinterHasComeAI
Project Description:
More than eight thousand years ago, the longest winter in history fell on the continent
of Westeros. During this dark time, humanoid ice creatures known as the white walkers
swept through Westeros riding on their dead horses and hunting everything living.
Eventually, the people of Westeros rallied against the white walkers and forced them
to the north. A great wall was built to bar the return of the white walkers ever again.
The wall stood tall for thousands of years protecting all living things until the white
walkers finally succeeded in bringing it down. Now a long and deadly winter has come
to Westeros once again.
As a last strive for survival, the King in the North Jon Snow gathered all remaining
magic in Westeros to freeze the white walkers in their places in a big field right after
they crossed the fallen wall. The white walkers must be exterminated before they find
a way to unfreeze bringing unstoppable doom to Westeros. In order not to provoke the
white walkers causing them to break free, only Jon Snow must enter the field where they
are frozen in place to kill them. White walkers can be killed by stabbing them with a
form of volcanic glass commonly known as dragonglass which can be obtained from a
special stone called the dragonstone.
In this project, you will use search to help Jon Snow save Westeros. The field where
the white walkers are frozen in place can be thought of as an m × n grid of cells where
m, n ≥ 4 . A grid cell is either free or contains one of the following: a white walker,
Jon Snow, the dragonstone, or an obstacle. Jon Snow can move in the four directions
as long as the cell in the direction of movement does not contain an obstacle or a living
white walker. To obtain the dragonglass by which the white walkers can be killed, Jon
has to go to the cell where the dragonstone lies to pick up a fixed number of pieces
of dragonglass that he can carry. In order to kill a white walker, Jon has to be in an
adjacent cell. An adjacent cell is a cell that lies one step to the north, south, east, or west.
With a single move using the same piece of dragonglass, Jon can kill all adjacent white
walkers. If Jon steps out of a cell where he used a piece of dragonglass to kill adjacent
walkers, that piece of dragonglass becomes unusable. Once a white walker is killed, Jon
can move through the cell where the walker was. If Jon runs out of dragonglass before
all the white walkers are killed, he has to go back to the dragonstone to pick up more
pieces of dragonglass. Using search you should formulate a plan that Jon can follow to
kill all the white walkers. An optimal plan is one where Jon uses the least number of
pieces of dragonglass to kill all the white walkers. The following search algorithms will
be implemented and each will be used to help Jon

a) Breadth-first search.
b) Depth-first search.
c) Iterative deepening search.
d) Uniform-cost search.
e) Greedy search with at least two heuristics.
f) A∗
search with at least two admissible heuristics.

# TLDR

 We want to use a search agent to delete all enemies (walkers) off of a NxM randomly generated 2d grid that also contains weapons (shards) and immovable obstacles. The agent is able to kill the walkers by being adjacent to them and expending one of his shards. Expending a shard kills all walkers in adjacent cells. Max shards the agent can carry at any time is 2-5 shards but constant during a run. The agent can also interact with Shard depositories on the grid by being adjacent to them to replenish his shard count to max. The agent starts with zero shards in his inventory. The agent can’t move diagonally. The agent always starts in the bottom right corner of the grid.  Once the map has no walkers the problem is considered solved. The agent employs the different search strategies above.

# HOW TO RUN:
Import the project to your Java IDE and make sure your memory parameter is set high enough to handle bigger boards without running out of memory otherwise stick with 4x4 boards max.
Go to “GOTMain” class, in the main method, and call the “Search” Function as defined below.  The first parameter is the grid (you can select one of the static ones made above in the main method or create a random one with the GenerateGrid() method). The 2nd parameter is the search type and the final parameter is visualization. 
If the visualization is set to true, the output will be shown in console. 
In case you want to manipulate the output in anyway, refer to the FinalReturn class for details about the output format.


• Search(grid, strategy, visualize) uses search to try to formulate a winning plan:
– grid is a grid to perform the search on,
– strategy is a symbol indicating the search strategy to be applied:
∗ BF for breadth-first search,
∗ DF for depth-first search,
∗ ID for iterative deepening search,
∗ UC for uniform cost search,
∗ GRi for greedy search, with i ∈ {1, 2} distinguishing the two heuristics, and
∗ ASi for A∗
search, with i ∈ {1, 2} distinguishing the two heuristics.
– visualize is a Boolean parameter which, when set to t, results in 
side-effecting a visual presentation of the grid as it undergoes the different steps
of the discovered solution (if one was discovered).
The function returns a list of three elements, in the following order: (i) a representation
of the sequence of moves to reach the goal (if possible), (ii) the cost of the
solution computed, and (iii) the number of nodes chosen for expansion during the
search.
