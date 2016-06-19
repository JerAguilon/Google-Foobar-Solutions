import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jeremy on 6/6/16.
 */
public class StringCleaningTest {
    String chunk, word;

    @Test
    public void chunkEqualsWord() {
        chunk = "word";
        word = "word";
        String result = StringCleaning.answer(chunk, word);
        assertEquals("", result);
    }

    @Test
    public void wordReturnsNonEmptyString() {
        chunk = "asdfwordasdf";
        word = "word";

        String result = StringCleaning.answer(chunk, word);
        assertEquals("asdfasdf", result);
    }

    @Test
    public void wordNestedReturnsEmptyString() {
        chunk = "asasdfdf";
        word = "asdf";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("", result);

    }

    @Test
    public void wordHasAnOverlap() {
        chunk = "LOLOLababaOL";
        word = "aba";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("LOLOLabOL", result);
    }

    @Test
    public void wordDoesntOverlap() {
        chunk = "LOLOLabaLOLabaLOL";
        word = "aba";

        String result = StringCleaning.answer(chunk, word);
        assertEquals("LOLOLLOLLOL", result);
    }

    @Test
    public void wordLexicographicPreference() {
        chunk = "lololololo";
        word = "lol";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("looo", result);
    }

    @Test
    public void supplied() {
        chunk = "goodgooogoogfogoood";
        word = "goo";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("dogfood", result);
    }

    @Test
    public void wordsNextToEachOther() {
        chunk = "testasdfasdftest";
        word = "asdfasdf";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("testtest", result);
    }

    @Test
    public void removingStringGeneratesNewMatchToRightOfFound() {
        chunk = "abatestabababaa"; //[aba]test ab |ab| [aba] |a|;
        word = "aba";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("testba", result);
    }

    @Test
    public void testEmptyChunk() {
        chunk = "";
        word = "test";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("", result);
    }

    @Test
    public void testSingleCharacterWord() {
        chunk = "aaaaabbaababaaabababaa";
        word = "a";

        String result = StringCleaning.answer(chunk, word);

        assertEquals("bbbbbbb", result);
    }


}