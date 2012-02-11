/* *
 * In this example we will be reading in a description of a maze into a 2D
 * array. The nicest way to deal with this (and other) nicely-formatted
 * problems is with the use of the Scanner class. The full list of methods
 * available in Scanner is available online.
 * */

import java.util.Scanner;

class Input {
	public static void main(String[] args) {
		// Creates a scanner reading from System.in
		Scanner input = new Scanner(System.in);
		// Gets rows and columns
		int rows = input.nextInt();
		int cols = input.nextInt();
		// Gets rid of \n
		input.nextLine();
		// Reads in description of maze
		char[][] maze = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			String row = input.nextLine();
			for (int j = 0; j < cols; j++) {
				maze[i][j] = row.charAt(j);
			}
		}
		// Prints out input
		System.out.printf("%d %d", rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
}
