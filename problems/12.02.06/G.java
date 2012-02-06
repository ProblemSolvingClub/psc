import java.util.*;

public class G {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int max = 10000;
        int[] isPrime = new int[10000];
        for (int i = 0; i < max; ++i) {
            isPrime[i] = 1;
        }
        isPrime[0] = 0;
        isPrime[1] = 0;
        for (int i = 2; i < (max>>1); ++i) {
            int n = i<<1;
            while (true) {
                if (n < max) {
                    isPrime[n] = 0;
                    n += i;
                }
                else {
                    break;
                }
            }
        }
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i = 1000; i < max; ++i) {
            if (isPrime[i] == 1)
                primes.add(i);
        }
        int testCount = in.nextInt();
        for (int test = 0; test < testCount; ++test) {
            int source = in.nextInt();
            int target = in.nextInt();
            int dist = Dist(primes, source, target);
            if (dist == -1)
                System.out.println("Impossible");
            else
                System.out.println(dist);
        }
    }

    static int Dist(ArrayList<Integer> primes, int source, int target) {
        if (source == target) {
            return 0;
        }
        else {
            int[] usedPrimes = new int[10000];
            for (int i = 0; i < 10000; ++i) {
                usedPrimes[i] = Integer.MAX_VALUE;
            }
            usedPrimes[source] = 0;
            Queue<Integer> Q = new LinkedList<Integer>();
            Q.offer(source);
            while (Q.peek() != null) {
                int current = Q.poll();
                int currentDist = usedPrimes[current];
                ArrayList<Integer> neighbours = Neighbours(primes, current);
                for (int p : neighbours) {
                    if (p == target) {
                        return currentDist+1;
                    }
                    if (usedPrimes[p] == Integer.MAX_VALUE) {
                        usedPrimes[p] = currentDist+1;
                        Q.offer(p);
                    }
                }
            }
            return -1;
        }
    }

    static ArrayList<Integer> Neighbours(ArrayList<Integer> primes, int n) {
        int d1 = n % 10;
        int d2 = (n % 100) / 10;
        int d3 = (n % 1000) / 100;
        int d4 = n / 1000;
        //System.out.printf("%d %d %d %d\n", d1, d2, d3, d4);
        ArrayList<Integer> neighbours = new ArrayList<Integer>();
        for (int p : primes) {
            int diffs = 0;
            int p1 = p % 10;
            if (d1 != p1)
                diffs++;
            int p2 = (p % 100) / 10;
            if (d2 != p2)
                diffs++;
            int p3 = (p % 1000) / 100;
            if (d3 != p3)
                diffs++;
            int p4 = p / 1000;
            if (d4 != p4)
                diffs++;
            if (diffs == 1)
                neighbours.add(p);
        }
        return neighbours;
    }
}
