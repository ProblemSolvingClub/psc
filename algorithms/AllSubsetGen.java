/**
 * Demonstrates the method of generating all subsets of a set, using a bitmask.
 * The bitmask is incremented until all 2^n values have been generated, which
 * are in turn used to generate every subset of the n-value set.
 **/

import java.util.*;

public class AllSubsetGen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != 0) {
            System.out.printf("Set size %d:\n", n);
            ArrayList<Integer> set = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                set.add(in.nextInt()); 
            }
            Generate(set);
            n = in.nextInt();
        }
    }

    public static void Generate(ArrayList<Integer> set) {
        for (int bitmask = 0; bitmask < (1 << set.size()); bitmask++) {
            ArrayList<Integer> subset = SubsetFromBitmask(set, bitmask);
            System.out.println(subset);
        }
    }

    public static ArrayList<Integer> SubsetFromBitmask(ArrayList<Integer> set, int bitmask) {
        ArrayList<Integer> subset = new ArrayList<Integer>();
        int bit = 1;
        for (int idx = 0; idx < set.size(); idx++) {
            if ((bit & bitmask) != 0) {
                subset.add(set.get(idx)); 
            }
            bit = (bit << 1);
        }
        return subset;
    }
}
