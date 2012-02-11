import java.util.*;

public class SizeSubsetGen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int b = in.nextInt();
        while (n != 0) {
            System.out.printf("Set size %d, subset size %d:\n", n, b);
            ArrayList<Integer> set = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                set.add(in.nextInt());
            }
            Generate(set, b);
            n = in.nextInt();
            b = in.nextInt();
        }
    }

    public static void Generate(ArrayList<Integer> set, int b) {
        if (b == 0) {
            ArrayList<Integer> empty = new ArrayList<Integer>();
            System.out.println(empty);
        }
        else {
            int bitmask = (1 << b) - 1;
            int limit = Choose(set.size(), b);
            for (int i = 0; i < limit; i++) {
                ArrayList<Integer> subset = SubsetFromBitmask(set, bitmask);
                System.out.println(subset);
                bitmask = Snoob(bitmask);
            }
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

    public static int Snoob(int x) {
        int smallest, ripple, ones = 0;
        smallest = (x & -x);
        ripple = x + smallest;
        ones = x ^ ripple;
        ones = (ones >> 2)/smallest;
        return (ripple | ones);
    }

    public static int Choose(int n, int k) {
        if (k == 0)
            return 1;
        else
            return (Factorial(n)/(Factorial(n-k)*Factorial(k)));
    }

    public static int Factorial(int n) {
        int product = 1;
        for (int i = 1; i <= n; i++) {
            product *= i;
        }
        return product;
    }
}
