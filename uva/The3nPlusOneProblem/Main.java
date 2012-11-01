import java.util.*;

class Main {
    private Main() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int i = in.nextInt();
            int j = in.nextInt();
            int lower = Math.min(i, j);
            int higher = Math.max(i, j);
            int max = 0;
            for (int x = lower; x <= higher; ++x) {
                int length = 1;
                int n = x;
                while (n != 1) {
                    if ((n & 1) == 1)
                        n = 3*n+1;
                    else
                        n >>= 1;
                    ++length;
                }
                //System.out.println(length);
                max = Math.max(length, max);
            }
            System.out.printf("%d %d %d\n", i, j, max);
        }
    }    

    public static void main(String[] args) {
        Main problem = new Main();
    }
}
