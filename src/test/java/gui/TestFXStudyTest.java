package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Simulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

@ExtendWith(ApplicationExtension.class)
public class TestFXStudyTest {


    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(MainApplication.class.getResource("/main-window.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void locateButtonByFxId(FxRobot robot) {
        FxAssert.verifyThat("#simulateButton", LabeledMatchers.hasText("Simulate"));
    }

    @Test
    public void clickButtonUsingFxRobot(FxRobot robot) {
        robot.clickOn("#Simulate");

        FxAssert.verifyThat("#simulateButton", LabeledMatchers.hasText("Simulate"));
    }
}
