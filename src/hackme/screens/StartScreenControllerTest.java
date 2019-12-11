package hackme.screens;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartScreenControllerTest {

    private StartScreenController startScreenController;
    @BeforeEach
    void setUp() {
        startScreenController = new StartScreenController();
    }

    @AfterEach
    void tearDown() {
        startScreenController = null;
    }

    @Test
    void testInitialize() {
        try {
            startScreenController.initialize(null, null);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void testEasyLevelButtonClicked() {
        try {
            startScreenController.easyLevelButtonClicked(new ActionEvent());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testMediumLevelButtonClicked() {
        try {
            startScreenController.mediumLevelButtonClicked(new ActionEvent());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testhardLevelButtonClicked() {
        try {
            startScreenController.hardLevelButtonClicked(new ActionEvent());
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    void testExitButtonClicked() {
        try {
            startScreenController.exitButtonClicked(new ActionEvent());
        } catch (Exception e) {
            fail();
        }
    }
}