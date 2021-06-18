package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * This is a class simulates the battleground. The main function of the
 * battleground is to implement the battle phase of the game.
 *
 * @author HonestBook
 */
public class Board {
    private EnumMap<Position, Minion> minions;
    private ArrayList<Minion> minionsB;

    private BattleQueue queueA;
    private BattleQueue queueB;

    Random random = new Random();

    /**
     * Constructor of the Battleground class.
     */
    public Board() {
        minions = new EnumMap<>(Position.class);
    }

    // todo BattleRunner.java
    private void initializeBattleQueues() {
        queueA = new BattleQueue();
        queueB = new BattleQueue();
        addMinionsToBattleQueue();
    }

    // todo BattleRunner.java
    private void addMinionsToBattleQueue() {
        for (Position position : minions.keySet()) {
            BattleQueue bq = getBattleQueueByPosition(position);
            bq.addMinion(minions.get(position));
        }
    }

    // todo BattleRunner.java
    private BattleQueue getBattleQueueByPosition(Position position) {
        if (position.getSide() == Side.A) {
            return queueA;
        } else {
            return queueB;
        }
    }

    // todo BattleRunner.java
    public void battlePhase() {
        initializeBattleQueues();
        // A flag indicating whose turn to attack.
        // True if this is player A's turn to attack.
        boolean turn = true;
        // There is a fifty percent chance that player B starts first.
        if (random.nextInt(100) < 50) {
            turn = false;
        }

        // Each iteration of this loop represents one attack.
        while (battleViable()) {
            // Select attacker and defender
            Minion attacker = getNextAttackerByTurn(turn);
            Minion defender = getNextDefenderByTurn(turn);

            removeDeadMinions(attacker, defender, turn);
            attack(attacker, defender);
            // Change attacking player.
            turn = !turn;
        }
    }

    /**
     * Print the position at the start of combat.
     */
    public void printBoard() {
        System.out.println("Starting Position");
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
        System.out.println(bufferA.toString());
        System.out.println(bufferB.toString());
    }

    // todo BattleRunner.java
    public void printBattleResult() {
        StringBuilder buffer = new StringBuilder("Result: ");
        if (queueA.hasLivingMinion()) {
            int damage = queueA.getTierSum();
            buffer.append("PlayerA wins! ");
            buffer.append("PlayerB loses ");
            buffer.append(damage);
            buffer.append(" HP.");
        } else if (queueB.hasLivingMinion()) {
            int damage = queueB.getTierSum();
            buffer.append("PlayerB wins! ");
            buffer.append("PlayerA loses ");
            buffer.append(damage);
            buffer.append(" HP.");
        } else {
            buffer.append("Draw!");
        }
        System.out.println(buffer.toString());
    }

    // todo BattleRunner.java
    /**
     * Given the turn, return the next minion to attack from the battle queue.
     *
     * @param turn True if it is playerA's turn False otherwise
     * @return the next minion to attack
     */
    private Minion getNextAttackerByTurn(boolean turn) {
        return turn ? queueA.getNextAttacker() : queueB.getNextAttacker();
    }

    // todo BattleRunner.java
    /**
     * Given the turn, return the next minion to be attacked from the battle queue.
     *
     * @param turn True if it is playerA's turn False otherwise
     * @return the next minion to attack
     */
    private Minion getNextDefenderByTurn(boolean turn) {
        return turn ? queueB.getNextDefender() : queueA.getNextDefender();
    }

    // todo BattleRunner.java
    /**
     * Remove any dead minion as the consequence of an attack.
     *
     * @param turn True if it is playerA's turn False otherwise
     */
    private void removeDeadMinions(Minion attacker, Minion defender, boolean turn) {
        if (turn) {
            queueA.removeIfDead(attacker);
            queueB.removeIfDead(defender);
        } else {
            queueA.removeIfDead(defender);
            queueB.removeIfDead(attacker);
        }
    }


    // todo BattleRunner.java
    /**
     * One attack in a battle. Both minions involved loses HP equivalent to the
     * other's attack.
     *
     * @param attacker the attacking minion
     * @param defender the attacked minion
     */
    public void attack(Minion attacker, Minion defender) {
        assert attacker.isAlive();
        assert defender.isAlive();
        attacker.loseHP(defender.getAttack());
        defender.loseHP(attacker.getAttack());
        System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
    }

    public void addMinionToPosition(Minion minion, Position position) {
        minions.put(position, minion);
    }

    /**
     * Return if the battle will continue. The battle continues when both players
     * have at least one minion alive.
     *
     * @return True if the battle is viable.
     */
    public boolean battleViable() {
        // Check if there are alive minions in both lists.
        return (queueA.hasLivingMinion() && queueB.hasLivingMinion());
    }
}
