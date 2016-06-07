import java.util.ArrayList;
import java.util.List;

public class Answer {   
    public static String answer(String chunk, String word) { 
        StringBuilder sbChunk = new StringBuilder(chunk);
        String current = chunk;
        while (!current.equals(transform(sbChunk, word))) {
            current = sbChunk.toString();
        }
        
        return sbChunk.toString();
    } 
    
    private static String transform(StringBuilder sbChunk, String word) {
        String chunk = sbChunk.toString();
        
        List<Integer> kmpResult = kmp(chunk, word);
        
        int previous = chunk.length() + 1;
        for (int i = kmpResult.size() - 1; i >= 0; i--) {
            int index = kmpResult.get(i);
            
            if (index + word.length() >  previous) {
                continue;
            }
            
            previous = index;
            sbChunk.delete(index, index + word.length());
        }
        
        return sbChunk.toString();
        
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