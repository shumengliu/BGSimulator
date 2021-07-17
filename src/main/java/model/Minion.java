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

    private boolean isShielded;

    private final EnumSet<Keyword> keywords;

    public Minion(int attack, int health) {
        name = "Default Minion";
        this.atk = attack;
        this.currentHP = health;
        tier = 1;
        alive = true;
        keywords = EnumSet.noneOf(Keyword.class);
    }

    public Minion() {
        this(1, 1);
    }

    public Minion(String name, int attack, int health, int tier) {
        this.name = name;
        atk = attack;
        currentHP = health;
        this.tier = tier;
        alive = true;
        keywords = EnumSet.noneOf(Keyword.class);
        checkDivineShield();
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
        checkDivineShield();
    }

    public Minion(MinionBase base) {
        this.name = base.getName();
        this.atk = base.getAttack();
        this.currentHP = base.getHp();
        this.tier = base.getTier();
        this.keywords = base.getKeywords();
        alive = true;
        checkDivineShield();
    }

    private void checkDivineShield() {
        isShielded = hasDivineShieldKeyword();
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

    public void setDead() {
        alive = false;
    }

    public void addKeyword(Keyword keyword) {
        keywords.add(keyword);
        if (keyword == Keyword.DIVINE_SHIELD) {
            isShielded = true;
        }
    }

    public boolean isTaunt() {
        return keywords.contains(Keyword.TAUNT);
    }

    public boolean isPoisonous() {
        return keywords.contains(Keyword.POISONOUS);
    }

    public boolean hasDivineShieldKeyword() {
        return keywords.contains(Keyword.DIVINE_SHIELD);
    }

    public boolean isShielded() {
        return isShielded;
    }

    public void losesShield() {
        isShielded = false;
    }
}
