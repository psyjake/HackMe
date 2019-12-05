package hackme.screens;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {
    public ScreenReturn returner = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startButton_Clicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("start");
    }

    public void exitButton_Clicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("exit");
    }
}
