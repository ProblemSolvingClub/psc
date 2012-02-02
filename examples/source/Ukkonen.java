import java.util.*;

public class Ukkonen {

    public static void main(String[] args) {
        String testString = "BANANA";
        Ukkonen stree = new Ukkonen(testString);
        System.out.println("Hello, world!");
    }

    private String S;
    private Node root;
    public Ukkonen(String S) {
        this.S = S;
        this.root = new Node(0);
        int stringEnd = this.S.length();

        // Constructs initial implicit stree
        Node initialLeaf = new Node(1);
        Edge initialEdge = new Edge(this.root, initialLeaf, 1, stringEnd);
        this.root.AddEdge(initialEdge);

        int j = 1;
        for (int i = 1; i < stringEnd; ++i) {
            // Increments j - represents start of phase substring
            j++;

            // Determines the matching character
            char first = (j > i)? this.S.charAt(i) : this.S.charAt(j-1);

            // Determines which edge to follow, if any
            Edge path = null;
            ArrayList<Edge> fromRoot = this.root.EdgesFrom();
            for (Edge e : fromRoot) {
                char edgeStart = this.S.charAt(e.Start()-1); 
                if (edgeStart == first) {
                    path = e;
                    break;
                }
            }

            // If no path exists, create one to a leaf
            if (path == null) {
                Node newLeaf = new Node(i+1);
                Edge newEdge = new Edge(this.root, newLeaf, i+1, stringEnd);
                this.root.AddEdge(newEdge);
                continue;
            }
            // Otherwise, follow it using skip/jump
            else {

            }
        } 
    }

    public class Node {
        private int index;
        private Node suffixLink;
        private ArrayList<Edge> edgesFrom; 
        public Node(int index) {
            this.index = index;
            this.suffixLink = null;
            this.edgesFrom = new ArrayList<Edge>();
        }
        public int Index() {
            return this.index;
        } 
        public Node GetSuffixLink() {
            return this.suffixLink;
        }
        public void SetSuffixLink(Node linkTo) {
            this.suffixLink = linkTo;
        }
        public ArrayList<Edge> EdgesFrom() {
            return this.edgesFrom;
        }
        public void AddEdge(Edge edgeFrom) {
            this.edgesFrom.add(edgeFrom);
        }
    }

    public class Edge {
        private Node source;
        private Node target; 
        private int start;
        private int end;
        public Edge(Node source, Node target, int start, int end) {
            this.source = source;
            this.target = target;
            this.start = start;
            this.end = end;
        }
        public int Start() {
            return this.start;
        }
        public int End() {
            return this.end;
        }
        public int Length() {
            return this.end-this.start+1;
        }
        public Node Source() {
            return this.source;
        }
        public Node Target() {
            return this.target;
        }
    }
}

