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
    private GameEngine gameEngine;
    private List<String> lstWords;
    private String strPassword;
    private int iNumOfLives;
    private int iScore;

    @FXML
    private Text txtCorrectCharsAmount;
    /**
     *
     */
    public GameController()
    {
        System.out.println("game engine created");
        gameEngine = new GameEngine();
        iNumOfLives = 4;
    }

    /**
     *To store the parent scene from Main class
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * initialise the start scene
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
     */
    @FXML
    public void setWordsList (List lstWords) {
        this.lstWords = lstWords;
        this.strPassword = this.gameEngine.getPassword(lstWords);
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
}