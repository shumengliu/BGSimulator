package gui.components;

import javafx.beans.binding.ObjectBinding;
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

//    ObjectBinding<MinionOnBoard> minionBinding = new ObjectBinding<>() {
//        {
//            super.bind(view.itemsComboBox.valueProperty());
//        }
//        @Override
//        protected Item computeValue() {
//            Item item;
//            if (view.itemsComboBox.getSelectionModel().getSelectedIndex() >= 0)
//                item = view.itemsComboBox.getSelectionModel().selectedItemProperty().get();
//            else item = new Item();
//            view.quantitySlider.setValue(0);
//            return item;
//        }
//    };

    public MinionPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MinionPane.fxml"));
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
            board.getMinionByPosition(position).setHp(newHP);
        });
    }

    public void updateDisplay(MinionInBattle minion) {
        nameLabel.setText("Dragonspawn Lieutenant");
        atkField.setText("2");
        healthField.setText("3");
    }

    @FXML
    public void createMinion(ActionEvent event) {
        board.setMinionInPosition(new MinionOnBoard(), position);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
