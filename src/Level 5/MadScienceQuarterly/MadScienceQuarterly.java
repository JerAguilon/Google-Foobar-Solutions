/**
 * Created by jeremy on 7/11/16.
 */

public class MadScienceQuarterly {

    public static int answer(int[] L, int k) {
        int initialCount = 0;
        int maximum = 0; //there will always be a positive number, so we're fine to set this to 0

        for (int i = 0; i < k; i++) {
            initialCount += L[i];
        }

        while (k != 0) {
            int currVal = initialCount;

            for (int i = 0; i <= L.length - k; i++) {
                if (currVal > maximum) maximum = currVal;
                if (i + k < L.length) currVal = currVal - L[i] + L[i + k];
            }

            k--;
            initialCount -= L[k];
        }
        return maximum;
    }

}
