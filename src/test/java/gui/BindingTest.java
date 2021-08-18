package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Board;
import model.MinionBase;
import model.MinionOnBoard;
import model.Position;
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

@ExtendWith(ApplicationExtension.class)
public class BindingTest {
    private Board board;

    private MainWindowController controller;

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
    void setUp() throws Exception {
        board = new Board();
        controller.getSimulator().setBoard(board);
    }

    @Test
    public void createMinionShouldUpdateDisplay(FxRobot robot) {
        robot.clickOn("#DRAGONSPAWN_LIEUTENANTToggle");
        robot.clickOn("#createButton");
        FxAssert.verifyThat("#minionPaneA1 #nameLabel", LabeledMatchers.hasText("Dragonspawn Lieutenant"));
        FxAssert.verifyThat("#minionPaneA1 #atkField", TextInputControlMatchers.hasText("2"));
        FxAssert.verifyThat("#minionPaneA1 #healthField", TextInputControlMatchers.hasText("3"));
        assertEquals("Dragonspawn Lieutenant", controller.minionPaneA1.getMinion().getName());
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

    private void createAllycatToA1(FxRobot robot) {
        robot.clickOn("#ALLEYCATToggle");
        robot.clickOn("#createButton");
    }
}
