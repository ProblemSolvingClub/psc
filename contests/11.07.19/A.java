import java.util.*;

public class A
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int zMin = input.nextInt();
		int zMax = input.nextInt();
		Triple[] points = new Triple[n];
		for (int i = 0; i < n; i++)
		{
			points[i] = new Triple(input.nextInt(), input.nextInt(), input.nextInt());
		}
		aero(n, zMin, zMax, points);
	}

	public static void aero(int n, int zMin, int zMax, Triple[] points)
	{
		for (int i = zMin; i <= zMax; i++)
		{
			ArrayList<Point> hullPoints = new ArrayList<Point>();
			ArrayList<Triple> lessThan = new ArrayList<Triple>();
			ArrayList<Triple> greaterThan = new ArrayList<Triple>();
			for (int j = 0; j < n; j++)
			{
				if (points[j].z < i)
					lessThan.add(points[j]);
				else if (points[j].z > i)
					greaterThan.add(points[j]);
				else
					hullPoints.add(new Point((double)points[j].x, (double)points[j].y));
			}
			for (int j = 0; j < lessThan.length; j++)
			{
				Triple p1 = lessThan.get(j);
				int dLeft = Math.abs(i - p1.z);
				for (int k = 0; k < greaterThan.length; k++)
				{
					Triple p2 = greaterThan.get(k);
					int dx = p2.x - p1.x;
					int dy = p2.y - p1.y;
					int dz = p2.z - p1.z;
					double x = (double)dx / (double)dz * (double)dLeft + (double)p1.x;
					double y = (double)dy / (double)dz * (double)dLeft + (double)p1.y;
					hullPoints.add(new Point(x, y));
				}
			}
		}

	}

	static class Triple
	{
		public int x, y, z;
		Triple(int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Point
	{
		public double x, y;
		Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
