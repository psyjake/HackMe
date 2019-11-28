package hackme.gamelogic;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
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
     * @return a list of randomly selected words of the correct length
     */
    public List<String> getWordList(Difficulty difficulty) {
        Scanner wordScanner = getWordFileScanner(difficulty);
        ArrayList<String> allWords = new ArrayList<>();//A list to contain all words from file.
        while (wordScanner.hasNextLine()) {
            allWords.add(wordScanner.nextLine());
        }
        Collections.shuffle(allWords);//Randomise the order of the list.
        ArrayList<String> wordList = new ArrayList<>();

        //Add the first ten elements to the final list.
        for (int i = 0; i < 10; i++) {
            wordList.add(allWords.get(i));
        }
        return wordList;
    }

    /**
     * Create a Scanner object for the appropriate word bank file
     *
     * @param difficulty The current difficulty of the game (to determine which file to load)
     * @return Scanner object that can be used to iterate over the right file
     */
    private Scanner getWordFileScanner(Difficulty difficulty) {
        File wordFile;
        String path = System.getProperty("user.dir") + "\\src\\hackme\\resources\\";
        switch (difficulty) {
            case MODERATE:
                path += "ModerateWords.txt";
                break;
            case HARD:
                path  += "HardWords.txt";
                break;
            default://If 'easy' or erroneous, it will load EasyWords.
                path += "EasyWords.txt";
                break;
        }
        System.out.println(path);
        wordFile = new File(path);
        try {
            return new Scanner(wordFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid File URI", e);
        }
    }
}
