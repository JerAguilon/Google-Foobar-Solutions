import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 6/26/16.
 */
public class BreedingLikeRabbitsTest {
    @Test
    public void testBaseCases() {
        assertEquals("1", BreedingLikeRabbits.answer("1"));
        assertEquals("2", BreedingLikeRabbits.answer("2"));
    }

    @Test
    public void testGivenCases() {
        assertEquals("4", BreedingLikeRabbits.answer("7"));
        assertEquals("None", BreedingLikeRabbits.answer("100"));
    }

    @Test
    public void testMoreCases() {
        assertEquals("184", BreedingLikeRabbits.answer("532"));
        assertEquals("None", BreedingLikeRabbits.answer("186832"));
        assertEquals("42957", BreedingLikeRabbits.answer("186835"));
    }

}