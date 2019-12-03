package hackme;

import hackme.GameEngine;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EndController {

    private Stage stage;
    private int iScore;

    public EndController()
    {
        //display top 10 scores
    }

    /**
     *To store the parent scene from Main class
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * Handle key events
     * @param keyEvent the key event caused by a key press
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            //save away the name & score to a file, stop all key events from occuring again
        } else if(keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
            // remove a character from the end of the name Text object
        } else {
            // add char to the end of the name Text object
        }
    }

    @FXML
    public void restartOnClick()
    {
        //restart the game
    }

    @FXML
    public void closeOnClick()
    {
        //close the game
    }

    public void setFinalScore(int iScore) {
        this.iScore = iScore;
    }
}