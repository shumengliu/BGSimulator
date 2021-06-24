package gui.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Minion;
import model.Position;
import model.Simulator;

import java.io.IOException;

public class MinionPane extends BorderPane {
    private Simulator simulator;
    private Position position;
    @FXML
    private TextField atkField;
    @FXML
    private TextField healthField;

    public MinionPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/minion-pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        atkField.setText("10");
    }

    @FXML
    public void createMinion(ActionEvent event) {
        simulator.addMinionToBoard(new Minion(), position);
        simulator.simulateOnce();
    }


    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
