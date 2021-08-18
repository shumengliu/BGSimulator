package model;

import gui.MainWindowController;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.EnumMap;
import java.util.Map;

/**
 * This is a class simulates the battleground.
 * This class only cares about minions' existence on the battlegrounds but not the battle phase.
 * For battle phase, please see BattleRunner.java.
 *
 * @author HonestBook
 */
public class Board {
    private final ObservableMap<Position, MinionOnBoard> minions;

    public Board() {
        minions = FXCollections.observableMap(new EnumMap<>(Position.class));
        addMinionChangeListener();
    }

    private void addMinionChangeListener() {
        minions.addListener((MapChangeListener<Position, MinionOnBoard>) change -> {
            System.out.println(change.getKey());
            Position position = change.getKey();
            System.out.println(change.getValueAdded().getName());
            MainWindowController.minionPaneMap.get(position).setMinion(change.getValueAdded());
        });
    }

    /**
     * Convert the board state to a string.
     * This does not show minion names.
     */
    public String toString() {
        StringBuilder bufferA = new StringBuilder("PlayerA: ");
        StringBuilder bufferB = new StringBuilder("PlayerB: ");
        for (Map.Entry<Position, MinionOnBoard> entry : minions.entrySet()) {
            if (entry.getKey().getSide() == Side.A) {
                bufferA.append(entry.getValue().getAttack());
                bufferA.append("-");
                bufferA.append(entry.getValue().getHp());
                bufferA.append("  ");
            } else {
                bufferB.append(entry.getValue().getAttack());
                bufferB.append("-");
                bufferB.append(entry.getValue().getHp());
                bufferB.append("  ");
            }
        }
        return "Starting Position\n" + bufferA.toString() + '\n' + bufferB.toString();
    }

    public MinionOnBoard getMinionByPosition(Position position) {
        return minions.get(position);
    }

    public Map<Position, MinionOnBoard> getMinions() {
        return minions;
    }

    public ObservableMap<Position, MinionOnBoard> getObservableMinions() {
        return minions;
    }

    public void setMinionToPosition(MinionOnBoard minion, Position position) {
        minions.put(position, minion);
    }
}
