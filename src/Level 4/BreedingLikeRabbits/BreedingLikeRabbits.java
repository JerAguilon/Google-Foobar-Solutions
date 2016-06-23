/**
 * Created by jeremy on 6/23/16.
 */
import java.util.*;
import java.math.BigInteger;
public class BreedingLikeRabbits {
    private static final BigInteger MAX_VALUE = (new BigInteger("10")).pow(25);
    private static Map<BigInteger, BigInteger> map = new HashMap<>();

    public static String answer(String strS) {
        map.put(BigInteger.ZERO, BigInteger.ONE);
        map.put(BigInteger.ONE, BigInteger.ONE);
        map.put(new BigInteger("2"), new BigInteger("2"));
        BigInteger result = binarySearch(BigInteger.ZERO, MAX_VALUE, new BigInteger(strS));
        if (result != null) return result.toString();
        else return "notfound";
    }

    public static BigInteger R(BigInteger key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        BigInteger newValue;
        if (key.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            newValue = R(key.divide(new BigInteger("2"))).add(R(key.divide(new BigInteger("2")).add(BigInteger.ONE))).add(key);
        } else {
            newValue = R((key.subtract(BigInteger.ONE)).divide(new BigInteger("2")).subtract(BigInteger.ONE)).add(R((key.subtract(BigInteger.ONE)).divide(new BigInteger("2")))).add(BigInteger.ONE);
        }

        map.put(key, newValue);
        return newValue;
    }

    public static BigInteger binarySearch(BigInteger low, BigInteger high, BigInteger target) {
        BigInteger midPoint = low.add(high.subtract(low).divide(new BigInteger("2")));

        if (high.compareTo(low) <= 0) {
            return null;
        }

        BigInteger currentAnswer = R(midPoint);
        if (currentAnswer.equals(target)) {
            return midPoint;
        }

        if (currentAnswer.compareTo(target) < 0) {
            return binarySearch(midPoint.add(BigInteger.ONE), high, target);
        }

        else {
            return binarySearch(low, midPoint.subtract(BigInteger.ONE), target);
        }
    }


}