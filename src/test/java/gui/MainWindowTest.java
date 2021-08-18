package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Position;
import model.SimulationResult;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
public class MainWindowTest {

    private Simulator simulator;

    MainWindowController controller;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/MainWindow.fxml"));
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
    public void selectingAndCreatingMinionBaseShouldWork(FxRobot robot) {
        robot.clickOn("#ALLEYCATToggle");
        robot.clickOn("#createButton");
        assertEquals("Alleycat", controller.getBoard().getMinionByPosition(Position.A1).getName());
    }

    @Test
    public void createMinionToACertainPositionShouldWork(FxRobot robot) {
        // fixme after refactoring using properties and bindings
        fail();
        robot.clickOn("#ALLEYCATToggle");
        robot.clickOn("#positionBox");
        robot.clickOn("B2");
        robot.clickOn("#createButton");
        assertEquals("Alleycat", controller.getBoard().getMinionByPosition(Position.B2).getName());
    }

    @Test
    public void createMinionShouldUpdateDisplay(FxRobot robot) {
        robot.clickOn("#DRAGONSPAWN_LIEUTENANTToggle");
        robot.clickOn("#createButton");
        FxAssert.verifyThat("#minionPaneA1 #nameLabel", LabeledMatchers.hasText("Dragonspawn Lieutenant"));
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("2"));
        FxAssert.verifyThat("#minionPaneA1 #healthField", TextInputControlMatchers.hasText("3"));
    }

    @Test
    public void modifyingAttackFieldChangesMinionAttack(FxRobot robot) {
        createAllycatToA1(robot);
        robot.doubleClickOn("#minionPaneA1 #atkField");
        robot.write("42");
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("42"));
        assertEquals(42, controller.getBoard().getMinionByPosition(Position.A1).getAttack());
    }

    @Test
    public void modifyingHealthFieldChangesMinionHP(FxRobot robot) {
        createAllycatToA1(robot);
        robot.doubleClickOn("#minionPaneA1 #healthField");
        robot.write("42");
        FxAssert.verifyThat("#minionPaneA1 #healthField", TextInputControlMatchers.hasText("42"));
        assertEquals(42, controller.getBoard().getMinionByPosition(Position.A1).getHp());
    }

    @Test
    public void enteringNonNumericCharactersToAttackFieldShouldFail(FxRobot robot) {
        robot.clickOn("#minionPaneA1 #createMinionButton");
        robot.doubleClickOn("#minionPaneA1 #atkField");
        robot.write("abc");
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("0"));
    }

    @Test
    public void runSimulation1000TimesByClickingTheMultiSimulButton(FxRobot robot) {
        // inject mocked simulator
        simulator = mock(Simulator.class);
        when(simulator.simulate(anyInt())).thenReturn(new SimulationResult());
        controller.setSimulator(simulator);

        robot.clickOn("#multiSimButton");
        verify(simulator, times(1)).simulate(1000);
    }

    @Test
    public void runSimulation500TimesByChangingTheSimulField(FxRobot robot) {
        // inject mocked simulator
        simulator = mock(Simulator.class);
        when(simulator.simulate(anyInt())).thenReturn(new SimulationResult());
        controller.setSimulator(simulator);

        robot.doubleClickOn("#numberOfSimField");
        robot.write("500");
        robot.clickOn("#multiSimButton");
        verify(simulator, times(1)).simulate(500);
    }

    @Test
    public void shouldDisplayResultAfterRunningMultiSim(FxRobot robot) {
        robot.clickOn("#multiSimButton");
        FxAssert.verifyThat("#winRateA", LabeledMatchers.hasText("0.0%"));
        FxAssert.verifyThat("#drawRate", LabeledMatchers.hasText("100.0%"));
        FxAssert.verifyThat("#winRateB", LabeledMatchers.hasText("0.0%"));
    }

    private void createAllycatToA1(FxRobot robot) {
        robot.clickOn("#ALLEYCATToggle");
        robot.clickOn("#createButton");
    }
}