import java.util.*;

public class D
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        while (input.hasNext())
        {
            int n = input.nextInt();
            if (n == 1)
                System.out.println(1);
            else
            {
                int a = 11 % n;
                int digits = 2;
                while (a != 0)
                {
                    a = a*10+1;
                    a = a % n;
                    digits++;
                }
                System.out.println(digits);
            }
        }
    }
}


