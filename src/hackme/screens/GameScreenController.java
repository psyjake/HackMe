package hackme.screens;

import hackme.gamelogic.GameEngine;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    public ScreenReturner returner;

    private GameEngine gameEngine;
    private int iNumOfLives;
    private GameEngine.Difficulty difficulty;

    private List<String> lstWords;
    private String strPassword;
    private int iScore;

    @FXML
    private ListView<String> listViewWords;

    @FXML
    private Text txtCorrectCharsAmount; // The text that displays on the screen the number of characters in the correct place


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
                String selectedWord = listViewWords.getSelectionModel().getSelectedItem();
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

        listViewWords.getItems().addAll(lstWords);

    }

    private void listWords() {
//        for (String s: gameEngine.wordList)
    }

    /**
     * Handle the action of clicking on a word
     *
     */
    public void wordOnClick(String word) {
        if(word.equals(strPassword)) {
            //go to end game
            System.out.println("GAME WON");
        } else {
            iScore -= 500;
            if(--iNumOfLives < 1) {
                //you lose, end game
                System.out.println("GAME LOST");
                return;
            }
            int iNumOfCorrectChars = gameEngine.correctCharacters(word, strPassword);
            System.out.println("NUMBER OF CORRECT CHARACTERS: " + iNumOfCorrectChars);
            //txtCorrectCharsAmount.setText(Integer.toString(iNumOfCorrectChars));
        }
    }
}