import java.util.*;
import java.lang.*;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int A = in.nextInt();
        int B = in.nextInt();
        while (!(A == 0 && B == 0 && N == 0)) {
            ArrayList<Team> teams = new ArrayList<Team>();
            for (int i = 0; i < N; i++) {
                teams.add(new Team(in.nextInt(), in.nextInt(), in.nextInt())); 
            }
            Collections.sort(teams);
            System.out.println(MinTravel(teams, A, B));
            N = in.nextInt();
            A = in.nextInt();
            B = in.nextInt();
        }
    }

    public static int MinTravel(ArrayList<Team> teams, int A, int B) {
        int distance = 0;
        for (Team t : teams) {
            //System.out.printf("DA: %d, DB: %d, B: %d\n", t.distA, t.distB, t.balloons);
            if (t.distA < t.distB) {
                // Take from A greedily
                if (t.balloons > A) {
                    distance += (t.distA * A);
                    t.balloons -= A;
                    A = 0;
                }
                else {
                    distance += (t.distA * t.balloons);
                    A -= t.balloons;
                    t.balloons = 0;
                }
                distance += (t.distB * t.balloons);
                B -= t.balloons;
                t.balloons = 0;
            }
            else {
                // Take from B greedily
                if (t.balloons > B) {
                    distance += (t.distB * B);
                    t.balloons -= B;
                    B = 0;
                }
                else {
                    distance += (t.distB * t.balloons);
                    B -= t.balloons;
                    t.balloons = 0;
                }
                distance += (t.distA * t.balloons);
                A -= t.balloons;
                t.balloons = 0;
            }
            //System.out.printf("NUMA: %d, NUMB: %d\n", A, B);
        }
        return distance;
    }

    public static class Team implements Comparable<Team> {
        public int balloons;
        public int distA;
        public int distB;
        public Team(int balloons, int distA, int distB) {
            this.balloons = balloons;
            this.distA = distA;
            this.distB = distB;
        }
        public int compareTo(Team o) {
            int thisDiff = Math.abs(this.distA - this.distB);
            int otherDiff = Math.abs(o.distA - o.distB);
            return (otherDiff - thisDiff);
        }
    }
}
