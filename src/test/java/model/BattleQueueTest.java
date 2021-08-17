package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleQueueTest {
    private BattleQueue queue;

    private MinionOnBoard minionSample1;
    private MinionOnBoard minionSample2;
    private MinionOnBoard minionSample3;

    @BeforeEach
    void setUp() {
        queue = new BattleQueue();
        minionSample1 = new MinionOnBoard(MinionBase.MICRO_MACHINE);
        minionSample2 = new MinionOnBoard(MinionBase.ALLEYCAT);
        minionSample3 = new MinionOnBoard(MinionBase.FREEDEALING_GAMBLER);
    }

    @Test
    void rightAfterCreationFrontIsZero() {
        assertEquals(0, queue.size());
    }

    @Test
    void addingMinionIncrementsSize() {
        queue.addMinionFromBoardForm(minionSample1);
        assertEquals(1, queue.size());
    }

    @Test
    void addMinionCreatesAnotherInstance() {
        queue.addMinionFromBoardForm(minionSample1);
        assertNotEquals(minionSample1, queue.getNextAttacker());
    }

    @Test
    void rightAfterCreationThereIsNoLivingMinion() {
        assertFalse(queue.hasLivingMinion());
    }

    @Test
    void afterAddingMinionThereIsLivingMinion() {
        queue.addMinionFromBoardForm(minionSample1);
        assertTrue(queue.hasLivingMinion());
    }

    @Test
    void getNextAttackerShouldReturnFirstMinionWhenCalledOnce() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);
        MinionInBattle actual = queue.getNextAttacker();
        assertEquals(minionSample1.getName(), actual.getName());
    }

    @Test
    void getNextAttackerShouldReturnSecondMinionWhenCalledTwice() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        queue.getNextAttacker();
        MinionInBattle actual = queue.getNextAttacker();
        assertEquals(minionSample2.getName(), actual.getName());
    }

    @Test
    void getNextAttackerShouldWorkInACircularFashionWhenCalledMultipleTimes() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        for (int i = 0; i < 10; i++) {
            MinionInBattle minion1 = queue.getNextAttacker();
            assertEquals(minionSample1.getName(), minion1.getName());
            MinionInBattle minion2 = queue.getNextAttacker();
            assertEquals(minionSample2.getName(), minion2.getName());
            MinionInBattle minion3 = queue.getNextAttacker();
            assertEquals(minionSample3.getName(), minion3.getName());
        }
    }

    @Test
    void getNextDefenderShouldReturnTheMinionWhenThereIsOnlyOne() {
        queue.addMinionFromBoardForm(minionSample1);

        MinionInBattle actual = queue.getNextDefender();
        assertEquals(minionSample1.getName(), actual.getName());
    }

    @Test
    void getNextDefenderShouldPrioritiseTauntMinions() {
        minionSample1.addKeyword(Keyword.TAUNT);
        queue.addMinionFromBoardForm(minionSample1); // this is taunt
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        for (int i = 0; i < 10; i++) {
            MinionInBattle actual = queue.getNextDefender();
            assertEquals(minionSample1.getName(), actual.getName());
        }
    }

    @Test
    void getNextDefenderShouldReturnOneOfTheTauntMinions() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);
        queue.addMinionFromBoardForm(new MinionOnBoard(MinionBase.MOLTEN_ROCK)); // this is taunt
        queue.addMinionFromBoardForm(new MinionOnBoard(MinionBase.TWILIGHT_EMISSARY)); // this is taunt
        queue.addMinionFromBoardForm(new MinionOnBoard(MinionBase.ARCANE_ASSISTANT));
        queue.addMinionFromBoardForm(new MinionOnBoard(MinionBase.DECK_SWABBIE));

        for (int i = 0; i < 10; i++) {
            MinionInBattle actual = queue.getNextDefender();
            assertTrue(actual.isTaunt());
        }
    }

    @Test
    void getNextMinionShouldVisitEveryMinionIfCalled100Times() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        boolean minion1visited = false;
        boolean minion2visited = false;
        boolean minion3visited = false;
        for (int i = 0; i < 100; i++) {
            MinionInBattle minion = queue.getNextDefender();
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
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        MinionInBattle minion = queue.getNextAttacker();
        minion.loseHP(10); // Minion Sample 1 has 3 health.
        queue.removeIfDead(minion);

        assertEquals(2, queue.size());
    }

    @Test
    void removeIfDeadShouldNotRemoveLivingMinion() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        MinionInBattle minion = queue.getNextAttacker();
        minion.loseHP(1); // Minion Sample 1 has 3 health.
        queue.removeIfDead(minion);

        assertEquals(3, queue.size());
    }


    @Test
    void getTierSumShouldReturnCorrectValue() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        int actual = queue.getTierSum();
        assertEquals(4, actual);
    }

    @Test
    void clearAllMinionsShouldRemoveAllMinions() {
        queue.addMinionFromBoardForm(minionSample1);
        queue.addMinionFromBoardForm(minionSample2);
        queue.addMinionFromBoardForm(minionSample3);

        queue.reset();
        assertEquals(0, queue.size());
    }
}