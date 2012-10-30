// Google is Feeling Lucky

import java.util.*;

class Main {

	private class Pair {
		public String url;
		public int score;
		public Pair(String url, int score) {
			this.url = url;
			this.score = score;
		}
	}

	private Main() {
		Scanner in = new Scanner(System.in);
		int caseCount = in.nextInt();
		in.nextLine();
		for (int c = 0; c < caseCount; ++c) {
			ArrayList<Pair> ls = new ArrayList<Pair>(10);
			for (int i = 0; i < 10; ++i) {
				String url = in.next();
				int score = in.nextInt();
				if (in.hasNextLine())
					in.nextLine();
				ls.add(new Pair(url, score));
			}
			int bestScore = Integer.MIN_VALUE;
			for (Pair p : ls)
				bestScore = Math.max(bestScore, p.score);
			ArrayList<Pair> best = new ArrayList<Pair>();
			for (Pair p : ls) {
				if (p.score == bestScore)
					best.add(p);
			}
			System.out.printf("Case #%d:\n", c+1);
			for (Pair p : best)
				System.out.println(p.url);
		}
	}

	public static void main(String[] args) {
		Main problem = new Main();
	}
}

