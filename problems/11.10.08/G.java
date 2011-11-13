import java.util.*;

public class G {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int Q = in.nextInt();
        while (N != 0 && M != 0 && Q != 0) {
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < Q; i++)
                edges.add(parseInput(in, N)); 
            if (hasNegCycle(edges, N, M))
                System.out.println("Impossible");
            else
                System.out.println("Possible");
            N = in.nextInt();
            M = in.nextInt();
            Q = in.nextInt();
        }
    }

    private static boolean hasNegCycle(ArrayList<Edge> edges, int N, int M) {
        ArrayList<Integer> vertices = new ArrayList<Integer>(N+M);
        vertices.add(0);
        for (int i = 1; i < N+M; i++)
            vertices.add(Integer.MAX_VALUE);
        for (int i = 0; i < N+M-1; i++) {
            for (Edge e : edges) {
                int u = vertices.get(e.source-1);
                int v = vertices.get(e.target-1);
                if (u + e.weight < v && u != Integer.MAX_VALUE) 
                    vertices.set(e.target-1, u + e.weight);
            }
        }
        for (Edge e : edges) {
            int u = vertices.get(e.source-1);
            int v = vertices.get(e.target-1);
            if (u + e.weight < v && u != Integer.MAX_VALUE) 
                return true;
        }
        return false;
    }

    private static Edge parseInput(Scanner in, int N) {
        int source = in.nextInt();
        int target = in.nextInt() + N;
        in.skip(" ");
        String expr = in.next();
        int weight = in.nextInt();
        if (expr.equals("<="))
            return new Edge(source, target, weight);
        else
            return new Edge(target, source, -1*weight);
    }

    private static class Edge {
        public int source;
        public int target;
        public int weight;
        public Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
        public String toString() {
            return String.format("Source: %d Target: %d Weight: %d", this.source, this.target, this.weight);
        }
    }
}
