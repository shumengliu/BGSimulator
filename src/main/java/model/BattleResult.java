package model;

public class BattleResult {
    public enum Outcome {
        WINFORA, WINFORB, DRAW
    }

    Outcome outcome;

    int damage;

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
