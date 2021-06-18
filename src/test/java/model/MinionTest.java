package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinionTest {
    @Test
    void testConstructor() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertEquals("23Dragon", minion.getName());
        assertEquals(2, minion.getAttack());
        assertEquals(3, minion.getHealth());
        assertEquals(1, minion.getTier());
    }

    @Test
    void minionIsAliveRightAfterConstruction() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertTrue(minion.isAlive());
    }

    @Test
    void haveCorrectRemainingHPAfterLosingSome() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        minion.loseHP(1);
        assertEquals(2, minion.getHealth());
    }

    @Test
    void setToDeadAfterLosingTooMuchHP() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        minion.loseHP(3);
        assertFalse(minion.isAlive());
    }

    @Test
    void setToDeadAfterLosingHPTwice() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        minion.loseHP(1);
        assertTrue(minion.isAlive());
        minion.loseHP(2);
        assertFalse(minion.isAlive());
    }

    @Test
    void setAttack() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        minion.setAttack(4);
        assertEquals(4, minion.getAttack());
    }

    @Test
    void setHP() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        minion.setHP(5);
        assertEquals(5, minion.getHealth());
    }

    @Test
    void getName() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertEquals("23Dragon", minion.getName());
    }

    @Test
    void getAttack() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertEquals(2, minion.getAttack());
    }

    @Test
    void getHealth() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertEquals(3, minion.getHealth());
    }

    @Test
    void getTier() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertEquals(1, minion.getTier());
    }
}