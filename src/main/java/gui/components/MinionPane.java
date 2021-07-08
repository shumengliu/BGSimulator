package gui.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.Board;
import model.Minion;
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
            board.getMinionByPosition(position).setHP(newHP);
        });
    }

    @FXML
    public void createMinion(ActionEvent event) {
        board.setMinionInPosition(new Minion(), position);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
