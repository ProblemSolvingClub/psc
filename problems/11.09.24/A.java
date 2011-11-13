import java.util.*;
import java.lang.*;

public class A
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        double d = input.nextDouble();
        double f = input.nextDouble();
        while (d != -1.0 && f != -1.0)
        {
            ArrayList<Double> odom = new ArrayList<Double>();
            ArrayList<Double> fuel = new ArrayList<Double>();
            while (d != 0.0 && f != 0.0)
            {
                odom.add(d);
                fuel.add(f);
                d = input.nextDouble();
                f = input.nextDouble();
            }
            ArrayList<Double> rate = new ArrayList<Double>();
            for (int i = 0; i < odom.size()-1; i++)
            {
                double dFuel = fuel.get(i+1) - fuel.get(i);
                double dDist = odom.get(i) - odom.get(i+1);
                if (dFuel < 0.0)
                    rate.add(dDist/dFuel);
            }
            double sum = 0.0;
            for (double r : rate)
                sum += r;
            double avg = sum/rate.size();
            double range = avg * fuel.get(fuel.size()-1);
            System.out.println(StrictMath.round(range));
            d = input.nextDouble();
            f = input.nextDouble();
        }
    }
}
