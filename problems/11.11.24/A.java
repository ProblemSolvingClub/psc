import java.util.*;
import java.lang.*;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuffer n = new StringBuffer(in.nextLine());
        while (!IsZero(n)) {
            System.out.println(FindNext(n));
            n = new StringBuffer(in.nextLine());
        }
    }

    public static boolean IsZero(StringBuffer n) {
        return (n.length() == 1 && n.charAt(0) == '0');
    }

    public static int FindNext(StringBuffer n) {
        int mileCount = 0;
        while (!IsPalindrome(n)) {
            mileCount++;
            Increment(n);
            //System.out.printf("Count %d String %s\n", mileCount, n);
        }
        return mileCount;
    }

    public static boolean IsPalindrome(StringBuffer n) {
        Stack<Character> read = new Stack<Character>();
        for (int decimalPlace = 0; decimalPlace < n.length(); decimalPlace++) {
            read.push(n.charAt(decimalPlace));
        }
        for (int decimalPlace = 0; decimalPlace < n.length(); decimalPlace++) {
            if (n.charAt(decimalPlace) != read.pop()) {
                return false;    
            }
        }
        return true;
    }

    public static void Increment(StringBuffer n) {
        boolean overflow = true;
        int decimalPlace = n.length()-1;
        while (overflow && decimalPlace >= 0) {
            int numericalValue = Character.getNumericValue(n.charAt(decimalPlace));
            numericalValue++;
            if (numericalValue == 10) {
                overflow = true;
                n.setCharAt(decimalPlace, '0');
            }
            else {
                overflow = false;
                n.setCharAt(decimalPlace, Character.forDigit(numericalValue, 10));
            }
            decimalPlace--;
        }
    }
}
