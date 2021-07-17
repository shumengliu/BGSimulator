package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Position;
import model.Simulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
public class MainWindowTest {

    private Simulator simulator;

    MainWindowController controller;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/main-window.fxml"));
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();

        controller = loader.getController();
    }

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void clickingOnSimulateButtonRunsSimulationOnce(FxRobot robot) {
        // inject mocked simulator
        simulator = mock(Simulator.class);
        controller.setSimulator(simulator);

        robot.clickOn("#oneSimButton");
        verify(simulator, times(1)).simulateOnce();
    }

    @Test
    public void runSimulation1000TimesByClickingTheMultiSimulButton(FxRobot robot) {
        // inject mocked simulator
        simulator = mock(Simulator.class);
        controller.setSimulator(simulator);

        robot.clickOn("#multiSimButton");
        verify(simulator, times(1)).simulate(1000);
    }

    @Test
    public void runSimulation500TimesByChangingTheSimulField(FxRobot robot) {
        // inject mocked simulator
        simulator = mock(Simulator.class);
        controller.setSimulator(simulator);

        robot.doubleClickOn("#numberOfSimField");
        robot.write("500");
        robot.clickOn("#multiSimButton");
        verify(simulator, times(1)).simulate(500);
    }

    @Test
    public void modifyingAttackFieldChangesMinionAttack(FxRobot robot) {
        robot.clickOn("#minionPaneA1 #createMinionButton");
        robot.doubleClickOn("#minionPaneA1 #atkField");
        robot.write("42");
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("42"));
        assertEquals(42, controller.getSimulator().getBoard().getMinionByPosition(Position.A1).getAttack());
    }

    @Test
    public void modifyingHealthFieldChangesMinionHP(FxRobot robot) {
        robot.clickOn("#minionPaneA1 #createMinionButton");
        robot.doubleClickOn("#minionPaneA1 #");
        robot.write("42");
        FxAssert.verifyThat("#minionPaneA1 #healthField", TextInputControlMatchers.hasText("42"));
        assertEquals(42, controller.getSimulator().getBoard().getMinionByPosition(Position.A1).getHealth());
    }

    @Test
    public void enteringNonNumericCharactersToAttackFieldShouldFail(FxRobot robot) {
        robot.clickOn("#minionPaneA1 #createMinionButton");
        robot.doubleClickOn("#minionPaneA1 #atkField");
        robot.write("abc");
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("0"));
    }

    @Test
    public void shouldDisplayResultAfterRunningMultiSim(FxRobot robot) {
        robot.clickOn("#multiSimButton");
        FxAssert.verifyThat("#resultPane #winRateA", LabeledMatchers.hasText("0.0%"));
        FxAssert.verifyThat("#resultPane #drawRate", LabeledMatchers.hasText("100.0%"));
        FxAssert.verifyThat("#resultPane #winRateB", LabeledMatchers.hasText("0.0%"));
    }
}