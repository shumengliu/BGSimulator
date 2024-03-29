package model;

import java.util.ArrayList;
import java.util.Random;

public class BattleQueue {
    static private final Random random = new Random();

    private final ArrayList<Minion> minions;
    // index of the next minion to attack
    private int front;

    public BattleQueue() {
        front = 0;
        minions = new ArrayList<>();
    }

    public int size() {
        return minions.size();
    }

    public void addCloneOfMinion(Minion minion) {
        Minion clone = new Minion(minion);
        minions.add(clone);
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

    /**
     * Removes the given minion from queue if it is dead.
     *
     * @param minion the minion to remove.
     */
    public void removeIfDead(Minion minion) {
        if (!minion.isAlive()) {
            minions.remove(minion);
        }
    }

    public int getTierSum() {
        return minions.stream().map(Minion::getTier).mapToInt(Integer::intValue).sum();
    }

    public void reset() {
        minions.clear();
        front = 0;
    }
}
