package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Simulator;

public class MainWindowController {
    private Simulator simulator;

    @FXML
    public Label minionNameA1;
    @FXML
    public Label minionNameA2;
    @FXML
    public Label minionNameA3;
    @FXML
    public Label minionNameA4;
    @FXML
    public Label minionNameA5;
    @FXML
    public Label minionNameA6;
    @FXML
    public Label minionNameA7;
    @FXML
    public Label minionNameB1;
    @FXML
    public Label minionNameB2;
    @FXML
    public Label minionNameB3;
    @FXML
    public Label minionNameB4;
    @FXML
    public Label minionNameB5;
    @FXML
    public Label minionNameB6;
    @FXML
    public Label minionNameB7;

    @FXML
    public Button createMinionButtonA1;
    @FXML
    public Button createMinionButtonA2;
    @FXML
    public Button createMinionButtonA3;
    @FXML
    public Button createMinionButtonA4;
    @FXML
    public Button createMinionButtonA5;
    @FXML
    public Button createMinionButtonA6;
    @FXML
    public Button createMinionButtonA7;
    @FXML
    public Button createMinionButtonB1;
    @FXML
    public Button createMinionButtonB2;
    @FXML
    public Button createMinionButtonB3;
    @FXML
    public Button createMinionButtonB4;
    @FXML
    public Button createMinionButtonB5;
    @FXML
    public Button createMinionButtonB6;
    @FXML
    public Button createMinionButtonB7;

    @FXML
    public void initialize() {
        simulator = new Simulator();
    }

    @FXML
    public void createMinion(ActionEvent event) {
        Button button = (Button) event.getSource();
        BorderPane borderPane = (BorderPane) button.getParent();
        Label minionName = (Label) borderPane.getCenter();
        minionName.setText("Here is a minion.");
    }
}
