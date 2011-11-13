import java.util.*;

public class D
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Character> letters = new ArrayList<Character>();
        letters.add('F');
        letters.add('D');
        letters.add('C');
        letters.add('B');
        letters.add('A');
        while(input.hasNextLine())
        {
            String line = input.nextLine();
            ArrayList<Character> grades = new ArrayList<Character>();
            for (int i = 0; i < line.length(); i++)
            {
                if (i % 2 == 0)
                {
                    grades.add(line.charAt(i));
                }
            }
            boolean valid = true;
            for (char grade : grades)
            {
                if (!letters.contains(grade))
                {
                    valid = false;
                }
            }
            if (valid)
            {
                double sum = 0.0;
                for (char grade : grades)
                {
                    sum += (double)letters.indexOf(grade);
                }
                double avg = sum / (double)grades.size();
                System.out.printf("%.2f\n", avg);
            }
            else
            {
                System.out.println("Unknown letter grade in input");
            }
        }
    }
}
