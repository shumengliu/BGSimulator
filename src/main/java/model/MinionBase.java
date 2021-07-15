package model;

import java.util.Arrays;
import java.util.EnumSet;

public enum MinionBase {
    // Tier 1
    ALLEYCAT("Alleycat", 1, 1, 1),
    DECK_SWABBIE("Deck Swabbie", 2, 2, 1),
    DRAGONSPAWN_LIEUTENANT("Dragonspawn Lieutenant", 2, 2, 1,  Keyword.TAUNT), // taunt
    MICRO_MACHINE("Micro Machine", 1, 2, 1),
    MURLOC_TIDECALLER("Murloc Tidecaller", 1, 2, 1),
    MURLOC_TIDEHUNTER("MUrloc Tidehunter", 2, 1, 1),
    RAZORFEN_GEOMANCER("Razorfen Geomancer", 3, 1, 1),
    REFRESEHING_ANOMALY("Refreshing Anomaly", 1, 4, 1),
    ROCKPOOL_HUNTER("Rockpool Hunter", 2, 3, 1),
    SELLMENTAL("Sellmental", 2, 2, 1),
    SUN_BACON_RELAXER("Sun-Bacon Relaxer", 1, 2, 1),
    VULGAR_HOMUNCULUS("Vulgar Homunculus", 2, 4, 1, Keyword.TAUNT), // taunt
    WRATH_WEAVER("Wrath Weaver", 1, 3, 1),

    // Tier 2
    FREEDEALING_GAMBLER("Freedealing Gambler", 3, 3, 2),
    MENAGERIE_MUG("Menagrie Mug", 2, 2, 2),
    METALTOOTH_LEAPER("Metaltooth Leaper", 3, 3, 2),
    MOLTEN_ROCK("Molten Rock", 2, 4, 2, Keyword.TAUNT), // taunt
    NATHREZIM_OVERSEER("Nathrezim Overseer", 2, 4, 2),
    PARTY_ELEMENTAL("Party Elemental", 3, 2, 2),
    PROPHET_OF_THE_BOAR("Prophet of the Board", 2, 3, 2),
    RABID_SAUROLISK("Rabid Saurolisk", 3, 2, 2),
    ROADBOAR("Roadboar", 1, 4, 2),
    STEWARD_OF_TIME("Steward of Time", 3, 3, 2),

    // Tier 3
    ARCANE_ASSISTANT("Arcane Assistant", 3, 3, 3),
    BANNERBOAR("Bannerboar", 1, 4, 3),
    BLOODSAIL_CANNONEER("Bloodsail Cannoneer", 4, 3, 3),
    BRISTLEBACK_BRUTE("Bristleback Brute", 3, 3, 3),
    COLDLIGHT_SEER("Coldlight Seer", 2, 3, 3),
    CRYSTALWEAVER("Crystalweaver", 5, 4, 3),
    FELFIN_NAVIGATOR("Felfin Navigator", 4, 4, 3),
    HANGRY_DRAGON("Hangry Dragon", 4, 4, 3),
    HOUNDMASTER("Houndmaster", 4, 3, 3),
    IRON_SENSEI("Iron Sensei", 2, 2, 3),
    NECROLYTE("Necrolyte", 3, 3, 3),
    SALTY_LOOTER("Salty Looter", 4, 4, 3),
    SCREWJANK_CLUNKER("Screwjank Clunker", 2, 5, 3),
    SOUL_DEVOURER("Soul Devourer", 3, 3, 3),
    SOUTHSEA_STRONGARM("Southsea Strongarm", 4, 3, 3),
    STASIS_ELEMENTAL("Stasis Elemental", 4, 4, 3),
    THORNCALLER("Thorncaller", 4, 3, 3),
    TWILIGHT_EMISSARY("Twilight Emissary", 4, 4, 3, Keyword.TAUNT), // taunt
    WARDEN_OF_OLD("Warden of Old", 3, 3, 3),
    BRONZE_WARDEN("Bronze Warden", 2, 1, 3, Keyword.DIVINE_SHIELD, Keyword.REBORN),
    CRACKLING_CYCLONE("Crackling Cyclone", 4, 1, 3, Keyword.DIVINE_SHIELD, Keyword.WINDFURY),
    DEFLECT_O_BOT("Deflect-o-bot", 3, 2, 3, Keyword.DIVINE_SHIELD), // todo finish deflect bot

