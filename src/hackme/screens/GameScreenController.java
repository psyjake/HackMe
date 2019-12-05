package hackme.screens;

import hackme.gamelogic.GameEngine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    public ScreenReturner returner = null;

    private GameEngine gameEngine;
    private List<String> lstWords;
    private String strPassword;
    private int iNumOfLives;
    private int iScore;
    private GameEngine.Difficulty difficulty;
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
     * Set up the game
     */
    public GameScreenController()
    {
        System.out.println("game controller constructor");
        gameEngine = new GameEngine();
        iNumOfLives = 4;
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

    /**
     * set the difficulty & create the list of words and password
     * @param difficulty the difficulty of the game
     */
    public void initialiseGameWordVariables(GameEngine.Difficulty difficulty) {
        this.difficulty = difficulty;
        lstWords = gameEngine.getWordList(difficulty);
        strPassword = gameEngine.getPassword(lstWords);
    }
}