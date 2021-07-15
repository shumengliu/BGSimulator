package model;

public class AttackEvaluator {
    public static void evaluate(Minion attacker, Minion defender) {
        assert attacker.isAlive();
        assert defender.isAlive();

        evaluateOneSide(attacker, defender);
        evaluateOneSide(defender, attacker);
        System.out.println(attacker.getName() + " attacked " + defender.getName() + ".");
    }

    private static void evaluateOneSide(Minion minion1, Minion minion2) {
        if (minion1.isShielded()) {
            minion1.losesShield();
        } else {
            minion1.loseHP(minion2.getAttack());
        }
        if (minion1.isPoisonous()) {
            minion2.setDead();
        }
    }
}