    // Tier 4
    COBALT_SCALEBANE("Cobalt Scalebane", 5, 5, 4),
    DEFENDER_OF_ARGUS("Defender of Argus", 3, 3, 4),
    DYNAMIC_DUO("Dynamic Duo", 3, 4, 4, Keyword.TAUNT), // taunt
    GOLDGRUBBER("Goldgrubber", 2, 2, 4),
    HEXRUIN_MARAUDER("Hexruin Marauer", 3, 5, 4),
    MAJORDOMO_EXECUTUS("Majordomo Executus", 6, 3, 4),
    MENAGRIE_JUG("Menagerie Jug", 3, 3, 4),
    PRIMALFIN_LOOKOUT("Primalfin Lookout", 3, 2, 4),
    TOXFIN("Toxfin", 1, 2, 4),
    VIRMEN_SENSEI("Virmen Sensei", 4, 5, 4),
    ANNOY_O_MODULE("Annoy-o-Module", 2, 4, 4, Keyword.DIVINE_SHIELD, Keyword.TAUNT),
    BOLVAR_FIREBLOOD("Bolvar, Fireblood", 1, 7, 4, Keyword.DIVINE_SHIELD), // todo finish Bolvar


    // Tier 5
    AGAMAGGAN_THE_GREAT_BOAR("Agamaggan, the Great Boar", 6, 6, 5),
    AGGEM_THORNCURSE("Aggem Thorncurse", 3, 6, 5),
    ANNIHILAN_BATTLEMASTER("Annihilan Battlemaster", 3, 1, 5),
    BRANN_BRONZEBEARD("Brann Bronzebeard", 2, 4, 5),
    CAPN_HOGGARR("Cap'n Hoggarr", 6, 6, 5),
    LIGHTFANG_ENFORCER("Lightfang Enforcer", 2, 2, 5),
    MUROZOND("Murozond", 5, 5, 5),
    MYTHRAX_THE_UNRAVELER("Mythrax the Unraveler", 4, 4, 5),
    NAT_PAGLE_EXTREME_ANGLER("Nat Pagle, Extreme Angler", 8, 5, 5),
    NOMI_KITCHEN_NIGHTMARE("Nomi, Kitchen Nightmare", 4, 4, 5),
    RAZORGORE_THE_UNTAMED("Razorgore, the Untamed", 4, 6, 5),
    STRONGSHELL_SCAVENGER("Strongshell Scavenger", 2, 3, 5),
    DEADLY_SPORE("Deadly Spore", 1, 1, 5, Keyword.POISONOUS),
    TAVERN_TEMPEST("Tavern Tempest", 4, 4, 5),
    BRISTLEBACK_KNIGHT("Bristleback Knight", 4, 8, 5, Keyword.DIVINE_SHIELD, Keyword.WINDFURY), // todo finish this

    // Tier 6
    ARCHDRUID_HAMUUL("Archdruid Hamuul", 4, 4, 6),
    CAPTAIN_FLAT_TUSK("Captain Flat Tusk", 9, 6, 6),
    CHARLGA("Charlga", 4, 4, 6),
    KALECGOS_ARCANE_ASPECT("Kalecgos, Arcane Aspect", 4, 12, 6),
    LIUETENANT_GARR("Lieutenant Garr", 8, 8, 6, Keyword.TAUNT), // taunt
    LIL_RAG("Lil' Rag", 6, 6, 6),
    MAEXXNA("Maexxna", 2, 8, 6, Keyword.POISONOUS);

    private final String name;
    private final int attack;
    private final int hp;
    private final int tier;
    private final EnumSet<Keyword> keywords;

    MinionBase(String name, int attack, int hp, int tier, Keyword... keywords) {
        this.name = name;
        this.attack = attack;
        this.hp = hp;
        this.tier = tier;
        this.keywords = EnumSet.noneOf(Keyword.class);
        this.keywords.addAll(Arrays.asList(keywords));
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

    public EnumSet<Keyword> getKeywords() {
        return keywords;
    }
}
