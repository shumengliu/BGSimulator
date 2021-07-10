package model;

import java.util.EnumSet;

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

    private EnumSet<Keyword> keywords;

    public Minion(String name, int attack, int health, int tier) {
        this.name = name;
        atk = attack;
        currentHP = health;
        this.tier = tier;
        alive = true;
        keywords = EnumSet.noneOf(Keyword.class);
    }

    public Minion() {
        this("Default Minion", 1, 1, 1);
    }

    /**
     * Constructor for cloning a minion.
     */
    public Minion(Minion other) {
        this.name = other.name;
        this.atk = other.atk;
        this.currentHP = other.currentHP;
        this.tier = other.tier;
        this.keywords = EnumSet.copyOf(other.keywords);
        alive = true;
    }

    public Minion(MinionBase base) {
        this.name = base.getName();
        this.atk = base.getAttack();
        this.currentHP = base.getHp();
        this.tier = base.getTier();
        this.keywords = base.getKeywords();
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

    public void addKeyword(Keyword keyword) {
        keywords.add(keyword);
    }

    public boolean isTaunt() {
        return keywords.contains(Keyword.TAUNT);
    }
}
