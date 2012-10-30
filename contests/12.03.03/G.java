import java.util.*;

public class G {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rH = in.nextInt();
        int rV = in.nextInt();
        int sH = in.nextInt();
        int sV = in.nextInt();
        Screen toBuild = new Screen(rH, rV, sH, sV);
        int screenCount = in.nextInt();
        ArrayList<Screen> screens = new ArrayList<Screen>(screenCount);
        for (int i = 0; i < screenCount; ++i) {
            rH = in.nextInt();
            rV = in.nextInt();
            sH = in.nextInt();
            sV = in.nextInt();
            int price = in.nextInt();
            screens.add(new Screen(rH, rV, sH, sV, price));
            screens.add(new Screen(rV, rH, sV, sH, price));
        }
        int minPrice = Integer.MAX_VALUE;
        for (Screen s : screens) {
            int resV = NumScreens(toBuild.rV, s.rV);
            int sizeV = NumScreens(toBuild.sV, s.sV);
            int needV = Math.max(resV, sizeV);

            int resH = NumScreens(toBuild.rH, s.rH);
            int sizeH = NumScreens(toBuild.sH, s.sH);
            int needH = Math.max(resH, sizeH);

            int price = needH * needV * s.price;
            minPrice = Math.min(price, minPrice);
        }
        System.out.println(minPrice);
    }

    static int NumScreens(int buildS, int gS) {
        int screens = (buildS % gS == 0) ? (buildS / gS) : (buildS / gS + 1);
        return screens;
    }

    private static class Screen {
        public int rH;
        public int rV;
        public int sH;
        public int sV;
        public int price;
        public Screen(int rH, int rV, int sH, int sV) {
            this.rH = rH;
            this.rV = rV;
            this.sH = sH;
            this.sV = sV;
            this.price = 0;
        }
        public Screen(int rH, int rV, int sH, int sV, int price) {
            this.rH = rH;
            this.rV = rV;
            this.sH = sH;
            this.sV = sV;
            this.price = price;
        }
    }
}
