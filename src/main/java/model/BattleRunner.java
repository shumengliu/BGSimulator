package model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BattleRunner {
    private static final Random random = new Random();
    private static final List<Position> POSITIONS_LIST = Arrays.asList(Position.A1, Position.A2, Position.A3, Position.A4, Position.A5, Position.A6, Position.A7, Position.B1, Position.B2, Position.B3, Position.B4, Position.B5, Position.B6, Position.B7);

    private final BattleQueue queueA;
    private final BattleQueue queueB;
    private Side nextToAttack; // indicating whose turn to attack.

    public BattleRunner() {
        queueA = new BattleQueue();
        queueB = new BattleQueue();
    }

    public void initializeQueuesFromBoard(Board board) {
        queueA.reset();
        queueB.reset();
        Map<Position, MinionOnBoard> minions = board.getMinions();
        initializeQueuesFromMinionsMap(minions);
    }

    private void initializeQueuesFromMinionsMap(Map<Position, MinionOnBoard> minions) {
        for (Position position : POSITIONS_LIST) {
            MinionOnBoard minionOnBoard = minions.get(position);
            if (minionOnBoard != null) {
                BattleQueue bq = getBattleQueueByPosition(position);
                bq.addMinionFromBoardForm(minions.get(position));
            }
        }
    }

    private BattleQueue getBattleQueueByPosition(Position position) {
        if (position.getSide() == Side.A) {
            return queueA;
        } else {
            return queueB;
        }
    }

    public BattleResult battlePhase() {
        decideInitiative();
        // Each iteration of this loop represents one attack.
        while (battleViable()) {
            executeNextAttack();
            alternateTurn();
        }
        System.out.println("Battle ended.");
        return evaluateResult();
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
        MinionInBattle attacker = getNextAttacker();
        MinionInBattle defender = getNextDefender();

        AttackEvaluator.evaluate(attacker, defender);
        removeDeadMinions(attacker, defender);
    }

    private MinionInBattle getNextAttacker() {
        return nextToAttack == Side.A ? queueA.getNextAttacker() : queueB.getNextAttacker();
    }

    private MinionInBattle getNextDefender() {
        return nextToAttack == Side.A ? queueB.getNextDefender() : queueA.getNextDefender();
    }

    /**
     * Removes any dead minion as the consequence of an attack.
     */
    private void removeDeadMinions(MinionInBattle attacker, MinionInBattle defender) {
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

    private BattleResult evaluateResult() {
        BattleResult result = new BattleResult();
        if (queueA.hasLivingMinion()) {
            result.setOutcome(BattleResult.Outcome.WINFORA);
            result.setDamage(queueA.getTierSum());
            return result;
        } else if (queueB.hasLivingMinion()) {
            result.setOutcome(BattleResult.Outcome.WINFORB);
            result.setDamage(queueB.getTierSum());
            return result;
        } else {
            result.setOutcome(BattleResult.Outcome.DRAW);
            return result;
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
