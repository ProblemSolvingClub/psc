import java.util.*;

public class E {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String patternString = in.nextLine();
		Regex rg = new Regex(patternString);
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; ++i) {
			String name = in.nextLine();
			if (rg.Matches(name))
				System.out.println(name);
		}
	}

	public static class Regex {
		Node root;
		Node accept;
		public Regex(String pattern) {
			this.root = new Node();
			Node current = root;
			for (int i = 0; i < pattern.length(); ++i) {
				char c = pattern.charAt(i);
				if (c == '*') {
					current.AddEdge(new Edge(current, current, '?'));
				}
				else {
					Node next = new Node();
					current.AddEdge(new Edge(current, next, c));
					current = next;
				}
			}
			this.accept = current;
		}
		public boolean Matches(String toMatch) {
			ArrayList<Edge> edges = root.MatchesWith(toMatch.charAt(0));
			for (Edge e : edges) {
				if (e.target == this.accept && 0 == toMatch.length()-1) 
					return true;
				else if (RecurseMatch(toMatch, e.target, 1)) {
					return true;
				}
			}
			return false;
		}
		public boolean RecurseMatch(String toMatch, Node start, int idx) {
			if (idx == toMatch.length())
				return false;
			ArrayList<Edge> edges = start.MatchesWith(toMatch.charAt(idx));
			//System.out.printf("%s %c ", toMatch, toMatch.charAt(idx));
			//System.out.println(edges);
			for (Edge e : edges) {
				if (e.target == this.accept && idx == toMatch.length()-1) 
					return true;
				else if (RecurseMatch(toMatch, e.target, idx+1))
					return true;
			}
			return false;
		}
	}

	public static class Node {
		public ArrayList<Edge> out;	
		public Node() {
			this.out = new ArrayList<Edge>();
		}
		public void AddEdge(Edge newEdge) {
			this.out.add(newEdge);
		}
		public ArrayList<Edge> MatchesWith(char c) {
			ArrayList<Edge> matching = new ArrayList<Edge>();
			for (Edge e : out) {
				if (e.label == c || e.label == '?') {
					matching.add(e);
				}
			}
			return matching;
		}
	}

	public static class Edge {
		public char label;
		public Node source;
		public Node target;
		public Edge(Node source, Node target, char label) {
			this.source = source;
			this.target = target;
			this.label = label;
		}
	}
}
