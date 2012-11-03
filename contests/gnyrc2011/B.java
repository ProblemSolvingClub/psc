import java.util.*;

public class B {
	public B() {
		Scanner in = new Scanner(System.in);
		int caseCount = in.nextInt();
		for (int c = 0; c < caseCount; ++c) {
			int setNum = in.nextInt();
			System.out.printf("%d ", setNum);
			int row = in.nextInt();
			int col = in.nextInt();
			if (row < 0 || col < 0 || col > row)
				System.out.print(0);
			else if (col == 0 || col == row)
				System.out.print(1);
			else {
				int diag = 0;
				if (col <= (row/2))
					diag = col;
				else
					diag = row-col;
				long res = (diag)*(row-diag)+1;
				System.out.print(res);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		B p = new B();
	}
}
