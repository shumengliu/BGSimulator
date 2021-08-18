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
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

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
    public void addMinionToBoardUpdatesMinionBindingInMinionPane() {
        MinionOnBoard minion = new MinionOnBoard(MinionBase.ALLEYCAT);
        board.setMinionToPosition(minion, Position.A1);
        assertEquals(minion, controller.minionPaneA1.getMinion());
    }
}
