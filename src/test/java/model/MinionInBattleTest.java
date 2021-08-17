package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinionInBattleTest {
    @Test
    void testDefaultConstructor() {
        MinionInBattle minion = new MinionInBattle();
        assertEquals("Default Minion", minion.getName());
        assertEquals(1, minion.getAttack());
        assertEquals(1, minion.getHealth());
        assertEquals(1, minion.getTier());
        assertTrue(minion.isAlive());
    }

    @Test
    void testConstructor() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        assertEquals("23Dragon", minion.getName());
        assertEquals(2, minion.getAttack());
        assertEquals(3, minion.getHealth());
        assertEquals(1, minion.getTier());
    }

    @Test
    void createAlleycatFromMinionBaseShouldHaveCorrectStats() {
        MinionInBattle minion = MinionFactory.createBattleFormFromBase(MinionBase.ALLEYCAT);
        assertEquals("Alleycat", minion.getName());
        assertEquals(1, minion.getAttack());
        assertEquals(1, minion.getHealth());
        assertEquals(1, minion.getTier());
    }

    @Test
    void dragonspawnLieutenantShouldBeTaunt() {
        MinionInBattle minion = MinionFactory.createBattleFormFromBase(MinionBase.DRAGONSPAWN_LIEUTENANT);
        assertTrue(minion.isTaunt());
    }

    @Test
    void alleyCatShouldNotBeTaunt() {
        MinionInBattle minion = MinionFactory.createBattleFormFromBase(MinionBase.ALLEYCAT);
        assertFalse(minion.isTaunt());
    }

    @Test
    void maexxnaShouldBePoisonous() {
        MinionInBattle minion = MinionFactory.createBattleFormFromBase(MinionBase.MAEXXNA);
        assertTrue(minion.isPoisonous());
    }

    @Test
    void bronzeWardenShouldHaveDivineShield() {
        MinionInBattle minion = MinionFactory.createBattleFormFromBase(MinionBase.BRONZE_WARDEN);
        assertTrue(minion.isShielded());
    }

    @Test
    void minionIsAliveRightAfterConstruction() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        assertTrue(minion.isAlive());
    }

    @Test
    void haveCorrectRemainingHPAfterLosingSome() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        minion.loseHP(1);
        assertEquals(2, minion.getHealth());
    }

    @Test
    void setToDeadAfterLosingTooMuchHP() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        minion.loseHP(3);
        assertFalse(minion.isAlive());
    }

    @Test
    void setToDeadAfterLosingHPTwice() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        minion.loseHP(1);
        assertTrue(minion.isAlive());
        minion.loseHP(2);
        assertFalse(minion.isAlive());
    }

    @Test
    void setAttack() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        minion.setAttack(4);
        assertEquals(4, minion.getAttack());
    }

    @Test
    void setHP() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        minion.setHP(5);
        assertEquals(5, minion.getHealth());
    }

    @Test
    void getName() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        assertEquals("23Dragon", minion.getName());
    }

    @Test
    void getAttack() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        assertEquals(2, minion.getAttack());
    }

    @Test
    void getHealth() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        assertEquals(3, minion.getHealth());
    }

    @Test
    void getTier() {
        MinionInBattle minion = new MinionInBattle("23Dragon", 2, 3, 1);
        assertEquals(1, minion.getTier());
    }
}