/**
 * Created by jeremy on 7/24/16.
 */
import java.math.BigInteger;
import java.util.*;

public class UndercoverUnderground {

    private static Map<List<Integer>, BigInteger> memoizedSolutions =
            new HashMap<>();
    private static Map<Integer, BigInteger> factorials =
            new HashMap<>();

    public static void main(String[] args) {
        System.out.println(answer(4, 3));
    }
    public static String answer(int n, int k) {
        factorials.put(0, BigInteger.ONE);
        for (int i = 1; i < 100; i++) {
            BigInteger newValue =
                    factorials.get(i -1).multiply(BigInteger.valueOf(i));
            factorials.put(i, newValue);
        }

        return buildAnswer(n, k).toString();
    }

    private static BigInteger choose(int n, int k) {

        if (k > n) return BigInteger.ZERO;

        if (!factorials.containsKey(n)) {
            BigInteger val = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                val = val.multiply(BigInteger.valueOf(i));
            }
            factorials.put(n, val);
        }


        if (!factorials.containsKey(k)) {
            BigInteger val = BigInteger.ONE;
            for (int i = 1; i <= k; i++) {
                val = val.multiply(BigInteger.valueOf(i));
            }
            factorials.put(k, val);
        }

        if (!factorials.containsKey(n - k)) {
            BigInteger val = BigInteger.ONE;
            for (int i = 1; i <= n - k; i++) {
                val = val.multiply(BigInteger.valueOf(i));
            }
            factorials.put(n - k, val);
        }



        BigInteger denominator = factorials.get(k).multiply(factorials.get(n - k));
        return factorials.get(n).divide(denominator);
    }

    private static BigInteger buildAnswer(int n, int k) {
        if (k == (n - 1)) {
            return BigInteger.valueOf((int) Math.pow(n, n - 2));
        }

        if (k < n - 1) {
            return BigInteger.ZERO;
        }

        List<Integer> memoizedKey = new ArrayList<>();
        memoizedKey.add(n);
        memoizedKey.add(k);

        if (memoizedSolutions.containsKey(memoizedKey))
            return memoizedSolutions.get(memoizedKey);

        memoizedSolutions.put(memoizedKey, memoizeNewResult(n, k));
        System.out.println("RESULT: " + memoizeNewResult(n,k).toString());
        return memoizedSolutions.get(memoizedKey);
    }

    private static BigInteger memoizeNewResult(int n, int k) {
        BigInteger invalidPaths = BigInteger.ZERO;

        for (int i = 0; i < n - 1; i++) {
            BigInteger inner = BigInteger.ZERO;

            for (int j = 0; j <= k; j++) {
                int innerN = (n - 1 - i) * (n - 2 - i) / 2;
                inner = inner.add(choose(innerN, j).multiply(buildAnswer(i + 1, k - j)));
            }

            invalidPaths = invalidPaths.add(choose(n - 1, i).multiply(inner));

        }
        int paths = n * (n - 1) / 2;
        return choose(paths, k).subtract(invalidPaths);
    }

}
