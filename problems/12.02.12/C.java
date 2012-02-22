import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for (int caseNum = 0; caseNum < caseCount; ++caseNum) {
            int movieCount = in.nextInt();
            int watchCount = in.nextInt();
            ArrayList<Integer> toWatch = new ArrayList<Integer>(watchCount);
            for (int i = 0; i < watchCount; ++i) {
                toWatch.add(in.nextInt()-1);
            }
            for (int n : Locations(toWatch, movieCount)) {
                System.out.printf("%d ", n);
            }
            System.out.println();
        }
    }

    static ArrayList<Integer> Locations(ArrayList<Integer> toWatch, int movieCount) {
        ArrayList<Integer> results = new ArrayList<Integer>(toWatch.size());

        ArrayList<Integer> preBIT = new ArrayList<Integer>(toWatch.size()+movieCount);
        for (int i = 0; i < toWatch.size(); ++i) 
            preBIT.add(0);
        for (int i = 0; i < movieCount; ++i) 
            preBIT.add(1);
        BIT stack = new BIT(preBIT);

        // Keeps track of position of each movie in BIT
        ArrayList<Integer> indices = new ArrayList<Integer>(movieCount);
        for (int i = 0; i < movieCount; ++i)
            indices.add(toWatch.size()+i);
        int top = toWatch.size()-1;

        for (int movie : toWatch) {
            // Gets number of movies above one to watch
            int idx = indices.get(movie);
            int distFromTop = stack.PrefixSum(idx)-1;
            results.add(distFromTop);

            // Moves movie to top of stack
            stack.Update(idx, -1);
            stack.Update(top, 1);
            indices.set(movie, top);
            --top;
        }
        return results;
    }

    public static class BIT {
        // Structure used to represent the BIT - a simple array
        public ArrayList<Integer> tree;

        // Builds BIT from given set of values
        public BIT(ArrayList<Integer> vals) {
            this.tree = new ArrayList<Integer>(vals.size());
            for (int i = 0; i < vals.size(); ++i)
                this.tree.add(0);
            for (int i = 0; i < vals.size(); ++i) {
                this.Update(i, vals.get(i));
            }
        }

        // Updates BIT to reflect adding given value to index
        public void Update(int idx, int val) {
            // Special case to prevent infinite loop
            if (idx == 0) {
                int prev = this.tree.get(0);
                this.tree.set(0, prev+val);
            }
            else {
                // Progressively updates values in tree
                while (idx < this.tree.size()) {
                    int prev = this.tree.get(idx);
                    this.tree.set(idx, prev+val);
                    idx += idx & -idx;
                }
            }
        }

        // Queries the prefix sum of the given index
        public int PrefixSum(int idx) {
            int sum = this.tree.get(0);
            // Regressively retrieves values from tree
            while (idx > 0) {
                sum += this.tree.get(idx);
                idx &= (idx-1);
            }
            return sum;
        }
    }
}
