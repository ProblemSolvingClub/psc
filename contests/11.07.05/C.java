import java.util.*;

public class C
{

	static void simulate(char[][] grid, int n, int rows, int cols)
	{
		for (int trials = 0; trials < n; trials++)
		{
			char[][] newGrid = new char[rows][cols];
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < cols; j++)
				{
					newGrid[i][j] = checkAdjacent(grid, i, j, rows, cols);
				}
			}
			grid = newGrid;
		}
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}

	static char checkAdjacent(char[][] grid, int i, int j, int rows, int cols)
	{
		char second = grid[i][j];
		// Check north
		if (i > 0 && winsAgainst(grid[i-1][j], second))
			return grid[i-1][j];
		// check east
		else if (j < cols-1 && winsAgainst(grid[i][j+1], second))
			return grid[i][j+1];
		// check south
		else if (i < rows-1 && winsAgainst(grid[i+1][j], second))
			return grid[i+1][j];
		// check west
		else if (j > 0 && winsAgainst(grid[i][j-1], second))
			return grid[i][j-1];
		// not beaten
		else
			return second;
	}

	static boolean winsAgainst(char first, char second)
	{
		if (first == 'R' && second == 'S')
			return true;
		else if (first == 'S' && second == 'P')
			return true;
		else if (first == 'P' && second == 'R')
			return true;
		else
			return false;
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int numCases = input.nextInt();
		for (int i = 0; i < numCases; i++)
		{
			int rows = input.nextInt();
			int cols = input.nextInt();
			int n = input.nextInt();
			input.nextLine();
			char[][] grid = new char[rows][cols];
			for (int j = 0; j < rows; j++)
			{
				String line = input.nextLine();
				for (int k = 0; k < cols; k++)
				{
					grid[j][k] = line.charAt(k);
				}
			}
			simulate(grid, n, rows, cols);
			if (i != numCases-1)
				System.out.println();
		}
	}
}
