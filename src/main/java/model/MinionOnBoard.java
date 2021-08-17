package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.EnumSet;

public class MinionOnBoard {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty attack = new SimpleIntegerProperty();
    private final IntegerProperty hp = new SimpleIntegerProperty();
    private final IntegerProperty tier = new SimpleIntegerProperty();
    private EnumSet<Keyword> keywords;

    public MinionOnBoard() {
        name.set("default minion");
        attack.set(1);
        hp.set(1);
        tier.set(1);
        keywords = EnumSet.noneOf(Keyword.class);
    }

    public MinionOnBoard(String name, int attack, int hp, int tier) {
        this.name.set(name);
        this.attack.set(attack);
        this.hp.set(hp);
        this.tier.set(tier);
        keywords = EnumSet.noneOf(Keyword.class);
    }

    public MinionOnBoard(MinionBase base) {
        this(base.getName(), base.getAttack(), base.getHp(), base.getTier());
        keywords = base.getKeywords();
    }

    public void addKeyword(Keyword keyword) {
        keywords.add(keyword);
    }

    public boolean hasKeyword(Keyword keyword) {
        return keywords.contains(keyword);
    }

    public int getTier() {
        return tier.get();
    }

    public void setTier(int tier) {
        this.tier.set(tier);
    }

    public int getAttack() {
        return attack.get();
    }

    public void setAttack(int attack) {
        this.attack.set(attack);
    }

    public int getHp() {
        return hp.get();
    }

    public void setHp(int hp) {
        this.hp.set(hp);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

