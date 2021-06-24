package gui;

import gui.components.MinionPane;
import javafx.fxml.FXML;
import model.Position;
import model.Simulator;


public class MainWindowController {
    private Simulator simulator;

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

    private void initializeMinionPanes() {
        minionPaneA1.setSimulator(simulator);
        minionPaneA2.setSimulator(simulator);
        minionPaneA3.setSimulator(simulator);
        minionPaneA4.setSimulator(simulator);
        minionPaneA5.setSimulator(simulator);
        minionPaneA6.setSimulator(simulator);
        minionPaneA7.setSimulator(simulator);
        minionPaneB1.setSimulator(simulator);
        minionPaneB2.setSimulator(simulator);
        minionPaneB3.setSimulator(simulator);
        minionPaneB4.setSimulator(simulator);
        minionPaneB5.setSimulator(simulator);
        minionPaneB6.setSimulator(simulator);
        minionPaneB7.setSimulator(simulator);
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
}
