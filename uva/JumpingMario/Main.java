import java.util.*;

class Main {
    private Main() {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for (int c = 0; c < caseCount; ++c) {
            int wallCount = in.nextInt();
            ArrayList<Integer> walls = new ArrayList<Integer>(wallCount);
            for (int i = 0; i < wallCount; ++i)
                walls.add(in.nextInt());
            int highJumps = 0;
            int lowJumps = 0;
            for (int i = 0; i < wallCount-1; ++i) {
                int diff = walls.get(i+1) - walls.get(i);
                if (diff > 0)
                    highJumps++;
                else if (diff < 0)
                    lowJumps++;
            }
            System.out.printf("Case %d: %d %d\n", c+1, highJumps, lowJumps);
        }
    }

    public static void main(String[] args) {
        Main problem = new Main();
    }
}
