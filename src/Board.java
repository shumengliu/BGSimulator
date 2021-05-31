import java.util.ArrayList;
import java.util.Random;

/**
 * This is a class simulates the battleground. The main function of the
 * battleground is to implement the battle phase of the game.
 *
 * @author HonestBook
 */
public class Board {
    private ArrayList<Minion> minionsA;
    private ArrayList<Minion> minionsB;

    private BattleQueue queueA;
    private BattleQueue queueB;

    Random random = new Random();

    /**
     * Constructor of the Battleground class.
     */
    public Board() {
        minionsA = new ArrayList<>();
        minionsB = new ArrayList<>();
    }

//    /**
//     * Simulate a combat and return an integer
//     * indicating the result of the combat.
//     * Note that the minions remain intact
//     * after this type of combat.
//     *
//     * @return Either 1 (player A wins), 0 (draw), -1 (player B wins)
//     */
//    public int combat() {
//        // Create attack queue.
//        BattleQueue queueA = new BattleQueue(minionsA);
//        BattleQueue queueB = new BattleQueue(minionsB);
//
//        battlePhase();
//
//        if (queueA.hasLivingMinion()) {
//            return 1;
//        } else if (queueB.hasLivingMinion()) {
//            return -1;
//        } else {
//            return 0;
//        }
//    }

    /**
     * The battle phase of a combat.
     */
    public void battlePhase() {
        queueA = new BattleQueue(minionsA);
        queueB = new BattleQueue(minionsB);
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
    public void printStartingPosition() {
        System.out.println("Starting Position");
        StringBuilder bufferA = new StringBuilder("PlayerA: ");
        for (Minion minion : minionsA) {
            bufferA.append(minion.getAttack());
            bufferA.append("-");
            bufferA.append(minion.getHealth());
            bufferA.append("  ");
        }
        StringBuilder bufferB = new StringBuilder("PlayerB: ");
        for (Minion minion : minionsB) {
            bufferB.append(minion.getAttack());
            bufferB.append("-");
            bufferB.append(minion.getHealth());
            bufferB.append("  ");
        }
        System.out.println(bufferB.toString());
        System.out.println(bufferA.toString());
        System.out.println("Combat!");
    }

    /**
     * Print the result of a battle.
     */
    public void printBattleResult() {
        StringBuilder buffer = new StringBuilder("Result: ");
        if (minionsA.stream().anyMatch(Minion::isAlive)) {
            int damage = minionsA.stream()
                    .filter(Minion::isAlive)
                    .mapToInt(Minion::getTier)
                    .sum();
            buffer.append("PlayerA wins! ");
            buffer.append("PlayerB loses ");
            buffer.append(damage);
            buffer.append(" HP.");
        } else if (minionsB.stream().anyMatch(Minion::isAlive)) {
            int damage = minionsB.stream()
                    .filter(Minion::isAlive)
                    .mapToInt(Minion::getTier)
                    .sum();
            buffer.append("PlayerB wins! ");
            buffer.append("PlayerA loses ");
            buffer.append(damage);
            buffer.append(" HP.");
        } else {
            buffer.append("Draw!");
        }
        System.out.println(buffer.toString());
    }

    /**
     * Given the turn, return the next minion to attack from the battle queue.
     *
     * @param turn True if it is playerA's turn False otherwise
     * @return the next minion to attack
     */
    private Minion getNextAttackerByTurn(boolean turn) {
        return turn ? queueA.getNextAttacker() : queueB.getNextAttacker();
    }

    /**
     * Given the turn, return the next minion to be attacked from the battle queue.
     *
     * @param turn True if it is playerA's turn False otherwise
     * @return the next minion to attack
     */
    private Minion getNextDefenderByTurn(boolean turn) {
        return turn ? queueB.getNextDefender() : queueA.getNextDefender();
    }

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


    /**
     * One attack in a battle. Both minions involved loses HP equivalent to the
     * other's attack.
     *
     * @param attacker
     * @param defender
     */
    public void attack(Minion attacker, Minion defender) {
        attacker.loseHP(defender.getAttack());
        defender.loseHP(attacker.getAttack());
//		System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
    }

    /**
     * Return a list of minions of player A.
     *
     * @return
     */
    public ArrayList<Minion> getMinionsA() {
        return minionsA;
    }

    /**
     * Return a list of minions of player B.
     *
     * @return
     */
    public ArrayList<Minion> getMinionsB() {
        return minionsB;
    }

    /**
     * Add a minion to one of the players. This usually works with calling the
     * constructor of Minion in the parameter to create a new minion.
     *
     * @param minion The minion to add.
     * @param side   The owner of this minion.
     */
    public void addMinion(Minion minion, Side side) {
        if (side == Side.A) {
            minionsA.add(minion);
        } else {
            minionsB.add(minion);
        }
    }

    /**
     * Return if the battle will continue. The battle continues when both players
     * have at least one minion alive.
     *
     * @return
     */
    public boolean battleViable() {
        // Check if there are alive minions in both lists.
        return (queueA.hasLivingMinion() && queueB.hasLivingMinion());
    }
}
