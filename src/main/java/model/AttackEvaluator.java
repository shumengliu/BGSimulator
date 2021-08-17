package model;

public class AttackEvaluator {
    public static void evaluate(MinionInBattle attacker, MinionInBattle defender) {
        assert attacker.isAlive();
        assert defender.isAlive();

        evaluateOneSide(attacker, defender);
        evaluateOneSide(defender, attacker);
        System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
    }

    // minion1 takes damage
    private static void evaluateOneSide(MinionInBattle minion1, MinionInBattle minion2) {
        // zero atk does nothing in an attack
        if (hasZeroAtk(minion2)) {
            return;
        }
        if (minion1.isShielded()) {
            minion1.losesShield();
        } else {
            minion1.loseHP(minion2.getAttack());
            if (minion2.isPoisonous()) {
                minion1.setDead();
            }
        }
    }

    private static boolean hasZeroAtk(MinionInBattle minion) {
        return minion.getAttack() <= 0;
    }
}
