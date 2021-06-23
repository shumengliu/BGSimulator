package model;

/**
 * This class represents a simple minion in Battlegrounds.
 * A minion has health and attack.
 * A minion can also have keywords such as Taunt and Poisoned. (Not yet implemented)
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

    public Minion() {
        name = "Default Minion";
        atk = 1;
        currentHP = 1;
        tier = 1;
        alive = true;
    }

    public Minion(String name, int attack, int health, int tier) {
        this.name = name;
        atk = attack;
        currentHP = health;
        this.tier = tier;
        alive = true;
    }

    /**
     * Constructor for cloning a minion.
     */
    public Minion(Minion minion) {
        this.name = minion.getName();
        this.atk = minion.getAttack();
        this.currentHP = minion.getHealth();
        this.tier = minion.getTier();
        alive = true;
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

    public String getName() {
        return name;
    }

    public int getAttack() {
        return atk;
    }

    public int getHealth() {
        return currentHP;
    }

    public int getTier() {
        return tier;
    }

    public void setAttack(int atk) {
        this.atk = atk;
    }

    public void setHP(int HP) {
        this.currentHP = HP;
    }
}
