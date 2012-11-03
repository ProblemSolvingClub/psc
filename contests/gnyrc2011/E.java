import java.util.*;

public class E {
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
		public Edge opposite;
		public Edge(int source, int target, int capacity) {
			this.source = source;
			this.target = target;
			this.capacity = capacity;
			this.flow = 0;
			this.opposite = null;
		}
		public Edge(int source, int target, int capacity, int flow) {
			this.source = source;
			this.target = target;
			this.capacity = capacity;
			this.flow = flow;
			this.opposite = null;
		}
		public int cf() {
			return this.capacity - this.flow;
		}
		public void AddFlow(int flow) {
			this.flow += flow;
			this.opposite.flow -= flow;
		}
		public String toString() {
			return "(" + source + ", " + target + ", " + (capacity - flow) + ")";
		}
	}

	public ArrayList<Edge> FindViablePath(ArrayList<Node> nodes, int source, int sink) {
		ArrayList<ArrayList<Edge>> paths = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < nodes.size(); ++i)
			paths.add(new ArrayList<Edge>());
		boolean[] T = new boolean[nodes.size()];
		for (int i = 0; i < nodes.size(); ++i)
			T[i] = false;
		Queue<Node> Q = new LinkedList<Node>();
		Q.offer(nodes.get(source));
		T[source] = true;
		while (Q.peek() != null) {
			Node current = Q.poll();
			for (Edge e : current.edges) {
				if (e.cf() > 0) {
					int next = e.target;
					if (!T[next]) {
						Q.offer(nodes.get(next));
						T[next] = true;
						paths.get(next).addAll(paths.get(e.source));
						paths.get(next).add(e);
					}
				}
			}
		}
		return paths.get(sink);
	}

	public int Flow(ArrayList<Node> nodes, ArrayList<Edge> edges, int source, int sink) {
		ArrayList<Edge> path = FindViablePath(nodes, source, sink);
		int maxFlow = 0;
		while (path.size() != 0) {
			//System.out.println(path);
			int min = Integer.MAX_VALUE;
			for (Edge e : path)
				min = Math.min(e.cf(), min);
			maxFlow += min;
			for (Edge e : path)
				e.AddFlow(min);	
			//System.out.println(path);
			path = FindViablePath(nodes, source, sink);
		}
		return maxFlow;
	}

	public int Capacity(ArrayList<Node> nodes, ArrayList<Edge> edges, int source, int sink) {
		int[] T = new int[nodes.size()];
		for (int i = 0; i < nodes.size(); ++i)
			T[i] = -1;
		Queue<Node> Q = new LinkedList<Node>();	
		Q.offer(nodes.get(source));
		T[source] = Integer.MAX_VALUE;
		while (Q.peek() != null) {
			Node current = Q.poll();
			int minCap = T[current.id];
			for (Edge e : current.edges) {
				int nextMinCap = Math.min(e.capacity, minCap);	
				if (T[e.target] != -1) {
					if (nextMinCap > T[e.target]) {
						T[e.target] = nextMinCap;
						Q.offer(nodes.get(e.target));
					}
				}
				else {
					T[e.target] = nextMinCap;
					Q.offer(nodes.get(e.target));
				}
			}
		}
		return T[sink];
	}

	public E() {
		Scanner in = new Scanner(System.in);
		int caseCount = in.nextInt();
		for (int c = 1; c <= caseCount; ++c) {
			int setNum = in.nextInt();
			int nodeCount = in.nextInt();
			int edgeCount = in.nextInt();
			int source = in.nextInt();
			int sink = in.nextInt();
			ArrayList<Node> nodes = new ArrayList<Node>(nodeCount);
			for (int i = 0; i < nodeCount; ++i)
				nodes.add(new Node(i));
			ArrayList<Edge> edges = new ArrayList<Edge>(edgeCount);
			for (int i = 0; i < edgeCount; ++i) {
				int s = in.nextInt();
				int t = in.nextInt();
				int capacity = in.nextInt();
				Edge e = new Edge(s, t, capacity);
				Edge be = new Edge(t, s, capacity, capacity);
				e.opposite = be;
				be.opposite = e;
				nodes.get(s).AddEdge(e);
				nodes.get(t).AddEdge(be);
				edges.add(e);
			}
			int maxFlow = Flow(nodes, edges, source, sink);
			int maxCap = Capacity(nodes, edges, source, sink);
			//System.out.printf("%d %d\n", maxFlow, maxCap);
			System.out.printf("%d %.3f\n", setNum, (double)maxFlow/(double)maxCap);
		}
	}

	public static void main(String[] args) {
		E p = new E();
	}
}
