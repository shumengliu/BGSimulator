package gui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.SimulationResult;

import java.io.IOException;

public class SimResultPane extends GridPane {
    @FXML
    Label winRateA;
    @FXML
    Label drawRate;
    @FXML
    Label winRateB;

    public SimResultPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sim-result-pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void setResult(SimulationResult result) {
        winRateA.setText(toPercentString(result.getWinRateForA()));
        drawRate.setText(toPercentString(result.getDrawRate()));
        winRateB.setText(toPercentString(result.getWinRateForB()));
    }

    private String toPercentString(double number) {
        return number * 100 + "%";
    }
}
