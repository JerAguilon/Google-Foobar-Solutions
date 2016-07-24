import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeremy on 6/21/16.
 */
public class BinaryBunniesTest {

    @Test
    public void testGivenScenario1() {
        assertEquals("6", BinaryBunnies.answer(new int[] {5, 9, 8, 2, 1}));
    }

    @Test
    public void testGivenScenario2() {
        assertEquals("1", BinaryBunnies.answer(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));

    }

    @Test
    public void largeTree1() {
        assertEquals("8", BinaryBunnies.answer(new int[] {1, -1, 3, 2, 4}));
    }

    @Test
    public void largeTree2() {
        assertEquals("12600", BinaryBunnies.answer(new int[] {50, 17, 72, 12, 23, 54, 76, 9, 14, 19, 67}));
    }

}