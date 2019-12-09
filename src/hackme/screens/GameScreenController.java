package hackme.screens;

import hackme.gamelogic.GameEngine;
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
     * @param mouseEvent The event caused by clicking on a Text object
     */
    @FXML
    public void wordOnClick(MouseEvent mouseEvent) {
        Text text = (Text) mouseEvent.getSource();

        if(text.getText().equals(strPassword)) {
            //go to end game
        } else {
            iScore -= 500;
            if(--iNumOfLives < 1) {

                //you lose, end game
                return;
            }
            int iNumOfCorrectChars = gameEngine.correctCharacters(text.getText(), strPassword);
            txtCorrectCharsAmount.setText(Integer.toString(iNumOfCorrectChars));
        }
    }
}