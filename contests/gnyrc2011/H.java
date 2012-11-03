import java.util.*;

public class H {
	public long choose(int n, int k) {
		if (k == 0)
			return 1;
		if (n <= 0 || k > n || k < 0)
			return 0;
		long res = fact(n);
		res /= fact(n-k);
		res /= fact(k);
		return res;
	}

	public long fact(int n) {
		if (n == 0)
			return 1;
		if (n < 0)
			return 0;
		long res = 1;
		for (int i = 2; i <= n; ++i)
		   res *= i;
		return res;
	}

	public H() {
		Scanner in = new Scanner(System.in);
		int caseCount = in.nextInt();
		for (int c = 0; c < caseCount; ++c) {
			int setNum = in.nextInt();
			System.out.printf("%d ", setNum);
			int n = in.nextInt();
			int k = in.nextInt();
			long result = 0;
			if (n == 1)
			   result = 1;	
			else if (k == 1)
				result = fact(n-1);
			else {
				for (int i = 2; i <= k; ++i)
					result += choose(k-2, i-2)*fact(i-1)*fact(n-i);
			}
			System.out.println(result);
		}
	}

	public static void main(String[] args) {
		H p = new H();
	}
}
