/**
 * Created by jeremy on 6/10/16.
 */

import java.util.ArrayList;
import java.util.List;

public class HashingItOut {
    static final int MAX_C = 129 * 255;

    public static List<Integer> cValues(int dVal) {
        ArrayList<Integer> cValues = new ArrayList<>();

        for (int i = 0; 256 * i + dVal < MAX_C; i++) {
            cValues.add(256 * i + dVal);
        }

        return cValues;
    }

    public static int[] answer(int[] digest) {
        ArrayList<Integer> messages = new ArrayList<>();

        for (int i = 0; i < digest.length; i++) {
            List<Integer> cVals = cValues(digest[i]);

            int previousMessage;

            if (messages.size() == 0) previousMessage = 0;
            else previousMessage = messages.get(i - 1);

            int message = -1;
            for (int cVal : cVals) {

                int numerator = cVal ^ previousMessage;
                if (numerator % 129 == 0 && numerator / 129 < 255) {
                    message = numerator / 129;
                    break;
                }
            }

            messages.add(message);
        }

        Object[] arr = messages.toArray();
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = ((Integer) arr[i]).intValue();
        }

        return result;
    }
}