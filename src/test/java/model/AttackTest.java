package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttackTest {
    private MinionInBattle attacker;
    private MinionInBattle defender;

    @Test
    void poisonousShouldInstantlyKill() {
        attacker = MinionFactory.createBattleFormFromBase(MinionBase.DEADLY_SPORE);
        defender = new MinionInBattle("Test Minion", 10, 10, 1);

        AttackEvaluator.evaluate(attacker, defender);
        assertFalse(defender.isAlive());
    }

    @Test
    void divineShiledShouldBlockFirstInstanceOfDamage() {
        attacker = MinionFactory.createBattleFormFromBase(MinionBase.ANNOY_O_MODULE);
        defender = new MinionInBattle("Test Minion", 3, 3, 1);

        AttackEvaluator.evaluate(attacker, defender);
        assertEquals(4, attacker.getHealth());
    }

    @Test
    void divineShiledShouldNotBlockSecondInstanceOfDamage() {
        attacker = MinionFactory.createBattleFormFromBase(MinionBase.ANNOY_O_MODULE);
        defender = new MinionInBattle("Test Minion", 3, 3, 1);

        AttackEvaluator.evaluate(attacker, defender);
        AttackEvaluator.evaluate(attacker, defender);
        assertEquals(1, attacker.getHealth());
    }

    @Test
    void poisonousShouldNotWorkAgainstDivineShield() {
        attacker = MinionFactory.createBattleFormFromBase(MinionBase.DEADLY_SPORE);
        defender = MinionFactory.createBattleFormFromBase(MinionBase.ANNOY_O_MODULE);

        AttackEvaluator.evaluate(attacker, defender);
        assertTrue(defender.isAlive());
        assertEquals(4, defender.getHealth());
    }

    @Test
    void zeroAttackShouldNotBreakDivineShield() {
        attacker = new MinionInBattle("Zero-attack Minion", 0, 10, 1);
        defender = MinionFactory.createBattleFormFromBase(MinionBase.ANNOY_O_MODULE);

        AttackEvaluator.evaluate(attacker, defender);
        assertTrue(defender.isShielded());
    }

    @Test
    void zeroAttackShouldNotTriggerPoisonous() {
        attacker = new MinionInBattle(0, 10);
        attacker.setPoisonous(true);
        defender = new MinionInBattle(8, 8);

        AttackEvaluator.evaluate(attacker, defender);
        assertTrue(defender.isAlive());
    }
}
