package gui;

import com.google.common.collect.ImmutableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Minion;
import model.Position;
import model.Simulator;

import java.util.Map;

public class MainWindowController {
    private Simulator simulator;

    private Map<BorderPane, Position> panePositionMap;
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
    public BorderPane minionPaneA1;
    @FXML
    public BorderPane minionPaneA2;
    @FXML
    public BorderPane minionPaneA3;
    @FXML
    public BorderPane minionPaneA4;
    @FXML
    public BorderPane minionPaneA5;
    @FXML
    public BorderPane minionPaneA6;
    @FXML
    public BorderPane minionPaneA7;
    @FXML
    public BorderPane minionPaneB1;
    @FXML
    public BorderPane minionPaneB2;
    @FXML
    public BorderPane minionPaneB3;
    @FXML
    public BorderPane minionPaneB4;
    @FXML
    public BorderPane minionPaneB5;
    @FXML
    public BorderPane minionPaneB6;
    @FXML
    public BorderPane minionPaneB7;

    @FXML
    public void initialize() {
        simulator = new Simulator();
        initializeBorderPanePositionMap();
    }

    private void initializeBorderPanePositionMap() {
        panePositionMap = new ImmutableMap.Builder<BorderPane, Position>()
                .put(minionPaneA1, Position.A1)
                .put(minionPaneA2, Position.A2)
                .put(minionPaneA3, Position.A3)
                .put(minionPaneA4, Position.A4)
                .put(minionPaneA5, Position.A5)
                .put(minionPaneA6, Position.A6)
                .put(minionPaneA7, Position.A7)
                .put(minionPaneB1, Position.B1)
                .put(minionPaneB2, Position.B2)
                .put(minionPaneB3, Position.B3)
                .put(minionPaneB4, Position.B4)
                .put(minionPaneB5, Position.B5)
                .put(minionPaneB6, Position.B6)
                .put(minionPaneB7, Position.B7)
                .build();
    }

    @FXML
    public void createMinion(ActionEvent event) {
        Button button = (Button) event.getSource();
        BorderPane borderPane = (BorderPane) button.getParent();
        Position position = getPositionByBorderPane(borderPane);
        simulator.addMinionToBoard(new Minion(), position);
    }

    private Position getPositionByBorderPane(BorderPane pane) {
        return panePositionMap.get(pane);
    }
//    private BorderPane getParentBorderPaneFromCreationButton(Button button) {
//        BorderPane borderPane = (BorderPane) button.getParent();
//        Label minionName = (Label) borderPane.getCenter();
//        minionName.setText("Here is a minion.");
//    }
}
