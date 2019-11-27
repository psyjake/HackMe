package hackme.gamelogic;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private GameEngine gameEngine;
    private int numberOfWords;
    private int sizeOfWords;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gameEngine = new GameEngine();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        gameEngine = null;
        numberOfWords = 10;
        sizeOfWords = 10;
    }

    @org.junit.jupiter.api.Test
    void testNumberOfWords() {
        List<String> words = gameEngine.getWordList(sizeOfWords);
        assertEquals(numberOfWords, words.size());
    }

    @org.junit.jupiter.api.Test
    void testSizeOfWords() {
        List<String> words = gameEngine.getWordList(sizeOfWords);
        Iterator<String> itrWords = words.iterator();

        do
        {
            if(itrWords.next().length() != 10)
            {
                fail("not all words have a length of 10");
            }
        }while(itrWords.hasNext());
    }
}