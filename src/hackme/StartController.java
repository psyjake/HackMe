package hackme;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class StartController {

    private Stage stage;
    //private GameEngine gameEngine;
    private List<String> lstWords;
    private String strPassword;

    @FXML
    private Text txtCorrectCharsAmount;

    /**
     *To store the parent scene from Main class
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * start the game scene
     */
    public void startGameScene()
    {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            root = loader.load();
            GameController controller = loader.getController();
            controller.setWordsList(lstWords);
            controller.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
    }
}