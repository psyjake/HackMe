package hackme;

import hackme.gamelogic.GameEngine;
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
        window.setTitle("HackMe Game");

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
     * Handles the start screen returning control back
     * @param message A message passed by the caller
     * @throws Exception
     */
    private void startScreenReturn(String message) throws Exception {
        if (message.contains("Start")) {
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

            // Initialise game screen with selected difficulty
            GameEngine.Difficulty difficulty = GameEngine.Difficulty.EASY;
            switch (message) {
                case "startEasy": difficulty = GameEngine.Difficulty.EASY; break;
                case "startMedium": difficulty = GameEngine.Difficulty.MODERATE; break;
                case "startHard": difficulty = GameEngine.Difficulty.HARD; break;
            }

            ((GameScreenController)loader.getController()).initialiseGame(difficulty);
            window.setScene(new Scene(root, 1225, 710));
        } else if (message.equals("exit")) Platform.exit();
    }

    /**
     * Handles the game screen returning control back
     * @param message A message passed by the caller
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
     * Handles the end screen returning control back
     * @param message A message passed by the caller
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