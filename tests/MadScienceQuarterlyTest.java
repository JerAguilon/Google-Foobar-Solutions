import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 7/11/16.
 */
public class MadScienceQuarterlyTest {
    @Test
    public void providedCase1() {
        int[] L = new int[] {-100, 95, 86, 47};
        int k = 3;

        assertEquals(228, MadScienceQuarterly.answer(L, k));
    }

    @Test
    public void providedCase2() {
        int[] L = new int[] {40, 91, -68, -36, 24, -67, -32, -23, -33, -52};
        int k = 7;

        assertEquals(131, MadScienceQuarterly.answer(L, k));
    }

    @Test public void negativeEdgeCase() {
        int[] L = new int[] {-42, -100, 0, 2, -3, -4, -5};
        int k = 7;

        assertEquals(2, MadScienceQuarterly.answer(L, k));
    }

}