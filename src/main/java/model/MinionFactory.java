package model;

public class MinionFactory {
    public static MinionInBattle createBattleFormForMinionOnBoard(MinionOnBoard minion) {
        MinionInBattle battleForm = new MinionInBattle(minion.getName(), minion.getAttack(), minion.getHp(), minion.getTier());
        if (minion.hasKeyword(Keyword.DIVINE_SHIELD)) {
            battleForm.setShielded(true);
        }
        if (minion.hasKeyword(Keyword.POISONOUS)) {
            battleForm.setPoisonous(true);
        }
        if (minion.hasKeyword(Keyword.TAUNT)) {
            battleForm.setTaunt(true);
        }
        return battleForm;
    }

    public static MinionInBattle createBattleFormFromBase(MinionBase base) {
        MinionOnBoard minion = new MinionOnBoard(base);
        return createBattleFormForMinionOnBoard(minion);
    }
}
