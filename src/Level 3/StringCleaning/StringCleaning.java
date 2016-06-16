import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class StringHolder implements Comparable<String> {
    static String string;

    public int length() {
        return string.length();
    }

    public int compareTo(String other) {
        return string.compareTo(other);
    }

    public String toString() {
        return string;
    }
}

public class StringCleaning {
    public static String answer(String chunk, String word) {
        StringHolder best = new StringHolder();
        best.string = chunk;
        Set<String> visited = new HashSet<String>();
        transform(chunk, word, best, visited);
        return best.toString();
    }

    private static void transform(String chunk, String word, StringHolder best,
                                  Set<String> visited) {
        if (chunk.length() < best.length()) {
            best.string = chunk;
        } else if (chunk.length() == best.length()
                && best.compareTo(chunk) > 0) {
            best.string = chunk;
        }

        List<Integer> kmpResults = kmp(chunk, word);

        //base case 1: Stop recursing if you can't remove anymore
        if (kmpResults.size() == 0) return;

        for (int i : kmpResults) {
            String removedWord = chunk.substring(0, i) + chunk.substring(i
                    + word.length(), chunk.length());
            //base case 1: Stop recursing if you've processed that case already
            if (!visited.contains(removedWord)) {
                visited.add(removedWord);
                transform(removedWord, word, best, visited);

            }
        }
    }

    //Failure table for KMP. Copied over from CS1332 homework
    private static int[] buildFailureTable(String word) {
        int[] table = new int[word.length()];
        table[0] = 0;

        int i = 1;
        int j =0;

        while (i < word.length()) {
            if (word.charAt(i) == word.charAt(j)) {
                table[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = table[j - 1];
            } else {
                i++;
            }
        }

        return table;
    }

    //kmp algorithm. I chose this because I'm betting that the
    //test cases utilize small alphabets, which means that kmp
    //would perform better than Boyer-Moore or Rabin-Karp
    private static List<Integer> kmp(String chunk, String word) {
        int n = chunk.length();
        int m = word.length();

        if (m > n) {
            return new ArrayList<>();
        }

        int[] table = buildFailureTable(word);
        int i = 0;
        int j = 0;

        ArrayList<Integer> result = new ArrayList<>();

        //while chars left in pattern is less than chars in text
        while (m - j <= n - i) {
            if (chunk.charAt(i) == word.charAt(j)) {
                if (j == m - 1) {
                    result.add(i - m + 1);
                    j = table[j];
                    //Note to self: we may have to change this to just go forward
                } else {
                    j++;
                }
                i++;
            } else if (j > 0) {
                j = table[j - 1];
            } else {
                i++;
            }
        }

        return result;
    }
}