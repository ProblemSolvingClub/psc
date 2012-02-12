/**
 * Java implementation of the Ford-Fulkerson algorithm to find max flow in a graph.
 * See ff.cpp for a C++ implementation of the same.
 **/

import java.util.*;

public class FordFulkerson {

    public int Flow(ArrayList<Node> nodes, ArrayList<Edge> edges, int source, int sink) {
        // Finds a path from the source to the sink with capacity left
        ArrayList<Edge> path = FindViablePath(nodes, source, sink);
        // Continues until no paths from source to sink with unused capacity exist
        while (path.size() != 0) {
            // Finds the minimum amount of unused capacity on the path
            int min = Integer.MAX_VALUE; 
            for (Edge e : path) {
                if (e.cf() < min) {
                    min = e.cf();
                }
            }
            // Adds minimum unused capacity to all edges on the path
            for (Edge e : path) {
                e.AddFlow(min);
            }
            // Finds the next path
            path = FindViablePath(nodes, source, sink);
        }
        // Sums all flow coming from the source to get the max flow
        int maxFlow = 0;
        for (Edge e : nodes.get(source).edges) {
            maxFlow += e.flow;
        }
        return maxFlow;
    }

    public ArrayList<Edge> FindViablePath(ArrayList<Node> nodes, int source, int sink) {
        // 'paths' keeps track of the edges taken to get to a given node
        ArrayList<ArrayList<Edge>> paths = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < nodes.size(); ++i)
            paths.add(new ArrayList<Edge>());
        // 'T' keeps track of whether a node has been visited
        boolean[] T = new boolean[nodes.size()];
        for (int i = 0; i < nodes.size(); ++i)
            T[i] = false;

        // Starts a standard Breadth-First Search
        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(nodes.get(source));
        T[source] = true;
        while (Q.peek() != null) {
            Node current = Q.poll();
            for (Edge e : current.edges) {
                // Discards edge if it has no unused capacity
                if (e.cf() > 0) {
                    int next = e.target;
                    // If next has not been visited, propogate source to next
                    if (!T[next]) {
                        Q.offer(nodes.get(next));
                        T[next] = true;
                        paths.get(next).addAll(paths.get(e.source));
                        paths.get(next).add(e);
                    }
                    if (next == sink) {
                        break;
                    }
                }
            }
        }
        return paths.get(sink);
    }

    public class Node {
        public int id;
        public ArrayList<Edge> edges;
        public Node(int id) {
            this.id = id;
            this.edges = new ArrayList<Edge>();
        }
        public void AddEdge(Edge newEdge) {
            this.edges.add(newEdge);
        }
    }

    public class Edge {
        public int source;
        public int target;
        public int capacity;
        public int flow;
        public Edge(int source, int target, int capacity) {
            this.source = source;
            this.target = target;
            this.capacity = capacity;
            this.flow = 0;
        }
        public int cf() {
            return this.capacity - this.flow;
        }
        public void AddFlow(int flow) {
            this.flow += flow;
        }
    }

    public static void main(String[] args) {
        FordFulkerson problem = new FordFulkerson();
    }

    public FordFulkerson() {
        // Parses input
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for (int caseNum = 0; caseNum < caseCount; ++caseNum) {
            int nodeCount = in.nextInt();
            int edgeCount = in.nextInt();
            ArrayList<Node> nodes = new ArrayList<Node>(nodeCount);
            for (int i = 0; i < nodeCount; ++i)
                nodes.add(new Node(i)); 
            ArrayList<Edge> edges = new ArrayList<Edge>(edgeCount);
            for (int i = 0; i < edgeCount; ++i) {
                int source = in.nextInt();
                int target = in.nextInt();
                int capacity = in.nextInt();
                Edge newEdge = new Edge(source, target, capacity);
                edges.add(newEdge);
                nodes.get(source).AddEdge(newEdge);
            }
            int source = in.nextInt();
            int sink = in.nextInt();
            int maxFlow = Flow(nodes, edges, source, sink);
            System.out.printf("Max flow %d: %d\n", caseNum, maxFlow);
        }
    }
}
