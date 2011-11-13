import java.util.*;
import java.lang.*;

/* *
 * Reads in a list of circles and calculates the area they cover
 * using the Monte Carlo method.
 * */
public class montecarlo
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        double xDim = in.nextDouble();
        double yDim = in.nextDouble();
        int numCircles = in.nextInt();
        while (xDim != 0.0 && yDim != 0.0)
        {
            ArrayList<Circle> circles = new ArrayList<Circle>();
            for (int i = 0; i < numCircles; i++)
            {
                double x = in.nextDouble();
                double y = in.nextDouble();
                double r = in.nextDouble();
                circles.add(new Circle(x, y, r));
            }
            System.out.printf("%.2f\n", CalcArea(circles, xDim, yDim));
            xDim = in.nextDouble();
            yDim = in.nextDouble();
            numCircles = in.nextInt();
        }
    }

    private static double CalcArea(ArrayList<Circle> circles, double xDim, double yDim)
    {
        int numTests = 10000000;
        int inCircles = 0;
        Random rGen = new Random();
        for (int i = 0; i < numTests; i++)
        {
            double x = rGen.nextDouble() * xDim;
            double y = rGen.nextDouble() * yDim;
            for (Circle c : circles)
            {
                if(Math.sqrt(Math.pow(c.x-x, 2) + Math.pow(c.y-y, 2)) <= c.r)
                {
                    inCircles++;
                    break;
                }
            }
        }
        return (double)inCircles / (double)numTests * xDim * yDim;
    }

    private static class Circle
    {
        public double x;
        public double y;
        public double r;

        public Circle(double x, double y, double r)
        {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}
