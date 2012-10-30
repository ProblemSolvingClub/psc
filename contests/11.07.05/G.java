import java.util.*;

public class G
{
	public static void findCatonym(String[] words)
	{

	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int cases = input.nextInt();
		for (int blah = 0; blah < cases; blah++)
		{
			int numWords = input.nextInt();
			input.nexLine();
			String[] words = new String[numWords];
			for (int i = 0; i < numWords; i++)
				words[i] = input.nextLine()
			findCatonym(words);
		}
	}
}
