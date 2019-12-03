package hackme;

import hackme.GameEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GameController {

    private Stage stage;
    private GameEngine.GameEngine gameEngine;
    private List<String> lstWords;
    private String strPassword;
    private int iNumOfLives;
    private int iScore;

    @FXML
    private Text txtCorrectCharsAmount;
    private Difficulty difficulty;

    /**
     * Set up the game
     */
    public GameController()
    {
        System.out.println("game controller constructor");
        gameEngine = new GameEngine();
        iNumOfLives = 4;

        //display
    }

    /**
     *To store the parent scene from Main class
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * start the end scene
     */
    public void startEndGameScene()
    {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("end.fxml"));
            root = loader.load();
            EndController controller = loader.getController();
            controller.setFinalScore(iScore);
            controller.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
    }

    /**
     *
     * @param mouseEvent
     */
    @FXML
    public void wordOnClick(MouseEvent mouseEvent) {
        Text text = (Text) mouseEvent.getSource();

        if(text.getText().equals(strPassword)) {
            //go to end game
            startEndGameScene();
        } else {
            iScore -= 500;
            if(--iNumOfLives < 1) {

                //you lose, end game
                startEndGameScene();
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