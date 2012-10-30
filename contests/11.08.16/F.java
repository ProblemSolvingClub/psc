import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.HashMap;

public class F
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		for (int i = 0; i < N; i++)
		{
			int L = input.nextInt();
			input.nextLine();
			ArrayList<String> lines = new ArrayList<String>(L);
			for (int j = 0; j < L; j++)
			{
				lines.add(j, input.nextLine());
			}
			limits(lines);
		}
	}

	public static void limits(ArrayList<String> lines)
	{
		Pattern start = Pattern.compile("[0-9]+ START");
		Pattern hold = Pattern.compile("[0-9]+ HOLD [0-9]+");
		Pattern cond = Pattern.compile("[0-9]+ HOLD [0-9]+ IF [a-z]+");
		Pattern negCond = Pattern.compile("[0-9]+ HOLD [0-9]+ IF NOT [a-z]+");

		int holdTime = 0;
		int startTime = 0;

		HashMap<String,ArrayList<Integer>> events = new HashMap<String,ArrayList<Integer>>();

		for (String line : lines)
		{
			if (negCond.matcher(line).matches())
			{
				String[] elems = line.split(" ");
				int n = -1*Integer.valueOf(elems[2]);
				String condition = elems[5];
				if (events.containsKey(condition))
					events.get(condition).add(n);
				else
				{
					ArrayList<Integer> ints = new ArrayList<Integer>();
					ints.add(n);
					events.put(condition, ints);
				}
			}
			else if (cond.matcher(line).matches())
			{
				String[] elems = line.split(" ");
				int n = Integer.valueOf(elems[2]);
				String condition = elems[4];
				if (events.containsKey(condition))
					events.get(condition).add(n);
				else
				{
					ArrayList<Integer> ints = new ArrayList<Integer>();
					ints.add(n);
					events.put(condition, ints);
				}
			}
			else if (hold.matcher(line).matches())
			{
				String[] elems = line.split(" ");
				holdTime += Integer.valueOf(elems[2]);
			}
			else if (start.matcher(line).matches())
			{
				String[] elems = line.split(" ");
				startTime = Integer.valueOf(elems[0]);
			}
		}

	}
}
