package model;

import java.util.EnumMap;
import java.util.Random;

public class BattleRunner {
    private static final Random random = new Random();

    private final BattleQueue queueA;
    private final BattleQueue queueB;
    private Side nextToAttack; // indicating whose turn to attack.

    public BattleRunner() {
        queueA = new BattleQueue();
        queueB = new BattleQueue();
    }

    public void initializeQueuesFromBoard(Board board) {
        queueA.clearAllMinions();
        queueB.clearAllMinions();
        EnumMap<Position, Minion> minions = board.getMinions();
        initializeQueuesFromMinionsMap(minions);
    }

    private void initializeQueuesFromMinionsMap(EnumMap<Position, Minion> minions) {
        for (Position position : minions.keySet()) {
            BattleQueue bq = getBattleQueueByPosition(position);
            bq.addCloneOfMinion(minions.get(position));
        }
    }

    private BattleQueue getBattleQueueByPosition(Position position) {
        if (position.getSide() == Side.A) {
            return queueA;
        } else {
            return queueB;
        }
    }

    public void battlePhase() {
        decideInitiative();
        // Each iteration of this loop represents one attack.
        while (battleViable()) {
            executeNextAttack();
            alternateTurn();
        }
    }

    private void decideInitiative() {
        if (queueA.size() != queueB.size()) {
            decideInitiativeBySize();
        } else {
            decideInitiativeRandomly();
        }
    }

    private void decideInitiativeBySize() {
        if (queueA.size() > queueB.size()) {
            nextToAttack = Side.A;
        } else if ((queueA.size() < queueB.size())) {
            nextToAttack = Side.B;
        }
    }

    private void decideInitiativeRandomly() {
        if (random.nextInt(100) < 50) {
            nextToAttack = Side.A;
        } else {
            nextToAttack = Side.B;
        }
    }

    /**
     * Return if the battle will continue. The battle continues when both players
     * have at least one minion alive.
     *
     * @return True if the battle is viable.
     */
    private boolean battleViable() {
        // Check if there are alive minions in both lists.
        return (queueA.hasLivingMinion() && queueB.hasLivingMinion());
    }

    private void executeNextAttack() {
        Minion attacker = getNextAttacker();
        Minion defender = getNextDefender();

        minionsLoseHPFromAttack(attacker, defender);
        removeDeadMinions(attacker, defender);
    }

    private Minion getNextAttacker() {
        return nextToAttack == Side.A ? queueA.getNextAttacker() : queueB.getNextAttacker();
    }

    private Minion getNextDefender() {
        return nextToAttack == Side.A ? queueB.getNextDefender() : queueA.getNextDefender();
    }

    /**
     * One attack in a battle. Both minions involved loses HP equivalent to the
     * other's attack.
     *
     * @param attacker the attacking minion
     * @param defender the attacked minion
     */
    private void minionsLoseHPFromAttack(Minion attacker, Minion defender) {
        assert attacker.isAlive();
        assert defender.isAlive();
        attacker.loseHP(defender.getAttack());
        defender.loseHP(attacker.getAttack());
        System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
    }

    /**
     * Removes any dead minion as the consequence of an attack.
     */
    private void removeDeadMinions(Minion attacker, Minion defender) {
        if (nextToAttack == Side.A) {
            queueA.removeIfDead(attacker);
            queueB.removeIfDead(defender);
        } else {
            queueA.removeIfDead(defender);
            queueB.removeIfDead(attacker);
        }
    }

    private void alternateTurn() {
        if (nextToAttack == Side.A) {
            nextToAttack = Side.B;
        } else if (nextToAttack == Side.B) {
            nextToAttack = Side.A;
        }
    }

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
}
