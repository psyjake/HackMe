package hackme.gamelogic;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

/**
 * The utility functions of the game
 */
public class GameEngine {

    /**
     * The difficulty of the game
     */
    public enum Difficulty {
        EASY, MODERATE, HARD
    }

    String password;
    List<String> wordList;

    /**
     * Called when starting the game. Sets initial values etc.
     * @param difficulty The chosen difficulty for the game.
     */
    public void setupGame(Difficulty difficulty) {
        wordList = getWordList(difficulty);
        password = getPassword(wordList);
    }

    /**
     * Returns a random list of words from the wordbank.
     *
     * @param difficulty The current difficulty, this determines the length of retrieved words
     * @return a list of randomly selected words of the correct length
     */
    public List<String> getWordList(Difficulty difficulty) {
        Scanner scnWords = getWordFileScanner(difficulty);
        ArrayList<String> arrLstAllWords = new ArrayList<>();//A list to contain all words from file.
        while (scnWords.hasNextLine()) {
            arrLstAllWords.add(scnWords.nextLine());
        }
        Collections.shuffle(arrLstAllWords);//Randomise the order of the list.
        ArrayList<String> lstWords = new ArrayList<>();

        //Add the first ten elements to the final list.
        for (int i = 0; i < 10; i++) {
            lstWords.add(arrLstAllWords.get(i));
        }
        return lstWords;
    }

    /**
     * Create a Scanner object for the appropriate word bank file
     *
     * @param difficulty The current difficulty of the game (to determine which file to load)
     * @return Scanner object that can be used to iterate over the right file
     */
    private Scanner getWordFileScanner(Difficulty difficulty) {

        File fWords;
        String path = System.getProperty("user.dir") + "/src/hackme/resources/";

        switch ((difficulty != null) ? difficulty : difficulty.EASY) {
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
        fWords = new File(path);
        try {
            return new Scanner(fWords);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid File URI", e);
        }
    }

    /**
     *
     * @param lstWords the list of words that will be displayed on the screen
     * @return the word chosen as the password
     */
    public String getPassword(List<String> lstWords) {
        Iterator<String> itrWords = lstWords.iterator();
        int iIndex = new Random().nextInt(lstWords.size());

        //Iterate through the words for 'iIndex' times
        for(int i = 0; i<iIndex; i++) {
            itrWords.next();
        }
        return itrWords.next();
    }

    /**
     *
     * @param strPickedWord the word a user selected
     * @param strPassword the password chosen by the system
     * @return true or false depending if the strings match or not
     */
    public boolean matchPassword(String strPickedWord, String strPassword) {
        return strPickedWord == strPassword;
    }

    /**
     * Create a list of buttons that will display the text of the words
     * @param lstWords the list of words to display on the screen
     * @return the buttons for the list of words
     */
    public ArrayList<Button> createDisplayableWords(List<String> lstWords, EventHandler<ActionEvent> actionEvent)
    {
        ArrayList<Button> arrLstWordButtons = new ArrayList<>();
       Iterator<String> itrWords = lstWords.iterator();

       while(itrWords.hasNext()) {
           Button b = new Button();
           b.setOnAction(actionEvent);
           b.setText(itrWords.next());
           arrLstWordButtons.add(b);
       }

       return arrLstWordButtons;
    }

    /**
     * Counts the number of characters that are in the same position in the two words.
     * @param pickedWord what the user entered
     * @param password the correct password
     * @return number of equal characters between words
     */
    public int correctCharacters(String pickedWord, String password) {
        int correct = 0;

        if(pickedWord == null || password == null)
            return -1;

        if(pickedWord.length() != password.length())
            return 0;

        for(int i = 0; i < pickedWord.length(); i++) {
            char pickedCharacter = pickedWord.charAt(i);
            char passwordCharacter = password.charAt(i);
            if(pickedCharacter == passwordCharacter){
                correct += 1;
            }
        }
        return correct;
    }
}