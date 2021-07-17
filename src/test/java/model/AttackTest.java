package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttackTest {
    private Minion attacker;
    private Minion defender;

    @Test
    void poisonousShouldInstantlyKill() {
        attacker = new Minion(MinionBase.DEADLY_SPORE);
        defender = new Minion("Test Minion", 10, 10, 1);

        AttackEvaluator.evaluate(attacker, defender);
        assertFalse(defender.isAlive());
    }

    @Test
    void divineShiledShouldBlockFirstInstanceOfDamage() {
        attacker = new Minion(MinionBase.ANNOY_O_MODULE);
        defender = new Minion("Test Minion", 3, 3, 1);

        AttackEvaluator.evaluate(attacker, defender);
        assertEquals(4, attacker.getHealth());
    }

    @Test
    void divineShiledShouldNotBlockSecondInstanceOfDamage() {
        attacker = new Minion(MinionBase.ANNOY_O_MODULE);
        defender = new Minion("Test Minion", 3, 3, 1);

        AttackEvaluator.evaluate(attacker, defender);
        AttackEvaluator.evaluate(attacker, defender);
        assertEquals(1, attacker.getHealth());
    }

    @Test
    void poisonousShouldNotWorkAgainstDivineShield() {
        attacker = new Minion(MinionBase.DEADLY_SPORE);
        defender = new Minion(MinionBase.ANNOY_O_MODULE);

        AttackEvaluator.evaluate(attacker, defender);
        assertTrue(defender.isAlive());
        assertEquals(4, defender.getHealth());
    }

    @Test
    void zeroAttackShouldNotBreakDivineShield() {
        attacker = new Minion("Zero-attack Minion", 0, 10, 1);
        defender = new Minion(MinionBase.ANNOY_O_MODULE);

        AttackEvaluator.evaluate(attacker, defender);
        assertTrue(defender.isShielded());
    }

    @Test
    void zeroAttackShouldNotTriggerPoisonous() {
        attacker = new Minion(0, 10);
        attacker.addKeyword(Keyword.POISONOUS);
        defender = new Minion(8, 8);

        AttackEvaluator.evaluate(attacker, defender);
        assertTrue(defender.isAlive());
    }
}
