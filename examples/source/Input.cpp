using namespace std;

#include <stdio.h>

/* *
 * In this example, we will be reading the description of a maze into a 2D
 * array. We will then simply print it out again, unchanged. This is meant
 * to demonstrate the basics of reading input with C++. iostream may also be
 * used, but with the types of problems we are given (strictly-defined input
 * with no errors) it is more convenient to use the older and more dangerous
 * functions found in stdio.h.
 *
 * A note about 2D arrays in C++:
 * In C++, when you have a 2D array you will be passing to a function, it
 * is far simpler to simply make it a 1D array where location (x,y) is
 * translated into the linear address y*COLS+x, where COLS is the depth
 * of the 2D array you wish to have. When passing the array to a function,
 * pass the COLUMNS value with it. Java encapsulates information about the
 * array's size into the array itself, so this same process takes place
 * invisibly and you don't have to worry about it. 
 * */

int main() {
	/* *
	 * Generally speaking, input will start with a description of the 
	 * size of the upcoming problem - in this example, number of rows
	 * and columns.
	 * */
	int rows, cols;
	// Reads in a pair of numbers on a single line
	scanf("%d %d\n", &rows, &cols);
	/* *
	 * Continuing with the example, we read the next <rows> lines of
	 * input, each of which has <cols> characters describing the maze.
	 * Since a string is an array of chars, we may simply pass the gets
	 * function the address of a location in the array. This reads
	 * all characters on the line into the array at that location,
	 * stripping the \n from the end.
	 *
	 * Be sure you have read and understand the note about 2D arrays in
	 * C++ above before proceeding.
	 * */
	char maze[rows*cols];
	for (int i = 0; i < rows; i++) {
		// The compiler issues a dire warning...
		gets(&maze[i*cols]);
	}
	/* *
	 * Now we have read in the array. To test we have done this
	 * correctly, we will print out the input exactly as we read
	 * it in:
	 * */
	printf("%d %d\n", rows, cols);
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			printf("%c", maze[i*cols+j]);
		}	
		printf("\n");
	}
	return 0;
}
