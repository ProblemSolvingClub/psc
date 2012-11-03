import java.util.*;

public class A {
	public A () {
		Scanner in = new Scanner(System.in);
		int caseCount = in.nextInt();
		for (int c = 0; c < caseCount; ++c) {
			int setNum = in.nextInt();
			System.out.printf("%d ", setNum);
			int r = in.nextInt();
			String s = in.next();
			for (int i = 0; i < s.length(); ++i) {
				for (int j = 0; j < r; ++j) {
					System.out.print(s.charAt(i));
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		A p = new A();
	}
}
