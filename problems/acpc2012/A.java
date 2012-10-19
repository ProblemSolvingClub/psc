import java.util.*;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int inCount = in.nextInt();
        in.nextLine();
        for (int i = 0; i < inCount; ++i) {
            String line = in.nextLine();
            String[] ls = line.split("-");
            String as = ls[0].toUpperCase();
            int b = Integer.parseInt(ls[1]);
            int a = (as.charAt(0)-65)*26*26 + (as.charAt(1)-65)*26 
                    + as.charAt(2)-65;
            //System.out.println(line);
            //System.out.println(a);
            //System.out.println(b);
            if (Math.abs(a-b) <= 100)
                System.out.println("nice");
            else
                System.out.println("not nice");
        }
    }
}
