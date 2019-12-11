package hackme.screens;

import hackme.gamelogic.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameScreenControllerTest {

    private GameScreenController gameScreenController;
    @BeforeEach
    void setUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("hackme/screens/StartScreenView.fxml"));
        try {
            Parent root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        gameScreenController = null;
    }

    @Test
    void testInitialize() {
       try {
           gameScreenController.initialize(null, null);
       } catch (Exception e) {
           fail();
       }
    }

    @Test
    void testWordOnClick() {
        try {
            gameScreenController.wordOnClick("word");
            gameScreenController.wordOnClick(null);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void textInitialiseGame() {
        try {
            gameScreenController.initialiseGame(GameEngine.Difficulty.HARD);
        } catch (Exception e) {
            fail();
        }
    }
}