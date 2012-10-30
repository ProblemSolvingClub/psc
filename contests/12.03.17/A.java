import java.util.*;

/* *
 * Givin lines of input, this program will output those lines with the ith word of every
 * line aligned to the same column.
 * */

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            lines.add(Parse(line));
        }
        ArrayList<String> formatted = Format(lines);
        for (String s : formatted) {
            System.out.println(s);
        }
    }

    static ArrayList<String> Parse(String s) {
        int idx = 0;
        int startIdx = 0;
        boolean isWord = false;
        ArrayList<String> words = new ArrayList<String>();
        for (idx = 0; idx < s.length(); ++idx) {
            if (s.charAt(idx) == ' ') { // space character
                if (isWord) { // word is terminated
                    isWord = false;
                    words.add(s.substring(startIdx, idx));
                }
            }
            else { // non-space character
                if (!isWord) { // word begins
                    isWord = true;
                    startIdx = idx;
                }
            }
        }
        if (isWord) {
            words.add(s.substring(startIdx, s.length()));
        }
        return words;
    }

    static ArrayList<String> Format(ArrayList<ArrayList<String>> lines) {
        ArrayList<String> formatted = new ArrayList<String>();
        for (int i = 0; i < lines.size(); ++i) {
            formatted.add("");
        }
        int maxWords = 0;
        for (ArrayList<String> line : lines) {
            maxWords = Math.max(line.size(), maxWords);
        }
        for (int word = 0; word < maxWords; ++word) {
            int maxWordSize = 0;
            for (ArrayList<String> line : lines) {
                if (line.size() > word) {
                    maxWordSize = Math.max(line.get(word).length(), maxWordSize);
                }
            }
            for (int i = 0; i < lines.size(); ++i) {
                if (lines.get(i).size() > word) {
                    String w = lines.get(i).get(word);
                    if (lines.get(i).size() != word+1) {
                        int spaceCount = maxWordSize-w.length()+1;
                        for (int j = 0; j < spaceCount; ++j) {
                            w = w.concat(" ");
                        }
                    }
                    formatted.set(i, formatted.get(i).concat(w));
                }
            }
        }
        return formatted;
    }
}
