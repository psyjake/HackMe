package hackme.gamelogic;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private GameEngine gameEngine;
    private int numberOfWords = 10;
    private int sizeOfWords = 7;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gameEngine = new GameEngine();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        gameEngine = null;
        numberOfWords = 10;
        sizeOfWords = 5;
    }

    @org.junit.jupiter.api.Test
    void testNumberOfWords() {
        List<String> words = gameEngine.getWordList(GameEngine.Difficulty.MODERATE);
        assertEquals(numberOfWords, words.size());
    }

    @org.junit.jupiter.api.Test
    void testSizeOfWords() {
        List<String> words = gameEngine.getWordList(GameEngine.Difficulty.MODERATE);
        Iterator<String> itrWords = words.iterator();

        do {
            if (itrWords.next().length() != sizeOfWords) {
                fail("not all words have a length of " + sizeOfWords);
            }
        } while (itrWords.hasNext());
    }
}