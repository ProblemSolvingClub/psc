import java.util.*;
import java.lang.Math;

public class B
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int datasets = input.nextInt();
		for (int i = 0; i < datasets; i++)
		{
			int numStrongholds = input.nextInt();
			ArrayList<Stronghold> strongholds = new ArrayList<Stronghold>(numStrongholds);
			for (int j = 0; j < numStrongholds; j++)
			{
				strongholds.add(j, new Stronghold(input.nextInt(), input.nextInt(), input.nextInt()));
			}
			int I = input.nextInt();
			Collections.sort(strongholds);
			if (Routs(strongholds, I))
				System.out.println("ROUT!");
			else
				System.out.println("RETREAT!");
		}
	}

	public static boolean Routs(ArrayList<Stronghold> strongholds, int I)
	{
		int traveled = 0;
		for (Stronghold sh : strongholds)
		{
			//System.out.printf("Before: D - %d, J - %d, S - %d, I - %d, traveled - %d\n", sh.D, sh.J, sh.S, I, traveled);
			int F = I*(sh.D - traveled);
			int B = sh.J*sh.S*sh.S;
			if (F <= B)
				return false;
			I = (int)Math.ceil((double)I * (1.0 - ((double)B / (double)F)));
			traveled = sh.D;
			//System.out.printf("After: F - %d, B - %d, I - %d, tr - %d\n", F, B, I, traveled);
		}
		return true;
	}

	public static class Stronghold implements Comparable<Stronghold>
	{
		public int D, J, S;
		public Stronghold(int D, int J, int S)
		{
			this.D = D;
			this.J = J;
			this.S = S;
		}

		public int compareTo(Stronghold other)
		{
			return this.D - other.D;
		}
	}
}
