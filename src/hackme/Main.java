package hackme;

import hackme.gamelogic.GameEngine;
import hackme.screens.EndScreenController;
import hackme.screens.GameScreenController;
import hackme.screens.ScreenReturner;
import hackme.screens.StartScreenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Start the application and handle the creation and
 * display of all scenes and of all returner
 * implementations for each controller
 */
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
        loadStartScreen();
        window.show();
    }

    /**
     * Displays the start screen
     */
    private void loadStartScreen() throws IOException {
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

        window.setScene(new Scene(root, 965, 540));
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
            GameEngine.Difficulty difficulty = GameEngine.Difficulty.HARD;
            switch (message) {
                case "easyStart": difficulty = GameEngine.Difficulty.EASY; break;
                case "mediumStart": difficulty = GameEngine.Difficulty.MODERATE; break;
                case "hardStart": difficulty = GameEngine.Difficulty.HARD; break;
            }

            ((GameScreenController)loader.getController()).initialiseGame(difficulty);
            window.setScene(new Scene(root, 965, 540));
        } else if (message.equals("exit")) Platform.exit();
    }

    /**
     * Handles the game screen returning control back
     * @param message A message passed by the caller
     * @throws Exception
     */
    private void gameScreenReturn(String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("hackme/screens/EndScreenView.fxml"));
        Parent root = loader.load();

        // Deal with screen returning control back
        ((EndScreenController)loader.getController()).returner = new ScreenReturner() {
            @Override
            public void onScreenReturn(String message) throws Exception {
                endScreenReturn(message);
            }
        };

        ((EndScreenController)loader.getController()).setResult(message);
        window.setScene(new Scene(root, 965, 540));
    }

    /**
     * Handles the end screen returning control back
     * @param message A message passed by the caller
     * @throws Exception
     */
    private void endScreenReturn(String message) throws Exception {
        if (message.equals("restart"))
            loadStartScreen();
        else if (message.equals(("exit")))
            Platform.exit();
    }


    /**
     * launch the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}