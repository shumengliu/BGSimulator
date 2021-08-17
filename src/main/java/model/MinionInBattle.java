package model;

/**
 * This class represents a simple minion in Battlegrounds.
 * A minion has health and attack.
 * A minion can also have keywords such as Taunt and Poisoned. (Not yet implemented)
 *
 * @author HonestBook
 */
public class MinionInBattle {
    private int attack;

    private int currentHP;

    private boolean alive;

    private final String name;

    private final int tier;

    private boolean isShielded = false;

    private boolean isPoisonous = false;

    private boolean isTaunt = false;

    public MinionInBattle(int attack, int health) {
        name = "Default Minion";
        this.attack = attack;
        this.currentHP = health;
        tier = 1;
        alive = true;
    }

    public MinionInBattle() {
        this(1, 1);
    }

    public MinionInBattle(String name, int attack, int health, int tier) {
        this.name = name;
        this.attack = attack;
        currentHP = health;
        this.tier = tier;
        alive = true;
    }

//    public MinionInBattle(MinionBase base) {
//        this.name = base.getName();
//        this.attack = base.getAttack();
//        this.currentHP = base.getHp();
//        this.tier = base.getTier();
//        alive = true;
//    }

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
        return attack;
    }

    public int getHealth() {
        return currentHP;
    }

    public int getTier() {
        return tier;
    }

    public void setAttack(int atk) {
        this.attack = atk;
    }

    public void setHP(int HP) {
        this.currentHP = HP;
    }

    public void setDead() {
        alive = false;
    }

    public boolean isTaunt() {
        return isTaunt;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public boolean isShielded() {
        return isShielded;
    }

    public void setShielded(boolean shielded) {
        isShielded = shielded;
    }

    public void setPoisonous(boolean poisonous) {
        isPoisonous = poisonous;
    }

    public void setTaunt(boolean taunt) {
        isTaunt = taunt;
    }

    public void losesShield() {
        isShielded = false;
    }
}
