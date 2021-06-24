package gui;

import com.google.common.collect.ImmutableMap;
import gui.components.MinionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Minion;
import model.Position;
import model.Simulator;

import java.util.Map;

public class MainWindowController {
    private Simulator simulator;

    private Map<MinionPane, Position> panePositionMap;
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
    public MinionPane minionPaneA1;
    @FXML
    public MinionPane minionPaneA2;
    @FXML
    public MinionPane minionPaneA3;
    @FXML
    public MinionPane minionPaneA4;
    @FXML
    public MinionPane minionPaneA5;
    @FXML
    public MinionPane minionPaneA6;
    @FXML
    public MinionPane minionPaneA7;
    @FXML
    public MinionPane minionPaneB1;
    @FXML
    public MinionPane minionPaneB2;
    @FXML
    public MinionPane minionPaneB3;
    @FXML
    public MinionPane minionPaneB4;
    @FXML
    public MinionPane minionPaneB5;
    @FXML
    public MinionPane minionPaneB6;
    @FXML
    public MinionPane minionPaneB7;

    @FXML
    public void initialize() {
        simulator = new Simulator();
        initializeBorderPanePositionMap();
    }

    private void initializeBorderPanePositionMap() {
        panePositionMap = new ImmutableMap.Builder<MinionPane, Position>()
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
        MinionPane minionPane = (MinionPane) button.getParent();
        Position position = getPositionByMinionPane(minionPane);
        simulator.addMinionToBoard(new Minion(), position);
    }

    private Position getPositionByMinionPane(MinionPane pane) {
        return panePositionMap.get(pane);
    }
//    private BorderPane getParentBorderPaneFromCreationButton(Button button) {
//        BorderPane borderPane = (BorderPane) button.getParent();
//        Label minionName = (Label) borderPane.getCenter();
//        minionName.setText("Here is a minion.");
//    }
}
