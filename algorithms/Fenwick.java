/* *
 * Implementation of a Fenwick tree, also known as a Binary Indexed Tree.
 * A BIT is a data structure which efficiently caches and updates the prefix
 * sums of an array of values - that is, given an array of values A[0..n], 
 * the kth index in the BIT will be the sum of A[0..k]. You can use these
 * prefix sums to calculate the sum of any subarray of A.
 * */

import java.util.*;

public class Fenwick {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int valCount = in.nextInt();
        ArrayList<Integer> vals = new ArrayList<Integer>(valCount);
        for (int i = 0; i < valCount; ++i)
            vals.add(in.nextInt()); 
        System.out.println("Given values:");
        System.out.println(vals);
        BIT fenwick = new BIT(vals);
        System.out.println("Fenwick Tree representation:");
        System.out.println(fenwick.tree);
        System.out.println("Cumulative sums of values:");
        ArrayList<Integer> cumulativeSum = new ArrayList<Integer>(valCount);
        for (int i = 0; i < valCount; ++i)
            cumulativeSum.add(fenwick.PrefixSum(i));
        System.out.println(cumulativeSum);
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
