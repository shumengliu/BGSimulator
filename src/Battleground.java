import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This is a class simulates the battleground. The main function of the
 * battleground is to implement the battle phase of the game.
 *
 * @author HonestBook
 */
public class Battleground {
    private ArrayList<Minion> minionsA;
    private ArrayList<Minion> minionsB;
    private ArrayList<Minion> minionsTempA;
    private ArrayList<Minion> minionsTempB;

    Random random = new Random();

    /**
     * Constructor of the Battleground class.
     */
    public Battleground() {
        minionsA = new ArrayList<>();
        minionsB = new ArrayList<>();
    }

    /**
     * Simulate a combat and return an integer
     * indicating the result of the combat.
     * Note that the minions remain intact
     * after this type of combat.
     *
     * @return Either 1 (player A wins), 0 (draw), -1 (player B wins)
     */
    public int combat() {
        // Copy minions.
        minionsTempA = new ArrayList<>();
        for (Minion minion : minionsA) {
            minionsTempA.add(new Minion(minion));
        }
        minionsTempB = new ArrayList<>();
        for (Minion minion : minionsB) {
            minionsTempB.add(new Minion(minion));
        }
        battlePhase();
        int result = 0;
        if (minionsA.stream().anyMatch(minion -> minion.isAlive())) {
            result = 1;
        } else if (minionsB.stream().anyMatch(minion -> minion.isAlive())) {
            result = -1;
        } else {
            result = 0;
        }
        // Restore minions after battle phase.
        minionsA.clear();
        for (Minion minion : minionsTempA) {
            minionsA.add(new Minion(minion));
        }
        minionsB.clear();
        for (Minion minion : minionsTempB) {
            minionsB.add(new Minion(minion));
        }
        return result;
    }

    /**
     * Simulate a single combat.
     * Position (minions) is printed at the combat.
     * Result, including winner and damage, is printed
     * after the combat.
     * The minions are modified after this type of combat.
     */
    public void detailedCombat() {
        printStartingPosition();
        battlePhase();
        printBattleResult();
    }

    /**
     * Print the position at the start of combat.
     */
    public void printStartingPosition() {
        System.out.println("Starting Position");
        StringBuffer bufferA = new StringBuffer("PlayerA: ");
        for (Minion minion : minionsA) {
            bufferA.append(minion.getAttack());
            bufferA.append("-");
            bufferA.append(minion.getHealth());
            bufferA.append("  ");
        }
        StringBuffer bufferB = new StringBuffer("PlayerB: ");
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
     * The battle phase of a combat.
     */
    private void battlePhase() {
        // A flag indicating whose turn to attack.
        // True if this is player A's turn to attack.
        boolean turn = true;
        // There is a fifty percent chance that player B starts first.
        if (random.nextInt(100) < 50) {
            turn = false;
        }

        Iterator<Minion> atkIteratorA = minionsA.iterator();
        Iterator<Minion> atkIteratorB = minionsB.iterator();

        // Each iteration of this loop represents one attack.
        while (battleViable()) {
            Minion attacker = null;
            Minion defender = null;
            // Select attacker and defender if
            // it is PlayerA's turn to attack.
            if (turn) {
                // Reset attack iterator if there is no next minion.
                if (!atkIteratorA.hasNext()) {
                    atkIteratorA = minionsA.iterator();
                }
                // Find the attacker.
                while (attacker == null) {
                    Minion nextAttaker = atkIteratorA.next();
                    // If that minion is alive, set it as attacker.
                    if (nextAttaker.isAlive()) {
                        attacker = nextAttaker;
                        // Else remove this minion from the list
                        // and get the next minion.
                    } else {
                        atkIteratorA.remove();
                        // Reset attack iterator if there is no next
                        // minion after removal.
                        if (!atkIteratorA.hasNext()) {
                            atkIteratorA = minionsA.iterator();
                        }
                    }
                }

                // Find the defender.
                while (defender == null) {
                    Minion nextDefender = minionsB.get(random.nextInt(minionsB.size()));
                    // If that minion is alive, set it as the defender.
                    if (nextDefender.isAlive()) {
                        defender = nextDefender;
                    }
                }
                // Reset the attack iterator.
            }
            // Select attacker and defender if
            // it is PlayerB's turn to attack.
            else {
                // Reset attack iterator if there is no next minion.
                if (!atkIteratorB.hasNext()) {
                    atkIteratorB = minionsB.iterator();
                }
                // Find the attacker.
                while (attacker == null) {
                    Minion nextAttaker = atkIteratorB.next();
                    // If that minion is alive, set it as attacker.
                    if (nextAttaker.isAlive()) {
                        attacker = nextAttaker;
                        // Else remove this minion from the list
                        // and get the next minion.
                    } else {
                        atkIteratorB.remove();
                        // Reset attack iterator if there is no next
                        // minion after removal.
                        if (!atkIteratorB.hasNext()) {
                            atkIteratorB = minionsB.iterator();
                        }
                    }
                }

                // Find the defender.
                while (defender == null) {
                    Minion nextDefender = minionsA.get(random.nextInt(minionsA.size()));
                    // If that minion is alive, set it as the defender.
                    if (nextDefender.isAlive()) {
                        defender = nextDefender;
                    }
                }
            }
            attack(attacker, defender);
            // Change attacking player.
            turn = !turn;
        }
    }

    /**
     * Print the result of a battle.
     */
    private void printBattleResult() {
        StringBuffer buffer = new StringBuffer("Result: ");
        if (minionsA.stream().anyMatch(minion -> minion.isAlive())) {
            int damage = minionsA.stream()
                    .filter(minion -> minion.isAlive())
                    .mapToInt(minion -> minion.getTier())
                    .sum();
            buffer.append("PlayerA wins! ");
            buffer.append("PlayerB loses ");
            buffer.append(damage);
            buffer.append(" HP.");
        } else if (minionsB.stream().anyMatch(minion -> minion.isAlive())) {
            int damage = minionsB.stream()
                    .filter(minion -> minion.isAlive())
                    .mapToInt(minion -> minion.getTier())
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
     * @param player The owner of this minion.
     */
    public void addMinion(Minion minion, Player player) {
        if (player == Player.pA) {
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
        return (minionsA.stream().anyMatch(minion -> minion.isAlive())) &&
                (minionsB.stream().anyMatch(minion -> minion.isAlive()));
    }
}
