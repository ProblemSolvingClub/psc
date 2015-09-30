import java.util.*;

/**
 * bfs
 */
class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int e = sc.nextInt();
		ArrayList<ArrayList<Integer>> adj_list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < e; i++) {
			adj_list.add(new ArrayList<Integer>());
			int n = sc.nextInt();
			for (int j = 0; j < n; j++) {
				adj_list.get(i).add(sc.nextInt());
			}
		}


		int t = sc.nextInt();
		for (int T = 0; T < t; T++) {
			int s = sc.nextInt();
			Queue<Integer> q = new LinkedList<Integer>();
			ArrayList<Integer> dist = new ArrayList<Integer>();
			ArrayList<Integer> newnode = new ArrayList<Integer>();
			for (int i = 0; i < e; i++) {
				dist.add(-1);
				newnode.add(0);
			}
			newnode.add(0);
			// bfs
			q.add(s);
			dist.set(s, 0);
			while (!q.isEmpty()) {
				int z = q.poll();
				for (int neighbour : adj_list.get(z)) {
					if (dist.get(neighbour) == -1) {
						dist.set(neighbour, dist.get(z) + 1);
						q.add(neighbour);
						newnode.set(dist.get(neighbour), newnode.get(dist.get(neighbour)) + 1);
					}
				}
			}
			int max = 0, maxday = 0;
			for (int i = 0; i < newnode.size(); i++) {
				if (newnode.get(i) > max) {
					max = newnode.get(i);
					maxday = i;
				}
			}
			if (max == 0) {
				System.out.println(0);
			} else {
				System.out.println(max + " " + maxday);
			}
		}
	}
}