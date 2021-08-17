package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BattleQueue {
    static private final Random random = new Random();

    private final ArrayList<MinionInBattle> minions;
    // index of the next minion to attack
    private int front;

    public BattleQueue() {
        front = 0;
        minions = new ArrayList<>();
    }

    public int size() {
        return minions.size();
    }

    public void addMinionFromBoardForm(MinionOnBoard minion) {
        minions.add(MinionFactory.createBattleFormForMinionOnBoard(minion));
    }

    public boolean hasLivingMinion() {
        return minions.stream().anyMatch(MinionInBattle::isAlive);
    }

    /**
     * Returns the next minion to attack and increment front.
     * If front is outside the list boundary, reset to 0.
     *
     * @return the next minion to attack.
     */
    public MinionInBattle getNextAttacker() {
        if (front >= minions.size()) {
            front = 0;
        }
        MinionInBattle minion = minions.get(front);
        front++;
        return minion;
    }

    /**
     * Returns a random minion to be attacked.
     *
     * @return the next minion to be attacked
     */
    public MinionInBattle getNextDefender() {
        if (hasAnyTauntMinion()) {
            List<MinionInBattle> tauntMinions = minions.stream().filter(MinionInBattle::isTaunt).collect(Collectors.toList());
            return getRandomMinionFromList(tauntMinions);
        } else {
            return getRandomMinionFromList(minions);
        }
    }

    private boolean hasAnyTauntMinion() {
        return minions.stream().anyMatch(MinionInBattle::isTaunt);
    }

    private MinionInBattle getRandomMinionFromList(List<MinionInBattle> minions) {
        return minions.get(random.nextInt(minions.size()));
    }

    /**
     * Removes the given minion from queue if it is dead.
     *
     * @param minion the minion to remove.
     */
    public void removeIfDead(MinionInBattle minion) {
        if (!minion.isAlive()) {
            minions.remove(minion);
        }
    }

    public int getTierSum() {
        return minions.stream().map(MinionInBattle::getTier).mapToInt(Integer::intValue).sum();
    }

    public void reset() {
        minions.clear();
        front = 0;
    }
}
