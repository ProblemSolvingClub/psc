// generally a good idea to import both of these
import java.util.Scanner;
import java.util.*;

// This solves the BFS maze escape problem from the first week.
// It is written to be understandable, not fast. However, it is
// as asymptotically efficient as any other implementation using
// BFS.
public class BFS {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// reads in rows and columns
		int rows, cols;
		rows = input.nextInt();
		cols = input.nextInt();
		input.nextLine();
		// runs until termination conditions satisfied
		while (rows != 0 && cols != 0) {
			// declares maze
			char[][] maze = new char[rows][cols];
			// declares source location
			State source = null;
			// reads input into maze array
			for (int i = 0; i < rows; i++) {
				String row = input.nextLine();
				for (int j = 0; j < cols; j++) {
					char square = row.charAt(j);
					// checks if source
					if (square == 's') {
						source = new State(i, j, 0);
					}
					maze[i][j] = square;
				}
			}
			// runs BFS, prints out steps to exit
			System.out.println(search(maze, source));
			// reads in next rows and columns
			rows = input.nextInt();
			cols = input.nextInt();
			input.nextLine();
		}
	}

	// performs a breadth-first search of the maze 
	public static int search(char[][] maze, State source) {
		// our data structure T will be the maze itself
		// declares new queue
		Queue<State> Q = new LinkedList<State>();
		// pushes source to Q
		Q.offer(source);
		// marks source as visited
		maze[source.xPos][source.yPos] = '#';
		// enters main loop - checks if Q is nonempty
		while (Q.peek() != null) {
			// gets next element off of queue
			State current = Q.poll();	
			// gets neighbors of current
			ArrayList<State> neighbors = getNeighbors(maze, current);
			// iterates through set of all neighbors (nice java syntax)
			for (State neighbor : neighbors) {
				// checks for exit
				if (maze[neighbor.xPos][neighbor.yPos] == 'x') {
					return neighbor.dist;
				}
				// we know every neighbor in neighbors is unvisited - why?
				Q.offer(neighbor);
				// marks neighbor as visited in array
				maze[neighbor.xPos][neighbor.yPos] = '#';
			}
		}
		// exit is not reachable
		return 0;
	}

	public static ArrayList<State> getNeighbors(char[][] maze, State current) {
		ArrayList<State> neighbors = new ArrayList<State>();
		// checks North
		if (current.yPos > 0 && maze[current.xPos][current.yPos-1] != '#') {
			neighbors.add(new State(current.xPos, current.yPos-1, current.dist+1));
		}
		// checks East
		if (current.xPos < maze.length-1 && maze[current.xPos+1][current.yPos] != '#') {
			neighbors.add(new State(current.xPos+1, current.yPos, current.dist+1));
		}
		// checks South
		if (current.yPos < maze[0].length-1 && maze[current.xPos][current.yPos+1] != '#') {
			neighbors.add(new State(current.xPos, current.yPos+1, current.dist+1));
		}
		// checks West
		if (current.xPos > 0 && maze[current.xPos-1][current.yPos] != '#') {
			neighbors.add(new State(current.xPos-1, current.yPos, current.dist+1));
		}
		return neighbors;
	}

	// why is this class a good idea?
	private static class State {
		public int xPos, yPos, dist;

		public State(int xPos, int yPos, int dist) {
			this.xPos = xPos;
			this.yPos = yPos;
			this.dist = dist;
		}
	}
}
