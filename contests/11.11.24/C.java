import java.util.*;
import java.lange.*;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        while (N != 0) {
            float x = in.nextFloat();
            float y = in.nextFloat();
            float r = in.nextFloat();
            ArrayList<Tower> towers = new ArrayList<Tower>();
            for (int i = 0; i < N-1; i++) {
                towers.add(in.nextFloat(), in.nextFloat(), in.nextFloat());
            }
            System.out.printf("%.2f\n", Covered(x, y, r, towers));
            N = in.nextInt();
        }
    }

    public static float Covered(float x, float y, float r, ArrayList<Tower> tower) {
        
    }

    public static class Tower {
        public float x;
        public float y;
        public float r;
        public void Tower(float x, float y, float r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
        public boolean InRange(float x, float y) {
            return (Math.sqrt(Math.pow(x-this.x,2) + Math.pow(y-this.y,2)) <= r);
        }
    }
}
