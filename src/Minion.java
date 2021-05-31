/**
 * This class represents a simple minion in Battlegrounds.
 * A minion has health and attack.
 * A minion can also have keywords such as Taunt and Poisoned.
 * (Not yet implemented)
 *
 * @author HonestBook
 */
public class Minion {
    private int atk;

    // private int maxHP;

    private int currentHP;

    private boolean alive;

    private final String name;

    private final int tier;

    /**
     * Create an instance of Minion.
     *
     * @param name
     * @param attack
     * @param health
     */
    public Minion(String name, int attack, int health, int tier) {
        this.name = name;
        atk = attack;
        currentHP = health;
        this.tier = tier;
        alive = true;
    }

    /**
     * Constructor for cloning a minion.
     *
     * @param minion
     */
    public Minion(Minion minion) {
        this.name = minion.getName();
        this.atk = minion.getAttack();
        this.currentHP = minion.getHealth();
        this.tier = minion.getTier();
        alive = true;
    }

    /**
     * Return the name of this minion.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the attack of this minion.
     *
     * @return
     */
    public int getAttack() {
        return atk;
    }


    /**
     * Return the health of this minion.
     *
     * @return
     */
    public int getHealth() {
        return currentHP;
    }

    /**
     * Return the tier of this minion.
     *
     * @return
     */
    public int getTier() {
        return tier;
    }

    /**
     * Reduces the minion's current HP.
     * If its HP is below 0 after reduction, set it to dead.
     *
     * @param damage the damage taken.
     */
    public void loseHP(int damage) {
        currentHP = Math.max(currentHP - damage, 0);
        if (currentHP == 0) {
            alive = false;
        }
    }

    /**
     * Return if the minion is alive.
     *
     * @return True if the minion is alive.
     */
    public boolean isAlive() {
        return alive;
    }
}
