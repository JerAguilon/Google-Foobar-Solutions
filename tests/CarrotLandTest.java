import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeremy on 8/3/16.
 */
public class CarrotLandTest {
    @Test
    public void testGivenScenario1() {
        int[][] input = new int[][] {{2, 3}, {6, 9}, {10, 160}};
        int result = CarrotLand.answer(input);

        assertEquals(289, result);
    }

    @Test
    public void testGivenScenario2() {
        int[][] input = new int[][] {{91207, 89566}, {-88690, -83026}, {67100, 47194}};

        int result = CarrotLand.answer(input);
        assertEquals(1730960165, result);
    }
}
