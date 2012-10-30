import java.util.*;
import java.lang.*;

public class A
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine())
        {
            double init = input.nextDouble();
            double interest = input.nextDouble();
            double target = input.nextDouble();
            //System.out.printf("%f %f %f\n", init, interest, target);
            double periods = (Math.log(target) - Math.log(init)) / Math.log(1.0 + interest/100.0);
            System.out.println((int)Math.ceil(periods));
            input.nextLine();
        }
    }
}
