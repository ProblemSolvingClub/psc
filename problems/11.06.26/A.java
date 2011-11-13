import java.util.*;
import java.lang.*;

public class A
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        while (a != 0 || b != 0)
        {
            int numCarries = 0;
            int carry = 0;
            for (int i = 0; i < 10; i++)
            {
                int digitA = (a % (int)Math.pow(10, i+1)) / (int)Math.pow(10, i);
                int digitB = (b % (int)Math.pow(10, i+1)) / (int)Math.pow(10, i);
                if (digitA + digitB + carry >= 10)
                {
                    carry = 1;
                    numCarries++;
                }
                else
                {
                    carry = 0;
                }
            }
            if (numCarries == 0)
                System.out.printf("No carry operation.\n");
            else if (numCarries == 1)
                System.out.printf("1 carry operation.\n");
            else
                System.out.printf("%d carry operations.\n", numCarries);
            a = input.nextInt();
            b = input.nextInt();
        }
    }
}
