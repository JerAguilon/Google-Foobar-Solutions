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

        int boundaryPoints = findBoundaryPoints(vertices[0], vertices[2])
                + findBoundaryPoints(vertices[1], vertices[2])
                + findBoundaryPoints(vertices[0], vertices[1])
                + 3;

        return area + 1 - (boundaryPoints / 2);

    }

    private static int area(int[] vector1, int[] vector2) {
        return (vector1[0] * vector2[1] - vector1[1] * vector2[0]) / 2;
    }

    //finds all boundary points that are not on a corner
    private static int findBoundaryPoints(int[] vertex1, int[] vertex2) {
        Fraction slope = new Fraction(vertex1, vertex2);

        int rise = slope.rise();
        int run = slope.run();

        int x = vertex1[0];
        int y = vertex1[1];
        int count = 0;
        while (x != vertex2[0] - run && y != vertex2[1] - rise) {
            count++;

            x += run;
            y += rise;
        }

        return count;
    }
}

class Fraction {
    private int numerator;
    private int denominator;
    public Fraction(int[] vertex1, int[] vertex2) {
        numerator = vertex2[1] - vertex1[1];
        denominator = vertex2[0] - vertex1[0];
        simplifySlope();
    }

    public void simplifySlope() {
        int gcm = gcm(numerator, denominator);
        numerator /= gcm;
        denominator /= gcm;
    }

    private static int gcm(int a, int b) {
        return b == 0 ? a : gcm (b, a % b);
    }

    public int rise() {
        return numerator;
    }

    public int run() {
        return denominator;
    }
}
