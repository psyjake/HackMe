package hackme.gamelogic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomStringTest {

    private RandomString randomString;
    private int iLength;

    @BeforeEach
    void setUp() {
        iLength = 5;
        randomString = new RandomString(iLength);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNextString() {
        String strWord = randomString.nextString();

        if(strWord.length() != iLength) {
            fail();
        }
    }
}