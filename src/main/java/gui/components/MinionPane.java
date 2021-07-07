package gui.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import model.Minion;
import model.Position;
import model.Simulator;

import java.io.IOException;

public class MinionPane extends BorderPane {
    private Simulator simulator;
    private Position position;
    private Minion minion;
    @FXML
    private TextField atkField;
    @FXML
    private TextField healthField;
    @FXML
    private Button createMinionButton;

    public MinionPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/minion-pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        addEventListeners();
    }

    private void addEventListeners() {
        setTextFormatters();
        setModifyAttackListener();
        setModifyHealthListener();
    }

    private void setTextFormatters() {
        setTextFormatter(atkField);
        setTextFormatter(healthField);
    }

    private void setTextFormatter(TextField field) {
        field.setTextFormatter(new TextFormatter<>(c -> {
            if (c.isContentChange()) {
                if (c.getControlNewText().length() == 0) {
                    return c;
                }
                try {
                    Integer.parseInt(c.getControlNewText());
                    return c;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return c;
        }));
    }

    private void setModifyAttackListener() {
        atkField.textProperty().addListener((observable, oldValue, newValue) -> {
            int newAttack = Integer.parseInt(newValue);
            minion.setAttack(newAttack);
            updateMinionInBoard();
        });
    }

    private void setModifyHealthListener() {
        healthField.textProperty().addListener((observable, oldValue, newValue) -> {
            int newHP = Integer.parseInt(newValue);
            minion.setHP(newHP);
            updateMinionInBoard();
        });
    }


    @FXML
    public void createMinion(ActionEvent event) {
        minion = new Minion();
        updateMinionInBoard();
    }

    private void updateMinionInBoard() {
        simulator.addMinionToBoard(minion, position);
        simulator.simulateOnce(); // todo delete this when test is completed
    }

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
