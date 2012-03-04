import java.util.*;

public class I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String code = in.nextLine();
        ArrayList<Character> type = new ArrayList<Character>();
        int i = 0;
        char current = code.charAt(i);
        // gets type
        while (!IsSpace(current)) {
            type.add(current);    
            ++i;
            current = code.charAt(i);
        }

        // skips space
        ++i; 
        current = code.charAt(i);
        
        int currVar = 0;
        ArrayList<ArrayList<Character>> vars = new ArrayList<ArrayList<Character>>();
        ArrayList<ArrayList<Character>> ops = new ArrayList<ArrayList<Character>>();
        vars.add(new ArrayList<Character>());
        ops.add(new ArrayList<Character>());
        boolean parsingVar = true;
        while (!IsSemicolon(current)) {

            if (!parsingVar) { // reading operators
                if (IsComma(current)) { // found , no longer reading operators
                    parsingVar = true;
                    ++currVar;
                    i += 2; // skips comma and space
                    current = code.charAt(i);
                    vars.add(new ArrayList<Character>());
                    ops.add(new ArrayList<Character>());
                }
                else {
                    ops.get(currVar).add(current);
                    ++i;
                    current = code.charAt(i);
                }
            }

            else {
                if (IsOperator(current)) { // found operator
                    parsingVar = false;
                }
                else { // reading in variable name
                    vars.get(currVar).add(current);
                    ++i;
                    current = code.charAt(i);
                }
            }
        }
        for (int k = 0; k < vars.size(); ++k) {
            // Prints type
            for (int j = 0; j < type.size(); ++j) {
                System.out.print(type.get(j));
            }
            ArrayList<Character> curr = ops.get(k);
            for (int j = curr.size()-1; j >= 0; --j) {
                System.out.print(Reverse(curr.get(j)));
            }
            System.out.print(' ');
            curr = vars.get(k);
            for (int j = 0; j < curr.size(); ++j) {
                System.out.print(curr.get(j));
            } 
            System.out.println(';');
        }
    }

    static char Reverse(char c) {
        if (IsLeftBracket(c)) 
            return ']';
        else if (IsRightBracket(c))
            return '[';
        else
            return c;
    }

    static boolean IsOperator(char c) {
        boolean isOperator = false;
        if (c == '*')
            return true;
        else if (c == '&')
            return true;
        else if (c == '[')
            return true;
        else if (c == ']')
            return true;
        else if (c == ';')
            return true;
        else if (c == ',')
            return true;
        else
            return false;
    }

    static boolean IsComma(char c) {
        return (c == ',');
    }

    static boolean IsSemicolon(char c) {
        return (c == ';');
    }

    static boolean IsLeftBracket(char c) {
        return (c == '[');
    }

    static boolean IsRightBracket(char c) {
        return (c == ']');
    }

    static boolean IsSpace(char c) {
        return (c == ' ');
    }
}
