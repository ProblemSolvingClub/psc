import java.util.*;
import java.lang.Math;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCount = in.nextInt();
        for (int test = 0; test < testCount; ++test) {
            int pieCount = in.nextInt();
            int friendCount = in.nextInt() + 1;
            ArrayList<Integer> pies = new ArrayList<Integer>(pieCount);
            for (int i = 0; i < pieCount; ++i) {
                pies.add(in.nextInt());
            }
            Collections.sort(pies);
            int pieTotal = 0;
            for (int i = pies.size()-1; i >= 0; --i) {
                int pie = pies.get(i);
                float pieSize = (float)Math.pow(pie, 2); 
                if (pieSize >= (float)Math.pow(pieTotal,2)/friendCount) {
                    pieTotal += pie;
                }
                else {
                    break;
                }
            }
            System.out.println(Math.PI*Math.pow(pieTotal,2)/friendCount);
        }
    }
}
