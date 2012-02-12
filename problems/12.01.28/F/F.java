import java.util.*;
import java.math.*;

public class F {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		int n = in.nextInt();
		ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>(n);
		for (int i = 0; i < n; ++i) {
			data.add(new ArrayList<Integer>());
		}
		Pair carl = new Pair(in.nextInt(), in.nextInt());
		data.get(carl.year-2011).add(carl.strength);
		for (int i = 0; i < n+k-2; ++i) {
			int year = in.nextInt();
			int strength = in.nextInt();
			data.get(year-2011).add(strength);
		}
		ArrayList<Integer> accumulated = new ArrayList<Integer>();
		for (int year = 0; year < n; ++year) {
			ArrayList<Integer> yearData = data.get(year);
			int yearMax = Math.max(FindMax(yearData), FindMax(accumulated));
			if (yearMax == carl.strength) {
				System.out.println(year+2011);
				return;
			}
			accumulated = Merge(yearData, accumulated, yearMax);	
			//System.out.println(accumulated);
		}
		System.out.println("unknown");
	}

	static int FindMax(ArrayList<Integer> set) {
		int max = 0;
		for (int elem : set) {
			if (elem > max) {
				max = elem;
			}
		}
		return max;
	}

	static ArrayList<Integer> Merge(ArrayList<Integer> a, ArrayList<Integer> b, int skip) {
		ArrayList<Integer> merged = new ArrayList<Integer>(a.size()+b.size()-1);
		for (int elem : b) {
			if (elem != skip)
				merged.add(elem);
		}
		for (int elem : a) {
			if (elem != skip)
				merged.add(elem);
		}
		return merged;
	}

	static class Pair {
		public int year;
		public int strength;
		public Pair(int year, int strength) {
			this.year = year;
			this.strength = strength;	
		}
	}
}
