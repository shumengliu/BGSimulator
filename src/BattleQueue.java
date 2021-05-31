import java.util.ArrayList;
import java.util.Random;

public class BattleQueue {
    static private final Random random = new Random();

    private ArrayList<Minion> minions;

    // index of the next minion to attack
    private int front;

    public BattleQueue(ArrayList<Minion> minionsOnBoard) {
        front = 0;
        minions = new ArrayList<>();
        for (Minion minion : minionsOnBoard) {
            minions.add(new Minion(minion));
        }
    }

    public boolean hasLivingMinion() {
        return minions.stream().anyMatch(Minion::isAlive);
    }

    /**
     * Returns the next minion to attack and increment front.
     * If front is outside the list boundary, reset to 0.
     *
     * @return the next minion to attack.
     */
    public Minion getNextAttacker() {
        if (front >= minions.size()) {
            front = 0;
        }
        Minion minion = minions.get(front);
        front++;
        return minion;
    }

    /**
     * Returns a random minion to be attacked.
     *
     * @return the next minion to be attacked
     */
    public Minion getNextDefender() {
        return minions.get(random.nextInt(minions.size()));
    }

    public void removeIfDead(Minion minion) {
        if (!minion.isAlive()) {
            minions.remove(minion);
        }
    }
}
