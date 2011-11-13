import java.util.*;

public class G
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numC = input.nextInt();
        int numR = input.nextInt();
        int iter = 1;
        while (numC != 0 && numR != 0)
        {
            ArrayList<Road> graph = new ArrayList<Road>();
            for (int i = 0; i < numR; i++)
            {
                int source = input.nextInt();
                int target = input.nextInt();
                int heightLimit = input.nextInt();
                int length = input.nextInt();
                graph.add(new Road(source, target, heightLimit, length));
            }
            int source = input.nextInt();
            int target = input.nextInt();
            int heightLimit = input.nextInt();
            System.out.printf("Case %d:\n", iter);
            Run(graph, numC, source, target, heightLimit);
            numC = input.nextInt();
            numR = input.nextInt();
            iter++;
        }
    }

    public static void Run(ArrayList<Road> graph, int numC, int source, int target, int heightLimit)
    {
        int low = 0;
        int high = heightLimit+1;
        int dist = -1;
        int height = 0;
        int mid = 0;
        while (low < high)
        {
            mid = (high + low)/2;
            int newDist = BF(graph, numC, source, target, mid);
            //System.out.printf("%d %d\n", newDist, mid);
            if (newDist == -1)
            {
                high = mid;
            }
            else
            {
                dist = newDist;
                height = mid;
                low = mid+1;
            }
        }
        if (dist == -1)
            System.out.println("cannot reach destination");
        else
            System.out.printf("maximum height = %d\nlength of shortest route = %d\n", height, dist);
    }

    public static int BF(ArrayList<Road> graph, int numC, int source, int target, int heightLimit)
    {
        ArrayList<Integer> cities = new ArrayList<Integer>();
        for (int i = 0; i < numC; i++)
        {
            cities.add(Integer.MAX_VALUE);
        }
        cities.set(source-1, 0);
        for (int i = 0; i < numC-1; i++)
        {
            for (Road r : graph)
            {
                if (heightLimit <= r.heightLimit || r.heightLimit == -1)
                {
                    int u = r.source;
                    int v = r.target;
                    int dU = cities.get(u-1);
                    int dV = cities.get(v-1);
                    if (dU != Integer.MAX_VALUE && dU + r.length < dV)
                    {
                        cities.set(v-1, dU + r.length);
                    }
                }
            }
        }
        int dist = cities.get(target-1);
        if (dist != Integer.MAX_VALUE)
            return dist;
        else
            return -1;
    }

    private static class Road
    {
        public int source;
        public int target;
        public int heightLimit;
        public int length;

        public Road(int source, int target, int heightLimit, int length)
        {
            this.source = source;
            this.target = target;
            this.heightLimit = heightLimit;
            this.length = length;
        }
    }
}
