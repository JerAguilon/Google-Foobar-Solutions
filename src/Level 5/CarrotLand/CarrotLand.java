import java.math.BigInteger;
/**
 * Created by jeremy on 8/3/16.
 */

public class CarrotLand {

    public static int answer(int[][] vertices) {

        int[] vector1 = new int[] {
                vertices[1][0] - vertices [0][0],
                vertices[1][1] - vertices[0][1]
        };

        int[] vector2 = new int[] {
                vertices[2][0] - vertices[0][0],
                vertices[2][1] - vertices[0][1]
        };
        int area = area(vector1, vector2);

        int boundaryPoints = euclidsAlg(Math.abs(vertices[0][0] - vertices[1][0]),
                                        Math.abs(vertices[0][1] - vertices[1][1]))
                             + euclidsAlg(Math.abs(vertices[1][0] - vertices[2][0]),
                                        Math.abs(vertices[1][1] - vertices[2][1]))
                             + euclidsAlg(Math.abs(vertices[2][0] - vertices[0][0]),
                                        Math.abs(vertices[2][1] - vertices[0][1]));
        return area + 1 - (boundaryPoints / 2);

    }

    private static int area(int[] vector1, int[] vector2) {
        BigInteger criss = BigInteger.valueOf(vector1[0]).multiply(BigInteger.valueOf(vector2[1]));
        BigInteger cross = BigInteger.valueOf(vector1[1]).multiply(BigInteger.valueOf(vector2[0]));
        BigInteger val = criss.subtract(cross).divide(BigInteger.valueOf(2));

        return Math.abs(Integer.parseInt(val.toString()));
    }

    private static int euclidsAlg(int a, int b) {
        if (a== 0 || b == 0) {
            return a + b;
        }

        return euclidsAlg(b, a % b);
    }
}

