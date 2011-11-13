import java.util.*;

public class A
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		for (int i = 0; i < n; i++)
		{
			int sum = input.nextInt();
			int diff = input.nextInt();
			int a = (sum + diff) / 2;
			int b = (sum - diff) / 2;
			if (b < 0 || a < 0 || a + b != sum || Math.abs(a - b) != diff)
				System.out.println("impossible");
			else
			{
				if (a > b)
					System.out.printf("%d %d\n", a, b);
				else
					System.out.printf("%d %d\n", a, b);
			}
		}
	}
}
