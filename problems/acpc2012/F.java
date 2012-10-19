import java.util.*;

public class F {

    public class State {
        public int x, y, t, s;
        public State(int x, int y, int t, int s) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.s = s;
        }
        public String toString() {
            return "("+this.x+", "+this.y+", "+Integer.toBinaryString(this.t)+", "+this.s+")";
        }
    }

    ArrayList<State> GetNext(int[][] maze, int[][] treasures, int[][][][] visited, State c) {
        int colCount = maze.length;
        int rowCount = maze[0].length;
        int dir = (c.s + maze[c.x][c.y])&3;
        int nDir = (c.s+1)&3;

        ArrayList<State> next = new ArrayList<State>(2);
        if (visited[c.x][c.y][c.t][nDir] == -1)
            next.add(new State(c.x, c.y, c.t, nDir));

        // North
        if (dir == 0 && c.y > 0) { 
            int treasure = c.t | treasures[c.x][c.y-1];
            if (visited[c.x][c.y-1][treasure][nDir] == -1)
                next.add(new State(c.x, c.y-1, treasure, nDir));
        }

        // East
        if (dir == 1 && c.x < colCount-1) { 
            int treasure = c.t | treasures[c.x+1][c.y];
            if (visited[c.x+1][c.y][treasure][nDir] == -1)
                next.add(new State(c.x+1, c.y, treasure, nDir));
        }

        // South
        if (dir == 2 && c.y < rowCount-1) { 
            int treasure = c.t | treasures[c.x][c.y+1];
            if (visited[c.x][c.y+1][treasure][nDir] == -1)
                next.add(new State(c.x, c.y+1, treasure, nDir));
        }

        // West
        if (dir == 3 && c.x > 0) {
            int treasure = c.t | treasures[c.x-1][c.y];
            if (visited[c.x-1][c.y][c.t][nDir] == -1)
                next.add(new State(c.x-1, c.y, treasure, nDir));
        }

        return next;
    }

    boolean IsExit(State s, int colCount, int rowCount, int treasureCount) {
        return (s.x == colCount-1 && s.y == rowCount-1 && s.t == (1 << treasureCount)-1);
    }

    public int BFS(int[][] maze, int[][] treasures, int[][][][] visited, int treasureCount) {
        int colCount = maze.length;
        int rowCount = maze[0].length;
        Queue<State> Q = new LinkedList<State>();
        State source = new State(0, 0, 0, 0);
        Q.offer(source);
        visited[0][0][0][0] = 0;
        while (Q.peek() != null) {
            State c = Q.poll();
            int steps = visited[c.x][c.y][c.t][c.s];
            ArrayList<State> next = GetNext(maze, treasures, visited, c);
            /*
            if (c.t == (1 << treasureCount)-1) {
                System.out.println(c);
                System.out.println(next);
            }
            */
            for (State n : next) {
                if (IsExit(n, colCount, rowCount, treasureCount))
                    return steps+1;
                Q.offer(n);
                visited[n.x][n.y][n.t][n.s] = steps+1;
            }
        }
        return -1;
    }

    public F() {
        Scanner in = new Scanner(System.in);
        int rowCount = in.nextInt();
        int colCount = in.nextInt();
        while (rowCount != 0 && colCount != 0) {
            in.nextLine();
            ArrayList<String> lines = new ArrayList<String>(rowCount);
            for (int row = 0; row < rowCount; ++row)
                lines.add(in.nextLine());
            int[][] treasures = new int[colCount][rowCount];
            int treasureCount = in.nextInt();
            for (int i = 0; i < treasureCount; ++i) {
                int row = in.nextInt()-1;
                int col = in.nextInt()-1;
                treasures[col][row] = (1 << i);
            }
            int[][] maze = new int[colCount][rowCount];
            String dir = "NESW";
            for (int row = 0; row < rowCount; ++row) {
                for (int col = 0; col < colCount; ++col)
                    maze[col][row] = dir.indexOf(lines.get(row).charAt(col));
            }
            int[][][][] visited = new int[colCount][rowCount][1<<treasureCount][4];
            for (int col = 0; col < colCount; ++col) {
                for (int row = 0; row < rowCount; ++row) {
                    for (int chests = 0; chests < (1 << treasureCount); ++chests) {
                        visited[col][row][chests][0] = -1;
                        visited[col][row][chests][1] = -1;
                        visited[col][row][chests][2] = -1;
                        visited[col][row][chests][3] = -1;
                    }
                }
            }
            System.out.println(BFS(maze, treasures, visited, treasureCount));
            rowCount = in.nextInt();
            colCount = in.nextInt();
        }
    }

    public static void main(String[] args) {
        F problem = new F();
    }
}

