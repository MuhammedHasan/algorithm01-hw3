import edu.princeton.cs.algs4.*;

import java.util.*;

public class SortingWithNewOrdering {

    public static void main(String[] args) {
        HashMap<String, Integer> alphabet = new HashMap<>();
        String[] ordering = new In("NewOrdering.txt").readAllStrings();
        for (int i = 0; i < ordering.length; i++)
            alphabet.put(ordering[i], i);

        String[] lines = new In("InputNewOrdering.txt").readAllLines();
        Selection.sort(lines, new MyStringComparator(alphabet));

        Out outputFile = new Out("OutputNewOrdering.txt");
        for (String l : lines) outputFile.println(l);
    }
}

class MyStringComparator implements Comparator<String> {

    private HashMap<String, Integer> mapping = new HashMap<>();

    MyStringComparator(HashMap<String, Integer> stringMapping) {
        this.mapping = stringMapping;
    }

    @Override
    public int compare(String s, String t1) {
        for (int i = 0; i < Math.max(s.length(), t1.length()); i++) {
            if (i >= t1.length() || i >= s.length()) return t1.length() - s.length();

            int diff = this.mapping.get("" + s.charAt(i)) - this.mapping.get("" + t1.charAt(i));
            if (diff != 0) return diff;
        }
        return 0;
    }
}


