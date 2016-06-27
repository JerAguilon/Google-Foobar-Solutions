/**
 * Created by jeremy on 6/23/16.
 */

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
public class BreedingLikeRabbits {
    private static final BigInteger MAX_VALUE = (new BigInteger("10")).pow(25);
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");

    private static Map<BigInteger, BigInteger> map = new HashMap<>();


    public static String answer(String strS) {
        map.put(BigInteger.ZERO, ONE);
        map.put(ONE, ONE);
        map.put(TWO, TWO);
        BigInteger evenResult = binarySearch(BigInteger.ZERO, MAX_VALUE, new BigInteger(strS), true);
        BigInteger oddResult = binarySearch(ONE, MAX_VALUE.subtract(ONE), new BigInteger(strS), false);

        if (evenResult == null && oddResult == null) return "None";
        if (evenResult != null && oddResult == null) return evenResult.toString();
        if (evenResult == null && oddResult != null) return oddResult.toString();

        if (oddResult.compareTo(evenResult) > 0) return oddResult.toString();
        else return evenResult.toString();
    }

    public static BigInteger R(BigInteger key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        BigInteger newValue;
        if (key.mod(TWO).equals(BigInteger.ZERO)) {
            newValue = R(key.divide(TWO))
                    .add(R(key.divide(TWO).add(ONE)))
                    .add(key.divide(TWO));
        } else {
            newValue = R((key.subtract(ONE)).divide(TWO).subtract(ONE))
                    .add(R((key.subtract(ONE)).divide(TWO)))
                    .add(BigInteger.ONE);
        }

        map.put(key, newValue);
        return newValue;
    }

    public static BigInteger binarySearch(BigInteger low, BigInteger high, BigInteger target, boolean isEven) {
        if (high.compareTo(low) < 0) return null;
        BigInteger currentVal = low.add(high).divide(TWO);

        if (isEven) {
            if ((currentVal.mod(TWO)).equals(ONE)) currentVal = currentVal.add(ONE);
        } else {
            if ((currentVal.mod(TWO)).equals(BigInteger.ZERO)) currentVal = currentVal.add(ONE);
        }

        BigInteger output = R(currentVal);
        if (target.equals(output)) return currentVal;
        else {
            if (target.compareTo(output) < 0) {
                return binarySearch(low, currentVal.subtract(TWO), target, isEven);
            } else {
                return binarySearch(currentVal.add(TWO), high, target, isEven);
            }
        }
    }


}