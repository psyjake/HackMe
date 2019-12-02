package hackme;

import hackme.gamelogic.GameEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.util.List;

public class Controller {

    @FXML
    private GameEngine gameEngine;
    private List<String> lstWords;
    private String strPassword;

    /**
     *
     */
    public Controller()
    {
        //GameManager gameManager = new GameManager();
        gameEngine = new GameEngine();
    }


    /**
     * initialise the start scene
     */
    public void initialiseStartScene()
    {

    }

    /**
     * initialise the start scene
     */
    public void initialiseGameScene()
    {
        //set text to buttons
        strPassword = gameEngine.getPassword(lstWords);
    }

    /**
     * initialise the start scene
     */
    public void initialiseEndScene()
    {
        //initialise end screen
    }

    /**
     *
     */
    @FXML
    public void createEasyList () {
        lstWords = gameEngine.getWordList(GameEngine.Difficulty.EASY);
    }

    /**
     *
     */
    @FXML
    public void createModerateList () {
        lstWords = gameEngine.getWordList(GameEngine.Difficulty.MODERATE);
    }

    /**
     *
     */
    @FXML
    public void createHardList () {
        lstWords = gameEngine.getWordList(GameEngine.Difficulty.HARD);
    }

    @FXML
    public void wordOnClick(ActionEvent e)
    {
        Button btn = (Button) e.getSource();

        int iNumOfCorrectChars = gameEngine.correctCharacters(btn.getText(), strPassword);
    }
}