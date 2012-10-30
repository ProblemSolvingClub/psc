import java.util.*;
import java.math.BigInteger;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	
		int n = in.nextInt();
		if (n == 1) {
			System.out.println("1");
		}
		else {
			in.nextLine();
			char[][] grid = new char[n][n];
			for (int i = 0; i < n; ++i) {
				String line = in.nextLine();	
				for (int j = 0; j < n; ++j) {
					grid[i][j] = line.charAt(j);
				}
			}
			if (PathExists(grid, n)) {
				int numPaths = PathCount(grid, n);
				if (numPaths != 0)
					System.out.println(numPaths);
				else
					System.out.println("THE GAME IS A LIE");
			}
			else {
				System.out.println("INCONCEIVABLE");
			}
		}
	}

	static int PathCount(char[][] grid, int n) {
		BigInteger[][] paths = new BigInteger[n][n];
		paths[0][0] = BigInteger.ONE;
		boolean reachable = true;
		for (int i = 1; i < n; ++i) {
			if (reachable) {
				if (grid[0][i] == '#') {
					reachable = false;
					paths[0][i] = BigInteger.ZERO;
				}
				else {
					paths[0][i] = paths[0][i-1];
				}
			}
			else {
				paths[0][i] = BigInteger.ZERO;
			}
		}
		reachable = true;
		for (int i = 1; i < n; ++i) {
			if (reachable) {
				if (grid[i][0] == '#') {
					reachable = false;
					paths[i][0] = BigInteger.ZERO;
				}
				else {
					paths[i][0] = paths[i-1][0];
				}
			}
			else {
				paths[i][0] = BigInteger.ZERO;
			}
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				if (grid[i][j] == '#') {
					paths[i][j] = BigInteger.ZERO;	
				}
				else {
					BigInteger north = paths[i][j-1];
					BigInteger west = paths[i-1][j];
					BigInteger result = BigInteger.ZERO;
					if (!north.equals(BigInteger.ZERO))
						result = result.add(north);
					if (!west.equals(BigInteger.ZERO))
						result = result.add(west);
					paths[i][j] = result;
				}
			}
		}
		/*
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				System.out.printf("%d ", paths[i][j].intValue());
			}
			System.out.println();
		}
		*/
		BigInteger result = paths[n-1][n-1].mod(BigInteger.valueOf((1<<31)-1));		
		return result.intValue();
	}

	static boolean PathExists(char[][] grid, int n) {
		boolean[][] visited = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == '#')
					visited[i][j] = true;
				else
					visited[i][j] = false;
			}
		}
		visited[0][0] = true;
		Queue<State> Q = new LinkedList<State>();
		State source = new State(0,0);
		Q.offer(source);
		while (Q.peek() != null) {
			State current = Q.poll();
			ArrayList<State> adjacent = Neighbours(grid, visited, current);
			for (State adj : adjacent) {
				if (adj.x == n-1 && adj.y == n-1)
					return true;
				Q.offer(adj);
				visited[adj.x][adj.y] = true;
			}
		}
		return false;
	}

	static ArrayList<State> Neighbours(char[][] grid, boolean[][] visited, State pos) {
		ArrayList<State> adjacent = new ArrayList<State>();
		// checks North
		if (pos.y > 0 && !visited[pos.x][pos.y-1]) 
			adjacent.add(new State(pos.x, pos.y-1));
		// checks East
		if (pos.x < grid.length-1 && !visited[pos.x+1][pos.y]) 
			adjacent.add(new State(pos.x+1, pos.y));	
		// checks South
		if (pos.y < grid[0].length-1 && !visited[pos.x][pos.y+1])
			adjacent.add(new State(pos.x, pos.y+1));
		// checks West
		if (pos.x > 0 && !visited[pos.x-1][pos.y])
			adjacent.add(new State(pos.x-1, pos.y));
		return adjacent;
	}

	private static class State {
		public int x;
		public int y;
		public State(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
