import java.util.*;
import java.awt.geom.*;

public class G {
	public class Line {
		public boolean vert;
		public double a, b;
		public Line(Line2D.Double l) {
			Point2D p1 = l.getP1();
			Point2D p2 = l.getP2();
			double dx = Math.abs(p1.getX() - p2.getX());
			if (0.001 >= dx)
				this.vert = true;
			else {
				this.a = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
				this.b = p1.getY()-this.a*(p1.getX());
			}
		}
		public double solveY(double x) {
			return this.a*x+this.b;
		}
	}
	
	public Point2D.Double mp(Line2D.Double l) {
		Point2D p1 = l.getP1();
		Point2D p2 = l.getP2();
		double x = (p1.getX() + p2.getX())/2.0;
		double y = (p1.getY() + p2.getY())/2.0;
		return new Point2D.Double(x, y);
	}

	public Point2D.Double is(Line2D.Double gl1, Line2D.Double gl2) {
		Line l1 = new Line(gl1);
		Line l2 = new Line(gl2);
		if (l1.vert) {
			double x = gl1.getP1().getX();
			double y = l2.solveY(x);
			return new Point2D.Double(x, y);
		}
		else if (l2.vert) {
			double x = gl2.getP1().getX();
			double y = l1.solveY(x);
			return new Point2D.Double(x, y);
		}
		else {
			double x = (l2.b-l1.b)/(l1.a - l2.a);
			double y = l1.solveY(x);
			return new Point2D.Double(x, y);
		}
	}

	public Point2D.Double Vectorize(Line2D.Double l) {
		double dx = l.getP2().getX() - l.getP1().getX();
		double dy = l.getP2().getY() - l.getP1().getY();
		return new Point2D.Double(dx, dy);
	}

	double det(Point2D.Double v1, Point2D.Double v2) {
		return Math.abs(v1.getX()*v2.getY() - v2.getX()*v1.getY())/2.0;
	}

	public double area(Line2D.Double l1, Line2D.Double l2) {
		Point2D.Double v1 = Vectorize(l1);
		Point2D.Double v2 = Vectorize(l2);
		return det(v1, v2);
	}

	public double area(Point2D.Double p1, Point2D.Double p2,
						Point2D.Double p3, Point2D.Double p4) {
		double a = det(p1, p2) + det(p2, p3) + det(p3, p4) + det(p4, p1);
		return (a/2.0);
	}

	public double dist(Point2D.Double p1, Point2D.Double p2) {
		double dx = p2.getX() - p1.getX();
		double dy = p2.getY() - p1.getY();
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double perim(Point2D.Double p1, Point2D.Double p2,
						Point2D.Double p3, Point2D.Double p4) {
		return (dist(p1, p2) + dist(p2, p3) + dist(p3, p4) + dist(p4, p1));
	}

	public G() {
		Scanner in = new Scanner(System.in);
		int caseCount = in.nextInt();
		for (int c = 0; c < caseCount; ++c) {
			int setNum = in.nextInt();
			Point2D.Double A = new Point2D.Double(0.0, 0.0);
			double bx = in.nextDouble();
			Point2D.Double B = new Point2D.Double(bx, 0.0);
			double cx = in.nextDouble();
			double cy = in.nextDouble();
			Point2D.Double C = new Point2D.Double(cx, cy);
			double dx = in.nextDouble();
			double dy = in.nextDouble();
			Point2D.Double D = new Point2D.Double(dx, dy);

			Line2D.Double AB = new Line2D.Double(A, B);
			Line2D.Double AC = new Line2D.Double(A, C);
			Line2D.Double BC = new Line2D.Double(B, C);
			Line2D.Double CD = new Line2D.Double(C, D);
			Line2D.Double CA = new Line2D.Double(C, A);
			Line2D.Double DA = new Line2D.Double(D, A);

			Line2D.Double AA = new Line2D.Double(A, mp(BC));
			Line2D.Double BB = new Line2D.Double(B, mp(CD));
			Line2D.Double CC = new Line2D.Double(C, mp(DA));
			Line2D.Double DD = new Line2D.Double(D, mp(AB));

			Point2D.Double pAA = is(AA, BB);
			Point2D.Double pBB = is(BB, CC);
			Point2D.Double pCC = is(CC, DD); 
			Point2D.Double pDD = is(DD, AA);

			AA = new Line2D.Double(A, pAA);
			BB = new Line2D.Double(B, pBB);
			CC = new Line2D.Double(C, pCC);
			DD = new Line2D.Double(D, pDD);

			/*
			System.out.printf("INTER (%.3f, %.3f) (%.3f, %.3f) (%.3f, %.3f) (%.3f, %.3f)\n",
								pAA.getX(), pAA.getY(), pBB.getX(), pBB.getY(), pCC.getX(),
								pCC.getY(), pDD.getX(), pDD.getY());
								*/
			double Al = area(AB, AA);
			double Bob = area(BC, BB);
			double Chas = area(CD, CC);
			double Dave = area(DA, DD);
			//double Emily = area(pAA, pBB, pCC, pDD);
			double total = area(AB, AC) + area(CD, CA);
			double Emily = (total - Al - Bob - Chas - Dave);
			long Fence = (long)Math.ceil(perim(pAA, pBB, pCC, pDD)*16.5);
			double a = 160.0;

			System.out.printf("%d %.3f %.3f %.3f %.3f %.3f %d\n", setNum, 
								Al/a, Bob/a, Chas/a, Dave/a, Emily/a, Fence);
		}
	}

	public static void main(String[] args) {
		G p = new G();
	}
}
