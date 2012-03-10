import java.util.*;

public class D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        in.nextLine();
        String[][] table = new String[rows][cols];
        for (int i = 0; i < rows; ++i) {
            String record = in.nextLine();
            table[i] = record.split(",");
        }

        for (int j = 0; j < cols; ++j) {
            HashMap<String, ArrayList<Integer>> d = new HashMap<String, ArrayList<Integer>>();
            for (int i = 0; i < rows; ++i) {
                String key = table[i][j];
                if (d.containsKey(key)) {
                    d.get(key).add(i);
                }
                else {
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    indices.add(i);
                    d.put(key, indices);
                }
            }
            for (int i = 0; i < rows; ++i) {
                String key = table[i][j];
                for (int idx : d.get(key)) {
                    if (idx != i) {
                        Pair p = IsDuplicate(table[i], table[idx]);
                        if (p != null) {
                            System.out.println("NO");
                            System.out.printf("%d %d\n", i+1, idx+1);
                            System.out.printf("%d %d\n", (p.x)+1, (p.y)+1);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("YES");
    }

    static Pair IsDuplicate(String[] e1, String[] e2) {
        Pair p = new Pair();
        int matches = 0;
        for (int i = 0; i < e1.length; ++i) {
            if (e1[i].equals(e2[i])) {
                //System.out.printf("%s %s\n", e1[i], e2[i]);
                if (matches == 0) 
                    p.x = i; 
                else if (matches == 1)
                    p.y = i;
                ++matches; 
            }
        }
        return (matches < 2) ? null : p;
    }
    
    private static class Pair {
        public int x;
        public int y;
        public Pair() {
            this.x = 0;
            this.y = 0;
        }
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
