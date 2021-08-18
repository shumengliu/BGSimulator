package gui.components;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Board;
import model.MinionInBattle;
import model.MinionOnBoard;
import model.Position;

import java.io.IOException;

public class MinionPane extends BorderPane {
    private Board board;
    private Position position;

    @FXML
    private IntField atkField;
    @FXML
    private IntField healthField;
    @FXML
    private Label nameLabel;
    // remove this later
    @FXML
    private Button createMinionButton;

    private MinionOnBoard minion;

    public MinionPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MinionPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

//        addEventListeners();
    }

    private void addEventListeners() {
        setModifyAttackListener();
        setModifyHealthListener();
    }

    private void setModifyAttackListener() {
        atkField.textProperty().addListener((observable, oldValue, newValue) -> {
            int newAttack = Integer.parseInt(newValue);
            board.getMinionByPosition(position).setAttack(newAttack);
        });
    }

    private void setModifyHealthListener() {
        healthField.textProperty().addListener((observable, oldValue, newValue) -> {
            int newHP = Integer.parseInt(newValue);
            board.getMinionByPosition(position).setHp(newHP);
        });
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public MinionOnBoard getMinion() {
        return minion;
    }

    public void setMinion(MinionOnBoard newMinion) {
        minion = newMinion;
        createBindings();
    }

    private void createBindings() {
        nameLabel.textProperty().bind(minion.nameProperty());
        atkField.textProperty().bind(Bindings.format("%d", minion.attackProperty()));
        healthField.textProperty().bind(Bindings.format("%d", minion.hpProperty()));
    }

    @FXML
    public void createMinion(ActionEvent event) {
        board.setMinionToPosition(new MinionOnBoard(), position);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Board getBoard() {
        return board;
    }
}
