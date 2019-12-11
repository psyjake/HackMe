package hackme.gamelogic;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// Class taken from https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
// and removed unneeded code
public class RandomString {

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    private final Random random;
    private final char[] symbols = { '!', '\"', '£', '$', '%', '^', '&', '*', '(', ')', '-', '=', '_', '+', '[', '{', ']', '}', ';', ':', '\'', '@', '#', '~', ',', '<', '.', '>', '/', '?', '`', '¬', '\\', '|' };
    private final char[] buf;

    public RandomString(int length, Random random) {
        if (length < 1) throw new IllegalArgumentException();

        this.random = Objects.requireNonNull(random);
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public RandomString(int length) {
        this(length, ThreadLocalRandom.current());
    }
}