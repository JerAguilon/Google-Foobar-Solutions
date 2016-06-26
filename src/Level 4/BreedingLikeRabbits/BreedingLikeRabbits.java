/**
 * Created by jeremy on 6/23/16.
 */
import java.util.*;
import java.math.BigInteger;
public class BreedingLikeRabbits {
    private static final BigInteger MAX_VALUE = (new BigInteger("10")).pow(25);
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");

    private static Map<BigInteger, BigInteger> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(answer("100"));
    }

    public static String answer(String strS) {
        map.put(BigInteger.ZERO, ONE);
        map.put(BigInteger.ONE, ONE);
        map.put(new BigInteger("2"), TWO);
        BigInteger result = binarySearch(BigInteger.ZERO, MAX_VALUE, new BigInteger(strS));
        if (result != null) return result.toString();
        else return "None";
    }

    public static BigInteger R(BigInteger key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        BigInteger newValue;
        if (key.mod(TWO).equals(BigInteger.ZERO)) {
            newValue = R(key.divide(TWO))
                    .add(R(key.divide(TWO).add(ONE)))
                    .add(key);
        } else {
            newValue = R((key.subtract(ONE)).divide(TWO).subtract(ONE))
                    .add(R((key.subtract(ONE)).divide(TWO)))
                    .add(BigInteger.ONE);
        }

        map.put(key, newValue);
        return newValue;
    }

    public static BigInteger binarySearch(BigInteger low, BigInteger high, BigInteger target) {
        if (high.compareTo(low) < 0) return null;

        BigInteger currentVal = low.add(high).divide(TWO);

        BigInteger output = R(currentVal);
        if (target.equals(output)) return currentVal;
        else {
            if (target.compareTo(output) < 0) {
                return binarySearch(low, currentVal.subtract(ONE), target);
            } else {
                return binarySearch(currentVal.add(ONE), high, target);
            }
        }
    }


}