// Lumberjack Sequencing

import java.util.*;

class Main {

    private Main() {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        System.out.println("Lumberjacks:");
        for (int c = 0; c < caseCount; ++c) {
            ArrayList<Integer> beards = new ArrayList<Integer>(10);
            for (int i = 0; i < 10; ++i)
                beards.add(in.nextInt());
            boolean lowFirst = (beards.get(1) - beards.get(0)) > 0;
            boolean ordered = true;
            for (int i = 0; i < 9; ++i) {
                if (beards.get(i+1) - beards.get(i) < 0) {
                    if (lowFirst) {
                        ordered = false;
                        break;
                    }
                }
                else {
                    if (!lowFirst) {
                        ordered = false;
                        break;
                    }    
                }
            }
            if (ordered)
                System.out.println("Ordered");
            else
                System.out.println("Unordered");
        }
    }

    public static void main(String[] args) {
        Main problem = new Main();
    }
}
