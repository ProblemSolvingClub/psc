import java.util.*;

public class A
{
	static int LongestUnique(int[] flakes)
	{
		int currentMax = 0;
		int sequence = 0;
		int limit = 0;
		HashMap<Integer, Integer> uniques = new HashMap<Integer, Integer>();
		for (int i = 0; i < flakes.length; i++)
		{
			if (uniques.containsKey(flakes[i]) && uniques.get(flakes[i]) >= limit)
			{
				if (sequence > currentMax)
				{
					currentMax = sequence;
				}
				limit = uniques.put(flakes[i], i);
				sequence = i - limit;
				//System.out.printf("Collision - %d\n", sequence);
			}
			else
			{
				sequence++;
				uniques.put(flakes[i], i);
				//System.out.printf("No Collision - %d\n", sequence);
			}
		}
		if (sequence > currentMax)
		{
			currentMax = sequence;
		}
		return currentMax;		
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int cases = input.nextInt();
		for (int i = 0; i < cases; i++)
		{
			int numFlakes = input.nextInt();
			int[] flakes = new int[numFlakes];
			for (int j = 0; j < numFlakes; j++)
			{
				flakes[j] = input.nextInt();
			}
			System.out.println(LongestUnique(flakes));
		}
	}
}
