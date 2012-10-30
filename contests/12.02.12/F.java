import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for(int caseNum = 0; caseNum < caseCount; ++caseNum) {
            int xDim = in.nextInt();
            int yDim = in.nextInt(); 
            int digCost = in.nextInt();
            int fillCost = in.nextInt();
            int lineCost = in.nextInt();
            in.nextLine();
            char[][] grid = new char[xDim][yDim];
            for (int j = 0; j < yDim; ++j) {
                String line = in.nextLine();
                for (int i = 0; i < xDim; ++i) {
                    grid[i][j] = line.charAt(i);
                }
            }
            System.out.println(Cost(grid, digCost, fillCost, lineCost));
        }
    }

    static int Cost(char[][] grid, int digCost, int fillCost, int lineCost) {
        int totalCost = 0;
        // Fills perimeter
        for (int i = 0; i < grid[0].length; ++i) {
            if (grid[0][i] == '.') {
                grid[0][i] = '#';
                totalCost += fillCost;
            }
            if (grid[grid.length-1][i] == '.') {
                grid[grid.length-1][i] = '#';
                totalCost += fillCost;
            }
        }
        for (int i = 0; i < grid.length; ++i) {
            if (grid[i][0] == '.') {
                grid[i][0] = '#';
                totalCost += fillCost;
            }
            if (grid[i][grid[0].length-1] == '.') {
                grid[i][grid[0].length-1] = '#';
                totalCost += fillCost;
            }
        }
        char[][] newGrid = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                newGrid[i][j] = grid[i][j];
            }
        }
        for (int i = 1; i < grid.length-1; ++i) {
            for (int j = 1; j < grid[0].length-1; ++j) {
                char current = grid[i][j];
                int costToDig = current == '#' ? digCost : 0;
                grid[i][j] = '.';
                costToDig += SurroundCost(grid, lineCost, i, j);
                int costToFill = current == '.' ? fillCost : 0;
                grid[i][j] = '#';
                costToFill += SurroundCost(grid, lineCost, i, j);
                grid[i][j] = current;
                if (costToFill >= costToDig) {
                    newGrid[i][j] = '.'; 
                    if (current == '#') {
                        totalCost += digCost;
                    }
                }
                else {
                    newGrid[i][j] = '#'; 
                    if (current == '.') {
                        totalCost += fillCost;
                    }
                }
            }
        }
        //System.out.println(totalCost);
        // Cost of inside
        for (int i = 1; i < grid.length-1; ++i) {
            for (int j = 1; j < grid[0].length-1; ++j) {
                totalCost += SquareCost(newGrid, lineCost, i, j);
            }
        }
        //System.out.println(totalCost);
        // Cost of perimeter
        for (int i = 0; i < grid[0].length; ++i) {
            if (newGrid[1][i] == '.') {
                //System.out.printf("%d %d\n", 1, i);
                totalCost += lineCost;
            }
            if (newGrid[grid.length-2][i] == '.') {
                //System.out.printf("%d %d\n", grid.length-2, i);
                totalCost += lineCost;
            }
        }
        for (int i = 0; i < grid.length; ++i) {
            if (newGrid[i][1] == '.') {
                //System.out.printf("%d %d\n", i, 1);
                totalCost += lineCost;
            }
            if (newGrid[i][grid[0].length-2] == '.') {
                //System.out.printf("%d %d\n", i, grid[0].length-2);
                totalCost += lineCost;
            }
        }
        /*
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                System.out.print(newGrid[i][j]);
            }
            System.out.println();
        }
        */
        return totalCost;
    }

    static int SurroundCost(char[][] grid, int lineCost, int x, int y) {
        int cost = 0;
        if (grid[x-1][y-1] == '#') {
            cost = (grid[x][y-1] == '.') ? cost+lineCost : cost;
            cost = (grid[x-1][y] == '.') ? cost+lineCost : cost;
        }
        if (grid[x][y-1] == '#') {
            cost = (grid[x-1][y-1] == '.') ? cost+lineCost : cost;
            cost = (grid[x+1][y-1] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y] == '.') ? cost+lineCost : cost;
        }
        if (grid[x+1][y-1] == '#') {
            cost = (grid[x][y-1] == '.') ? cost+lineCost : cost;
            cost = (grid[x+1][y] == '.') ? cost+lineCost : cost;
        }
        if (grid[x-1][y] == '#') {
            cost = (grid[x-1][y-1] == '.') ? cost+lineCost : cost;
            cost = (grid[x-1][y+1] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y] == '.') ? cost+lineCost : cost;
        }
        if (grid[x][y] == '#') {
            cost = (grid[x-1][y] == '.') ? cost+lineCost : cost;
            cost = (grid[x+1][y] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y+1] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y-1] == '.') ? cost+lineCost : cost;
        }
        if (grid[x+1][y] == '#') {
            cost = (grid[x+1][y-1] == '.') ? cost+lineCost : cost;
            cost = (grid[x+1][y+1] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y] == '.') ? cost+lineCost : cost;
        }
        if (grid[x-1][y+1] == '#') {
            cost = (grid[x-1][y] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y+1] == '.') ? cost+lineCost : cost;
        }
        if (grid[x][y+1] == '#') {
            cost = (grid[x-1][y+1] == '.') ? cost+lineCost : cost;
            cost = (grid[x+1][y+1] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y] == '.') ? cost+lineCost : cost;
        }
        if (grid[x+1][y+1] == '#') {
            cost = (grid[x+1][y] == '.') ? cost+lineCost : cost;
            cost = (grid[x][y+1] == '.') ? cost+lineCost : cost;
        }
        return cost;
    }

    static int SquareCost(char[][] grid, int lineCost, int i, int j) {
        int cost = 0;
        char square = grid[i][j];
        if (square == '#') {
            // Check north
           cost = (grid[i][j-1] == '.') ? cost+lineCost : cost; 
            // Checks east
           cost = (grid[i+1][j] == '.') ? cost+lineCost : cost; 
            // Checks south
           cost = (grid[i][j+1] == '.') ? cost+lineCost : cost; 
            // Checks west
           cost = (grid[i-1][j] == '.') ? cost+lineCost : cost; 
        }
        return cost;
    }
}
