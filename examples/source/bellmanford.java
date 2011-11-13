import java.util.*;

public class bellmanford {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numV = input.nextInt();
        ArrayList<Vertex> V = new ArrayList<Vertex>(numV);
        for (int i = 0; i < numV; i++)
        {
            V.add(new Vertex());
        }
        int numE = input.nextInt();
        ArrayList<Edge> E = new ArrayList<Edge>(numE);
        for (int i = 0; i < numE; i++)
        {
            int source = input.nextInt();
            int target = input.nextInt();
            int weight = input.nextInt();
            E.add(new Edge(source, target, weight));
        }
        Vertex source = V.get(input.nextInt()-1);
        relax(V, numV, E, numE, source);
        displayEdges(E);
        displayVertices(V);
        if (validate(V, E))
        {
            System.out.println("Graph does not contain cycle.");
        }
        else
            System.out.println("Graph contains negative cycle.");
    }

    public static void relax(ArrayList<Vertex> V, int numV, ArrayList<Edge> E, int numE, Vertex source)
    {
        source.pathweight = 0; 
        for (int i = 0; i < numV-1; i++)
        {
            for (Edge e : E)
            {
                Vertex u = V.get(e.source-1);
                Vertex v = V.get(e.target-1);
                if (u.pathweight != Integer.MAX_VALUE && u.pathweight + e.weight < v.pathweight)
                {
                    v.pathweight = u.pathweight + e.weight;
                    v.predecessor = u;
                }
            }
        }
    }

    public static boolean validate(ArrayList<Vertex> V, ArrayList<Edge> E)
    {
        for (Edge e : E)
        {
            Vertex u = V.get(e.source-1);
            Vertex v = V.get(e.target-1);
            if (u.pathweight != Integer.MAX_VALUE && u.pathweight + e.weight < v.pathweight)
                return false;
        }
        return true;
    }

    public static void displayEdges(ArrayList<Edge> list)
    {
        for (Edge e : list)
        {
            System.out.printf("%d -> %d: %d\n", e.source, e.target, e.weight);
        }
    }

    public static void displayVertices(ArrayList<Vertex> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            System.out.printf("%d cost to %d\n", list.get(i).pathweight, i+1);
        }
    }

    public static class Vertex
    {
        public Vertex predecessor;
        public int pathweight;

        public Vertex()
        {
            this.predecessor = null;
            this.pathweight = Integer.MAX_VALUE;
        }
    }

    public static class Edge
    {
        public int source;
        public int target;
        public int weight;

        public Edge(int source, int target, int weight)
        {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }
}
