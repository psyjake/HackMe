package hackme.screens;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the start screen
 */
public class StartScreenController{
    public ScreenReturner returner = null;

    /**
     * Handle the easy level start button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void easyLevelButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("easyStart");
    }

    /**
     * Handle the medium level start button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void mediumLevelButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("mediumStart");
    }

    /**
     * Handle the hard level start button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void hardLevelButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("hardStart");
    }

    /**
     * Handle the exit button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void exitButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("exit");
    }
}