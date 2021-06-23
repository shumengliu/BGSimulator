package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleQueueTest {
    private BattleQueue queue;

    private Minion minionSample1;
    private Minion minionSample2;
    private Minion minionSample3;

    @BeforeEach
    void setUp() {
        queue = new BattleQueue();
        minionSample1 = new Minion("23Dragon", 2, 3, 1);
        minionSample2 = new Minion("11Tiger", 1, 1, 1);
        minionSample3 = new Minion("33Southsea Captain", 3, 3, 2);
    }

    @Test
    void rightAfterCreationFrontIsZero() {
        assertEquals(0, queue.size());
    }

    @Test
    void addingMinionIncrementsSize() {
        queue.addCloneOfMinion(minionSample1);
        assertEquals(1, queue.size());
    }

    @Test
    void addMinionCreatesAnotherInstance() {
        queue.addCloneOfMinion(minionSample1);
        assertNotEquals(minionSample1, queue.getNextAttacker());
    }

    @Test
    void rightAfterCreationThereIsNoLivingMinion() {
        assertFalse(queue.hasLivingMinion());
    }

    @Test
    void afterAddingMinionThereIsLivingMinion() {
        queue.addCloneOfMinion(minionSample1);
        assertTrue(queue.hasLivingMinion());
    }

    @Test
    void getNextAttackerShouldReturnFirstMinionWhenCalledOnce() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);
        Minion actual = queue.getNextAttacker();
        assertEquals(minionSample1.getName(), actual.getName());
    }

    @Test
    void getNextAttackerShouldReturnSecondMinionWhenCalledTwice() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        queue.getNextAttacker();
        Minion actual = queue.getNextAttacker();
        assertEquals(minionSample2.getName(), actual.getName());
    }

    @Test
    void getNextAttackerShouldWorkInACircularFashionWhenCalledMultipleTimes() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        for (int i = 0; i < 10; i++) {
            Minion minion1 = queue.getNextAttacker();
            assertEquals(minionSample1.getName(), minion1.getName());
            Minion minion2 = queue.getNextAttacker();
            assertEquals(minionSample2.getName(), minion2.getName());
            Minion minion3 = queue.getNextAttacker();
            assertEquals(minionSample3.getName(), minion3.getName());
        }
    }

    @Test
    void getNextDefenderShouldReturnTheMinionWhenThereIsOnlyOne() {
        queue.addCloneOfMinion(minionSample1);

        Minion actual = queue.getNextDefender();
        assertEquals(minionSample1.getName(), actual.getName());
    }

    @Test
    void getNextMinionShouldVisitEveryMinionIfCalled100Times() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        boolean minion1visited = false;
        boolean minion2visited = false;
        boolean minion3visited = false;
        for (int i = 0; i < 100; i++) {
            Minion minion = queue.getNextDefender();
            if (minion.getName().equals(minionSample1.getName())) {
                minion1visited = true;
            } else if (minion.getName().equals(minionSample2.getName())) {
                minion2visited = true;
            } else if (minion.getName().equals(minionSample3.getName())) {
                minion3visited = true;
            }
        }

        assertTrue(minion1visited);
        assertTrue(minion2visited);
        assertTrue(minion3visited);
    }

    @Test
    void removeIfDeadShouldRemoveDeadMinion() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        Minion minion = queue.getNextAttacker();
        minion.loseHP(10); // Minion Sample 1 has 3 health.
        queue.removeIfDead(minion);

        assertEquals(2, queue.size());
    }

    @Test
    void removeIfDeadShouldNotRemoveLivingMinion() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        Minion minion = queue.getNextAttacker();
        minion.loseHP(1); // Minion Sample 1 has 3 health.
        queue.removeIfDead(minion);

        assertEquals(3, queue.size());
    }


    @Test
    void getTierSumShouldReturnCorrectValue() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        int actual = queue.getTierSum();
        assertEquals(4, actual);
    }

    @Test
    void clearAllMinionsShouldRemoveAllMinions() {
        queue.addCloneOfMinion(minionSample1);
        queue.addCloneOfMinion(minionSample2);
        queue.addCloneOfMinion(minionSample3);

        queue.clearAllMinions();
        assertEquals(0, queue.size());
    }
}