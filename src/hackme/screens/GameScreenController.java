package hackme.screens;

import hackme.gamelogic.GameEngine;
import hackme.gamelogic.RandomString;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameScreenController implements Initializable {
    public ScreenReturner returner;

    private GameEngine gameEngine;
    private int iNumOfLives;
    private GameEngine.Difficulty difficulty;

    private List<String> lstWords;
    private List<String> lstWordsObscured;
    private String strPassword;
    private int iScore;

    @FXML
    private Label attemptsLabel;

    @FXML
    private ListView<String> listViewWords;

    @FXML
    private Label feedbackLabel;


    /**
     * Initialise the controller
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("GameScreenController initialize");
        listViewWords.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                String selectedWord = lstWords.get(
                        listViewWords.getSelectionModel().getSelectedIndex());
                wordOnClick(selectedWord);
            }
        });
    }

    /**
     * Configure the game engine and start a new game
     * @param difficulty the difficulty of the game
     */
    public void initialiseGame(GameEngine.Difficulty difficulty) {
        gameEngine = new GameEngine();
        iNumOfLives = 4;

        this.difficulty = difficulty;
        lstWords = gameEngine.getWordList(difficulty);
        strPassword = gameEngine.getPassword(lstWords);

        int length = 0;
        switch (difficulty)
        {
            case EASY: length = 5; break;
            case MODERATE: length = 7; break;
            case HARD: length = 9; break;
        }

        // Duplicate word list and add random characters
        lstWordsObscured = new ArrayList<String>();

        for (String word: lstWords) {
            String random = new RandomString(40 - length).nextString();
            int pos = ThreadLocalRandom.current().nextInt(0, 40 - length - length + 1);

            String subA = random.substring(0, pos);
            String subB = random.substring(pos, random.length() - 1);
            lstWordsObscured.add(subA + word + subB);
        }

        listViewWords.getItems().addAll(lstWordsObscured);
    }

    /**
     * Handle the action of clicking on a word
     * @param word the selected word
     */
    public void wordOnClick(String word) {
        System.out.println("Password: " + strPassword);
        if (word.equals(strPassword)) {
            System.out.println("GAME WON");
            feedbackLabel.setText("GAME WON");

            try {
                returner.onScreenReturn("won");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            iScore -= 500;
            if (--iNumOfLives < 1) {
                feedbackLabel.setText("GAME LOST");
                System.out.println("GAME LOST");

                try {
                    returner.onScreenReturn("lost");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            int iNumOfCorrectChars = gameEngine.correctCharacters(word, strPassword);
            System.out.println("NUMBER OF CORRECT CHARACTERS: " + iNumOfCorrectChars);
            String strFeedback = feedbackLabel.getText() + "\n" + word + ": " + iNumOfCorrectChars;
            feedbackLabel.setText(strFeedback);

            String strAttempts = "Remaining attempts = " + iNumOfLives;
            attemptsLabel.setText(strAttempts);
        }
    }
}