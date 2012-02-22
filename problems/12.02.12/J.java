import java.util.*;

public class J {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for(int caseNum = 0; caseNum < caseCount; ++caseNum) {
            String source = in.next();
            String target = in.next();
            int trainCount = in.nextInt();
            ArrayList<Train> trains = new ArrayList<Train>(trainCount);
            for (int i = 0; i < trainCount; ++i) {
                Train t = new Train(in.next(), in.next(), in.nextInt(),
                                        in.nextInt(), in.nextInt(), in.nextInt())); 
                trains.add(t);
            }
        }
    }

    public static class Train {
        String source;
        String target;
        int departureTime;
        int journeyTime;
        int delayP;
        int maxDelay;
        double expected;
        public Train(String source, String target, int dt, int jt, int dp, int md) {
            this.source = source;
            this.target = target;
            this.departureTime = dt;
            this.journeyTime = jt;
            this.delayP = dp;
            this.maxDelay = md;
            this.expected = (double)jt + (((double)md+1)/2.0)*(((double)dp)/100.0)
        }
    }
}
