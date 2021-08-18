package gui;

import gui.components.IntField;
import gui.components.MinionPane;
import gui.components.MinionToggle;
import gui.components.SimResultPane;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import model.*;

import java.util.EnumMap;
import java.util.Map;


public class MainWindowController {
    private Simulator simulator;

    private Board board;
    // minion selection
    private ToggleGroup minionGroup;
    @FXML
    private ComboBox<Position> positionBox;
    @FXML
    private VBox minionBox;

    // simulation related
    @FXML
    private Button multiSimButton;
    @FXML
    private IntField numberOfSimField;
    @FXML
    private SimResultPane simResultPane;

    public static Map<Position, MinionPane> minionPaneMap;
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
        board = new Board();
        simulator.setBoard(board);
        initializeMinionPanes();
        initializePositionBox();
        initializeMinionCreationButtons();
    }

    private void initializeMinionPanes() {
        minionPaneMap = new EnumMap<>(Position.class);
        minionPaneMap.put(Position.A1, minionPaneA1);
        minionPaneMap.put(Position.A2, minionPaneA2);
        minionPaneMap.put(Position.A3, minionPaneA3);
        minionPaneMap.put(Position.A4, minionPaneA4);
        minionPaneMap.put(Position.A5, minionPaneA5);
        minionPaneMap.put(Position.A6, minionPaneA6);
        minionPaneMap.put(Position.A7, minionPaneA7);
        minionPaneMap.put(Position.B1, minionPaneB1);
        minionPaneMap.put(Position.B2, minionPaneB2);
        minionPaneMap.put(Position.B3, minionPaneB3);
        minionPaneMap.put(Position.B4, minionPaneB4);
        minionPaneMap.put(Position.B5, minionPaneB5);
        minionPaneMap.put(Position.B6, minionPaneB6);
        minionPaneMap.put(Position.B7, minionPaneB7);
        for (Map.Entry<Position, MinionPane> entry : minionPaneMap.entrySet()) {
            entry.getValue().setPosition(entry.getKey());
        }
        for (MinionPane pane : minionPaneMap.values()) {
            pane.setBoard(board);
        }
//        board.getObservableMinions().addListener((MapChangeListener<Position, MinionOnBoard>) change -> {
//            System.out.println(change.getKey());
//            Position position = change.getKey();
//            System.out.println(change.getValueAdded().getName());
////            minionPaneMap.get(position).setMinion(change.getValueAdded());
//        });
//        minionPaneA1.setPosition(Position.A1);
//        minionPaneA2.setPosition(Position.A2);
//        minionPaneA3.setPosition(Position.A3);
//        minionPaneA4.setPosition(Position.A4);
//        minionPaneA5.setPosition(Position.A5);
//        minionPaneA6.setPosition(Position.A6);
//        minionPaneA7.setPosition(Position.A7);
//        minionPaneB1.setPosition(Position.B1);
//        minionPaneB2.setPosition(Position.B2);
//        minionPaneB3.setPosition(Position.B3);
//        minionPaneB4.setPosition(Position.B4);
//        minionPaneB5.setPosition(Position.B5);
//        minionPaneB6.setPosition(Position.B6);
//        minionPaneB7.setPosition(Position.B7);
//        minionPaneA1.setBoard(board);
//        minionPaneA2.setBoard(board);
//        minionPaneA3.setBoard(board);
//        minionPaneA4.setBoard(board);
//        minionPaneA5.setBoard(board);
//        minionPaneA6.setBoard(board);
//        minionPaneA7.setBoard(board);
//        minionPaneB1.setBoard(board);
//        minionPaneB2.setBoard(board);
//        minionPaneB3.setBoard(board);
//        minionPaneB4.setBoard(board);
//        minionPaneB5.setBoard(board);
//        minionPaneB6.setBoard(board);
//        minionPaneB7.setBoard(board);

    }

    private void initializePositionBox() {
        for (Position position : Position.values()) {
            positionBox.getItems().add(position);
        }
        positionBox.getSelectionModel().select(Position.A1);
    }

    private void initializeMinionCreationButtons() {
        minionGroup = new ToggleGroup();
        for (MinionBase minion : MinionBase.values()) {
            MinionToggle button = new MinionToggle(minion.getName());
            button.setMinionBase(minion);
            button.setToggleGroup(minionGroup);
            button.setMaxWidth(Integer.MAX_VALUE);
            minionBox.getChildren().add(button);
        }
    }

    @FXML
    public void runSimulationOnce(ActionEvent event) {
        simulator.simulateOnce();
    }

    @FXML
    public void runMultiSimul(ActionEvent event) {
        int numberOfSim = Integer.parseInt(numberOfSimField.getText());
        SimulationResult result = simulator.simulate(numberOfSim);
        simResultPane.setResult(result);
    }

    @FXML
    public void createMinion(ActionEvent event) {
        MinionOnBoard minion = new MinionOnBoard(getToggledMinionBase());
        Position position = getSelectedPosition();
        board.setMinionToPosition(minion, position);
        minionPaneA1.updateDisplay(new MinionInBattle());
    }

    private MinionBase getToggledMinionBase() {
        MinionToggle selectedToggle = (MinionToggle) minionGroup.getSelectedToggle();
        return selectedToggle.getMinionBase();
    }

    private Position getSelectedPosition() {
        return positionBox.getSelectionModel().getSelectedItem();
    }

    // getters and setters for testing

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
