package hackme;

import hackme.screens.GameScreenController;
import hackme.screens.ScreenReturner;
import hackme.screens.StartScreenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window = null;

    /**
     * An Application start function
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("HackMe");

        // Display the start screen
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("hackme/screens/StartScreenView.fxml"));
        Parent root = loader.load();

        // Deal with screen returning control back
        ((StartScreenController)loader.getController()).returner = new ScreenReturner() {
            @Override
            public void onScreenReturn(String message) throws Exception {
                startScreenReturn(message);
            }
        };

        window.setScene(new Scene(root, 1225, 710));
        window.show();
    }

    /**
     *
     * @param message
     * @throws Exception
     */
    private void startScreenReturn(String message) throws Exception {
        if (message.equals("start")) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                    .getResource("hackme/screens/GameScreenView.fxml"));
            Parent root = loader.load();

            // Deal with screen returning control back
            ((GameScreenController)loader.getController()).returner = new ScreenReturner() {
                @Override
                public void onScreenReturn(String message) throws Exception {
                    gameScreenReturn(message);
                }
            };

            window.setScene(new Scene(root, 1225, 710));
        } else if (message.equals("exit")) Platform.exit();
    }

    /**
     *
     * @param message
     * @throws Exception
     */
    private void gameScreenReturn(String message) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
//                .getResource("hackme/screens/EndScreenView.fxml"));
//        Parent root = loader.load();
//
//        // Deal with screen returning control back
//        ((EndScreenController)loader.getController()).returner = new ScreenReturn() {
//            @Override
//            public void onScreenReturn(String message) throws Exception {
//                endScreenReturn(message);
//            }
//        };
//
//        window.setScene(new Scene(root, 1225, 710));
    }

    /**
     *
     * @param message
     * @throws Exception
     */
    private void endScreenReturn(String message) throws Exception {
        if (message.equals("restart"))
            startScreenReturn("start");
        else if (message.equals(("exit")))
            Platform.exit();
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}