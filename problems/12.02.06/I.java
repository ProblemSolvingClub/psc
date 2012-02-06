import java.util.*;

public class I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCount = in.nextInt();
        in.nextLine();
        for (int test = 0; test < testCount; ++test) {
            String perm = in.nextLine();
            ArrayList<Integer> cycles = Decomp(perm);
            //System.out.println(cycles);
            int[] numEven = new int[12];
            for (int i = 0; i < 12; ++i) {
                numEven[i] = 0;
            }
            for (int c : cycles) {
                if (c % 2 == 0) {
                    numEven[c>>1]++;
                }
            }
            boolean isGood = true;
            for (int i = 0; i < 12; ++i) {
                if (numEven[i] % 2 != 0) {
                    isGood = false;
                } 
            }
            if (isGood) {
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
        }
    }

    static ArrayList<Integer> Decomp(String perm) {
        String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean[] inCycle = new boolean[26];
        for (int i = 0; i < 26; ++i) {
            inCycle[i] = false;
        }
        ArrayList<Integer> cycles = new ArrayList<Integer>();
        for (int i = 0; i < 26; ++i) {
            if (!inCycle[i]) {
                int root = i;
                inCycle[root] = true;
                int current = ALPHA.indexOf(perm.charAt(root));
                int cycleSize = 1;
                while (current != root) {
                    cycleSize++;
                    inCycle[current] = true;
                    current = ALPHA.indexOf(perm.charAt(current));
                }
                cycles.add(cycleSize);
            }
        }
        return cycles;
    }
}
