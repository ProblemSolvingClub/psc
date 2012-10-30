import java.util.*;
import java.lang.*;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Parallelogram p = new Parallelogram(new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble())); 
        Triangle t = new Triangle(new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble())); 
        while (!p.isZero() && !t.isZero()) {
            double area = t.area();
            /*
            System.out.printf("Area: %.3f\n", area);
            t.display();
            p.display();
            */
            p.calc(area);
            p = new Parallelogram(new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble())); 
            t = new Triangle(new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble()), new Point(in.nextDouble(), in.nextDouble())); 
        }
    }


    private static class Triangle {
        public Point D;
        public Point E;
        public Point F;
        public Triangle(Point D, Point E, Point F) {
            this.D = D;
            this.E = E;
            this.F = F;
        }
        public boolean isZero() {
            return (this.D.isZero() && this.E.isZero() && this.F.isZero());
        }
        public double area() {
            Point DE = this.D.vectorTo(this.E);
            Point DF = this.D.vectorTo(this.F);
            return Math.abs(DE.det(DF)) / 2;
        }
        public void display() {
            System.out.printf("triangle - D: (%.3f, %.3f) E: (%.3f, %.3f) F: (%.3f, %.3f)\n", this.D.x, this.D.y, this.E.x, this.E.y, this.F.x, this.F.y);
        }
    }

    private static class Parallelogram { 
        public Point A;
        public Point B;
        public Point C;
        public Parallelogram(Point A, Point B, Point C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }
        public boolean isZero() {
            return (this.A.isZero() && this.B.isZero() && this.C.isZero());
        }
        public void calc(double area) {
            Point u = this.A.vectorTo(this.B);
            Point w = this.A.vectorTo(this.C);
            double vx;
            double vy;
            if (u.y != 0.0 && w.x != 0.0) {
                vx = (w.x*area)/Math.abs(u.det(w));
                vy = (w.y/w.x)*vx;
            }
            else {
                vx = 0;
                vy = area / Math.sqrt(Math.pow(u.x, 2) + Math.pow(u.y, 2));
                if (w.y < 0)
                    vy *= -1;
            }
            Point v = new Point(vx, vy);
            Point H = this.A.addTailToTip(v);
            Point G = H.addTailToTip(u);
            System.out.printf("%.3f %.3f %.3f %.3f\n", G.x, G.y, H.x, H.y);
        }
        public void display() {
            System.out.printf("parallelogram - A: (%.3f, %.3f) B: (%.3f, %.3f) C: (%.3f, %.3f)\n", this.A.x, this.A.y, this.B.x, this.B.y, this.C.x, this.C.y);
        }
    }

    private static class Point {
        public double x;
        public double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public boolean isZero() {
            return (this.x == 0.0 && this.y == 0.0);
        }
        public Point vectorTo(Point tip) {
            return new Point(tip.x - this.x, tip.y - this.y);
        }
        public Point addTailToTip(Point v) {
            return new Point(this.x + v.x, this.y + v.y);
        }
        public double det(Point v) {
            return this.x*v.y - v.x*this.y; 
        }
    }
}
