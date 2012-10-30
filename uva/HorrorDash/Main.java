// Lumberjack Sequencing

import java.util.*;

class Main {

    private Main() {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for (int c = 0; c < caseCount; ++c) {
            int numCount = in.nextInt();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < numCount; ++i) {
                int n = in.nextInt();
                max = Math.max(max, n);
            }
            System.out.printf("Case %d: %d\n", c+1, max);
        }
    }

    public static void main(String[] args) {
        Main problem = new Main();
    }
}
