import java.util.*;

public class B
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int initial = input.nextInt();
        int first = input.nextInt();
        int second = input.nextInt();
        int third = input.nextInt();
        while (initial != 0 || first != 0 || second != 0 || third != 0)
        {
            // Inititial turn
            int numTicks = 80;

            // Turn to first number
            int interval = initial - first;
            if (interval < 0)
                interval += 40;
            numTicks += interval;

            // Turn counter-clockwise one full turn
            numTicks += 40;

            // Turn to second number
            interval = second - first;
            if (interval < 0)
                interval += 40;
            numTicks += interval;

            // Turn to third number
            interval = second - third;
            if (interval < 0)
                interval += 40;
            numTicks += interval;

            System.out.println(numTicks*9);

            initial = input.nextInt();
            first = input.nextInt();
            second = input.nextInt();
            third = input.nextInt();
        }
    }
}
