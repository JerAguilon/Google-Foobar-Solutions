import java.util.*;
import java.math.*;

public class LineUpTheCaptives {  
	private static Map<Integer, BigInteger> factorials;
    private static Map<List<Integer>, BigInteger> solutionCache = new HashMap<>();
     
    public static String answer (int leftCount, int rightCount, int rabbits) {
        factorials = generateFactorials();
        BigInteger total = BigInteger.ZERO;
        for (int i = 1; i <= rabbits; i++) {
            BigInteger left = getPermutations(leftCount - 1, i - 1);
            BigInteger right = getPermutations(rightCount - 1, rabbits - i);
            
            total = total.add(left.multiply(right).multiply(combination(rabbits - 1, i - 1)));
        }
        
        return total.toString();
        
    }

 	public static BigInteger getPermutations(int targetShow, int numberOfRabbits) {
		if (numberOfRabbits == targetShow) return BigInteger.ONE;
		if (numberOfRabbits < targetShow) return BigInteger.ZERO;
        List<Integer> argList = Arrays.asList(new Integer[] {targetShow, numberOfRabbits});
        
        if (solutionCache.containsKey(argList)) {
            return solutionCache.get(argList);
        }

		BigInteger total = BigInteger.ZERO;
		for (int i = 1; i <= numberOfRabbits; i++) {
			total = total.add(getPermutations(targetShow - 1, i - 1).multiply(factorials.get(numberOfRabbits - i)).multiply(combination(numberOfRabbits - 1, i - 1)));
		}
		
		if (!solutionCache.containsKey(argList)) {
		    solutionCache.put(Arrays.asList(targetShow, numberOfRabbits), total);
		}

		return total;

	}
	
	private static BigInteger combination(int n, int r) {
		BigInteger N = factorials.get(n);
		BigInteger R = factorials.get(r);
		
		BigInteger NR = factorials.get(n - r);
		
		return N.divide(R.multiply(NR));
	}
	
	private static Map<Integer, BigInteger> generateFactorials() {
		Map<Integer, BigInteger> output = new HashMap<>();

		output.put(0, BigInteger.ONE);

		for (int i = 1; i <= 40; i++) {
		    BigInteger previousValue = output.get(i - 1);
		    BigInteger currentIndex = new BigInteger(Integer.toString(i));
			output.put(i, previousValue.multiply(currentIndex));
		}

		return output;
	}
}