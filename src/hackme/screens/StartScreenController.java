package hackme.screens;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {
    public ScreenReturner returner = null;

    /**
     * Initialise the controller
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Handle the exit button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void startButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("start");
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