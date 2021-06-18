package model;

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
    private final EnumMap<Position, Minion> minions;

    public Board() {
        minions = new EnumMap<>(Position.class);
    }

    /**
     * Convert the board state to a string.
     * This does not show minion names.
     */
    public String toString() {
        StringBuilder bufferA = new StringBuilder("PlayerA: ");
        StringBuilder bufferB = new StringBuilder("PlayerB: ");
        for (Map.Entry<Position, Minion> entry : minions.entrySet()) {
            if (entry.getKey().getSide() == Side.A) {
                bufferA.append(entry.getValue().getAttack());
                bufferA.append("-");
                bufferA.append(entry.getValue().getHealth());
                bufferA.append("  ");
            } else {
                bufferB.append(entry.getValue().getAttack());
                bufferB.append("-");
                bufferB.append(entry.getValue().getHealth());
                bufferB.append("  ");
            }
        }
        return "Starting Position\n" + bufferA.toString() + '\n' + bufferB.toString();
    }

    public Minion getMinionByPosition(Position position) {
        return minions.get(position);
    }

    public EnumMap<Position, Minion> getMinions() {
        return minions;
    }

    public void addMinionToPosition(Minion minion, Position position) {
        minions.put(position, minion);
    }
}
