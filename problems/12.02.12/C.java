import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for (int caseNum = 0; caseNum < caseCount; ++caseNum) {
            int movieCount = in.nextInt();
            int watchCount = in.nextInt();
            ArrayList<Integer> toWatch = new ArrayList<Integer>(watchCount);
            for (int i = 0; i < watchCount; ++i) {
                toWatch.add(in.nextInt()-1);
            }
            for (int n : Locations(toWatch, movieCount)) {
                System.out.printf("%d ", n);
            }
            System.out.println();
        }
    }

    static ArrayList<Integer> Locations(ArrayList<Integer> toWatch, int movieCount) {
        ArrayList<Integer> movies = new ArrayList<Integer>(movieCount);
        for (int i = 0; i < movieCount; ++i)
            movies.add(i+1);
        ArrayList<Integer> positions = new ArrayList<Integer>(toWatch.size());
        for (int i = 0; i < toWatch.size(); ++i)
            positions.add(toWatch.get(i)-1);
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int n : toWatch) {
            int index = positions.get(n);
            results.add(index);
            movies.remove(index);
            movies.add(n);
        }
    }
}
