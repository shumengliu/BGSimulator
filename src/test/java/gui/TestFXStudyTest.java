package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

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
        robot.clickOn("#simulateButton");

        FxAssert.verifyThat("#simulateButton", LabeledMatchers.hasText("Simulate"));
    }

    @Test
    public void writeToTextField(FxRobot robot) {
        robot.clickOn("#minionPaneA1 #createMinionButton");
        robot.doubleClickOn("#minionPaneA1 #atkField");
        robot.write("42");
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("42"));
    }
}
