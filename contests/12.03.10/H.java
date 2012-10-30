import java.util.*;

public class H {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int zeroCount = 0;
        int oneCount = 0;
        int num = 0;
        boolean inSection = false;
        String chamber = in.nextLine();
        for (int i = 0; i < chamber.length(); ++i) {
            char spot = chamber.charAt(i);
            if (spot == '0') {
                ++zeroCount;
                if (inSection)
                    ++num;
                inSection = true;
            }
            else {
                ++oneCount;
                inSection = false;
            }
        }
        if (chamber.charAt(chamber.length()-1) == '0' && chamber.charAt(0) == '0')
            ++num;
        //System.out.printf("1s: %d 0s: %d num: %d\n", oneCount, zeroCount, num);
        if (chamber.length() * num < zeroCount * zeroCount) 
            System.out.println("ROTATE");
        else if (chamber.length() * num == zeroCount * zeroCount)
            System.out.println("EQUAL");
        else
            System.out.println("SHOOT");
    }
}
