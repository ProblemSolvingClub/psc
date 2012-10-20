import java.util.*;

public class C {

	public void arrayToString(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; ++i)
			System.out.printf("%d, ", arr[i]);
		System.out.printf("%d]\n", arr[arr.length-1]);
	}

	public int Rotation(int[] alphaMap, int alphaSize, char a, char b) {
		return (alphaMap[a] - alphaMap[b] + alphaSize) % alphaSize;
	}

	public int[] BuildAlphaMap(String alpha) {
		int[] alphaMap = new int[128];
		for (int i = 0; i < alpha.length(); ++i)
			alphaMap[alpha.charAt(i)] = i;
		return alphaMap;
	}

	public int[] Transform(String text, int[] alphaMap, int alphaSize) {
		int[] diff = new int[text.length()-1];
		for (int i = 0; i < text.length()-1; ++i)
			diff[i] = Rotation(alphaMap, alphaSize, text.charAt(i+1), text.charAt(i));
		return diff;
	}

	public int MatchLength(int[] S, int idx1, int idx2) {
		if (idx1 == idx2)
			return (S.length - idx1);
		int matchCount = 0;
		while (idx1 < S.length && idx2 < S.length && S[idx1] == S[idx2]) {
			++matchCount;
			++idx1;
			++idx2;
		}
		return matchCount;
	}

	public int[] ZAlgo(int[] s) {
		int[] z = new int[s.length];
		z[0] = s.length;
		if (s.length == 1)
			return z;
		z[1] = MatchLength(s, 0, 1);
		int l = 0;
		int r = 0;
		for (int i = 2+z[1]; i < s.length; ++i) {
			if (i <= r) {
				int k = i-l;
				int b = z[k];
				int a = r-i+1;
				if (b < a)
					z[i] = b;
				else if (b > a) {
					z[i] = Math.min(b, s.length-i);
					l = i;
					r = i+z[i]-1;
				}
				else {
					z[i] = b+MatchLength(s, a, r+1);
					l = i;
					r = i+z[i]-1;
				}
			}
			else {
				z[i] = MatchLength(s, 0, i);
				if (z[i] > 0) {
					l = i;
					r = i+z[i]-1;
				}
			}
		}
		return z;
	}

	public int[] BuildSP(int[] p) {
		int[] sp = new int[p.length];
		int[] z = ZAlgo(p);
		for (int j = p.length-1; j > 0; --j) {
			int i = j + z[j] - 1;
			sp[i] = z[j];
		}
		return sp;
	}

	public ArrayList<Integer> KMP(int[] P, int[] T) {
		ArrayList<Integer> matches = new ArrayList<Integer>();
		if (P.length == 0 || T.length < P.length)
			return matches;
		int[] sp = BuildSP(P);
		int c = 0;
		int p = 0;
		while (c+P.length-p-1 < T.length) {
			while (p < P.length && P[p] == T[c]) {
				++c;
				++p;
			}
			if (p == P.length)
				matches.add(c - P.length);
			if (p == 0)
				++c;
			else
				p = sp[p-1];
		}
		return matches;
	}

	public void Problem(String alpha, String plain, String crypt) {
		ArrayList<Integer> matches = null;
		int[] alphaMap = BuildAlphaMap(alpha);
		if (plain.length() == 1) {
			matches = new ArrayList<Integer>(crypt.length());
			for (int i = 0; i < crypt.length(); ++i)
				matches.add(i);
		}
		else {
			int[] plainT = Transform(plain, alphaMap, alpha.length());
			int[] cryptT = Transform(crypt, alphaMap, alpha.length());
			matches = KMP(plainT, cryptT);
			//arrayToString(plainT);
			//arrayToString(cryptT);
			//System.out.println(matches);
		}
		Map<Integer, Integer> uMap = new HashMap<Integer, Integer>();
		for (int idx : matches) {
			int diff = Rotation(alphaMap, alpha.length(), crypt.charAt(idx), plain.charAt(0));
			if (uMap.containsKey(diff))
				uMap.put(diff, uMap.get(diff)+1);
			else
				uMap.put(diff, 1);
		}
		ArrayList<Integer> uniqueMatches = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> x : uMap.entrySet()) {
			if (x.getValue() == 1)
				uniqueMatches.add(x.getKey());
		}
		Collections.sort(uniqueMatches);
		if (uniqueMatches.size() == 0)
			System.out.println("no solution");
		else if (uniqueMatches.size() == 1)
			System.out.printf("unique: %d\n", uniqueMatches.get(0));
		else {
			System.out.print("ambiguous: ");
			for (int i = 0; i < uniqueMatches.size()-1; ++i)
				System.out.printf("%d ", uniqueMatches.get(i));
			System.out.println(uniqueMatches.get(uniqueMatches.size()-1));
		}
	}

	public C() {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        in.nextLine();
        for (int i = 0; i < caseCount; ++i) {
            String alpha = in.nextLine();
            String plain = in.nextLine();
            String crypt = in.nextLine();
			Problem(alpha, plain, crypt);
        }
	}

    public static void main(String[] args) {
		C problem = new C();
	}
}
