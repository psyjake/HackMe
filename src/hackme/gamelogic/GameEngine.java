package hackme.gamelogic;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameEngine {

    public enum Difficulty {
        EASY, MODERATE, HARD
    }

    /**
     * Returns a random list of words from the wordbank.
     *
     * @param difficulty The current difficulty, this determines the length of retrieved words
     * @return a list of randomly selected words of the correct length.
     */
    public List<String> getWordList(Difficulty difficulty) {
        Scanner wordScanner = getWordFileScanner(difficulty);
        ArrayList<String> wordList = new ArrayList<>();
        while (wordScanner.hasNextLine() && wordList.size() < 10) {
            wordList.add(wordScanner.nextLine());
        }
        return wordList;
        //TODO read random words, not first 10.
    }

    //TODO properly read each file
    private Scanner getWordFileScanner(Difficulty difficulty) {
        File wordFile;
        switch (difficulty) {
            case EASY:
                wordFile = new File(getClass().getResource("EasyWords.txt").getPath());
            case MODERATE:
                wordFile = new File(getClass().getResource("ModerateWords.txt").getPath());
            case HARD:
                wordFile = new File(getClass().getResource("HardWords.txt").getPath());
            default:
                wordFile = new File(getClass().getResource("EasyWords.txt").getPath());
        }
        try {
            return new Scanner(wordFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid File URI", e);
        }
    }
}
