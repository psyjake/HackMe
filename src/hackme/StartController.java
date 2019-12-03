package hackme;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartController {

    private Stage stage;
    private List<String> lstWords;
    private String strPassword;
    private Difficulty difficulty;

    @FXML
    private Text txtCorrectCharsAmount;

    /**
     * setup the start scene
     */
    public StartController()
    {
        ArrayList<String[]> lstScoreboard = getScoreboard();
        //display scoreboard...
    }

    /**
     * Creates an ArrayList used to display the scoreboard
     * @return an ArrayList which holds [name, score] arrays.
     */
    private ArrayList<String []> getScoreboard() {
        Scanner scnWords = null;
        try {
            scnWords = new Scanner(new File("/resources/scoreboard.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String []> arrLstLines = new ArrayList<String []>();
        while (scnWords.hasNextLine()) {
            String strLine = scnWords.nextLine();
            int iIndex = strLine.indexOf(" ");
            String [] strArrLine = {strLine.substring(0, iIndex), strLine.substring(iIndex+1, strLine.length())};//split line into name, score array.
            arrLstLines.add(strArrLine);
        }

        return arrLstLines;
    }

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
            controller.setDifficulty(difficulty);
            controller.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
    }

    /**
     * To set the difficulty used to create the list of words in the game controller
     * @param actionEvent the action which occurred on one of 3 difficulty buttons
     */
    public void setDifficulty(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String strDifficulty = button.getText();

        if(strDifficulty.equals("Easy")) {
            this.difficulty = GameEngine.Difficulty.Easy;
        } else if(strDifficulty.equals("Moderate")) {
            this.difficulty = GameEngine.Difficulty.Moderate;
        } else {
            this.difficulty = GameEngine.Difficulty.Hard;
        }
    }
}