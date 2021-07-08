package model;

public enum MinionBase {
    ALLEYCAT("Alleycat", 1, 1, 1),
    DECK_SWABBIE("Deck Swabbie", 2, 2, 1),
    DRAGONSPAWN_LIEUTENANT("Dragonspawn Lieutenant", 2, 2, 1), // taunt
    MICRO_MACHINE("Micro Machine", 1, 2, 1),
    MURLOC_TIDECALLER("Murloc Tidecaller", 1, 2, 1),
    MURLOC_TIDEHUNTER("MUrloc Tidehunter", 2, 1, 1),
    RAZORFEN_GEOMANCER("Razorfen Geomancer", 3, 1, 1),
    REFRESEHING_ANOMALY("Refreshing Anomaly", 1, 4, 1),
    ROCKPOOL_HUNTER("Rockpool Hunter", 2, 3, 1),
    SELLMENTAL("Sellmental", 2, 2, 1),
    SUN_BACON_RELAXER

    private final String name;
    private final int attack;
    private final int hp;
    private final int tier;

    MinionBase(String name, int attack, int hp, int tier) {
        this.name = name;
        this.attack = attack;
        this.hp = hp;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHp() {
        return hp;
    }

    public int getTier() {
        return tier;
    }
}
