import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCount = in.nextInt();
        for (int test = 0; test < testCount; ++test) {
            int jobCount = in.nextInt();
            int jobPos = in.nextInt();
            ArrayList<Integer> jobs = new ArrayList<Integer>();
            for (int i = 0; i < jobCount; ++i) {
                jobs.add(in.nextInt());
            }
            ArrayList<Integer> scratch = new ArrayList<Integer>(jobs);
            Collections.sort(scratch);
            int minutes = 0;
            int rotation = 0;
            while (true) {
                if (jobs.get(rotation) == scratch.get(scratch.size()-1)) {
                    minutes++; 
                    if (rotation == jobPos) {
                        break;
                    }
                    if (rotation < jobPos) {
                        jobPos--;
                    }
                    jobs.remove(rotation);
                    scratch.remove(scratch.size()-1);
                    rotation = rotation % jobs.size();
                }  
                else {
                    rotation = (rotation + 1) % jobs.size(); 
                }
            }
            System.out.println(minutes);
        }
    }
}
