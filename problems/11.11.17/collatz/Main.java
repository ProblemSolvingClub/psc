import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        int[] cycles = new int[1000001];
        for (int i = 0; i < 1000001; i++)
            cycles[i] = -1;
        cycles[1] = 1;
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt())
        {
            int i = in.nextInt();
            int j = in.nextInt();
            long max = Max(i, j, cycles);
            System.out.printf("%d %d %d\n", i, j, max);
        }
    }

    public static long Max(int i, int j, int[] cycles)
    {
        long max = 0;
        for (int num = i; num <= j; num++)
        {
            cycle visited = new cycle();
            long n = num;
            while (true)
            {
                if (n < 1000000)
                {
                    if (cycles[(int)n] != -1)
                        break;
                    else
                        visited.stack.add(new pair((int)n, 0));
                }
                if (n % 2 == 0)
                    n = n >> 1;
                else
                    n = 3*n + 1;
                visited.Increment();
            }
            int toVisit = cycles[(int)n]; 
            for (pair p : visited.stack)
                cycles[p.num] = p.cycleLength+toVisit;
            if (max < cycles[num])
                max = cycles[num];
            //System.out.println(cycles[num]);
        }
        return max;
    }

    public static class cycle
    {
        public ArrayList<pair> stack;
        public cycle()
        {
            stack = new ArrayList<pair>();
        }
        public void Increment()
        {
            for (pair p : stack)
            {
                p.cycleLength++;
            }
        }
    }

    public static class pair
    {
        public int num;
        public int cycleLength;
        public pair(int num, int cycleLength)
        {
            this.num = num;
            this.cycleLength = cycleLength;
        }
    }
}
