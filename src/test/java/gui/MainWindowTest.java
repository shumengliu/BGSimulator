package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Simulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
public class MainWindowTest {

    private Simulator simulator;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/main-window.fxml"));
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();

        // inject mocked simulator
        MainWindowController controller = loader.getController();
        simulator = mock(Simulator.class);
        controller.setSimulator(simulator);
    }

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void clickOnSimulateButtonRunsSimulationOnce(FxRobot robot) {
        robot.clickOn("#simulateButton");
        verify(simulator, times(1)).simulateOnce();
    }

}