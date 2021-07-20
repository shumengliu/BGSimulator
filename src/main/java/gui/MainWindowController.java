package gui;

import gui.components.IntField;
import gui.components.MinionPane;
import gui.components.SimResultPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import model.Board;
import model.Position;
import model.SimulationResult;
import model.Simulator;


public class MainWindowController {
    @FXML
    public ToggleGroup Group1;

    private Simulator simulator;

    @FXML
    public Button oneSimButton;
    @FXML
    public Button multiSimButton;
    @FXML
    public IntField numberOfSimField;
    @FXML
    public SimResultPane simResultPane;

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
        initializeMinionPanes();
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

    private void initializeMinionPanes() {
        Board board = simulator.getBoard();
        minionPaneA1.setBoard(board);
        minionPaneA2.setBoard(board);
        minionPaneA3.setBoard(board);
        minionPaneA4.setBoard(board);
        minionPaneA5.setBoard(board);
        minionPaneA6.setBoard(board);
        minionPaneA7.setBoard(board);
        minionPaneB1.setBoard(board);
        minionPaneB2.setBoard(board);
        minionPaneB3.setBoard(board);
        minionPaneB4.setBoard(board);
        minionPaneB5.setBoard(board);
        minionPaneB6.setBoard(board);
        minionPaneB7.setBoard(board);
        minionPaneA1.setPosition(Position.A1);
        minionPaneA2.setPosition(Position.A2);
        minionPaneA3.setPosition(Position.A3);
        minionPaneA4.setPosition(Position.A4);
        minionPaneA5.setPosition(Position.A5);
        minionPaneA6.setPosition(Position.A6);
        minionPaneA7.setPosition(Position.A7);
        minionPaneB1.setPosition(Position.B1);
        minionPaneB2.setPosition(Position.B2);
        minionPaneB3.setPosition(Position.B3);
        minionPaneB4.setPosition(Position.B4);
        minionPaneB5.setPosition(Position.B5);
        minionPaneB6.setPosition(Position.B6);
        minionPaneB7.setPosition(Position.B7);
    }

    // getters and setters for testing

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public Simulator getSimulator() {
        return simulator;
    }
}
