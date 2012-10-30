import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Elevator> elevators = new ArrayList<Elevator>(m);
        for (int i = 0; i < m; ++i) {
            Elevator e = new Elevator(in.nextInt(), in.nextInt());
            elevators.add(e);
        }
        int minFloor = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            int minE = MinFloor(e, n);
            minFloor = Math.min(minE, minFloor);
            //System.out.println(minE);
            //System.out.println();
        }
        System.out.println(minFloor);
    }

    static int MinFloor(Elevator e, int n) {
        int min = 1;
        int max = n; 
        int mid = max;
        //System.out.printf("Up %d Down %d\n", e.up, e.down);
        while (Calc(e, n, mid) != 0 && min <= max) {
            mid = (min+max)/2;
            int floor = Calc(e, n, mid);
            if (floor < 0)
                min = mid+1;
            else if (floor - (e.up + e.down) < 0)
                break;
            else
                max = mid-1;
            //System.out.printf("Min %d Max %d Mid %d Calc %d\n", min, max, mid, Calc(e, n, mid));
        }
        int floor = Calc(e, n, mid);
        return (floor == 0) ? (e.up + e.down) : floor;
    }

    static int Calc(Elevator e, int n, int k) {
        return k*e.up - (n-k)*e.down;
    }

    private static class Elevator {
        public int up;
        public int down;
        public Elevator(int up, int down) {
            this.up = up;
            this.down = down;
        }
    }
}
