package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BattleRunnerTest {
    private BattleRunner runner;

    private Board board;

    private Map<Position, Minion> sampleBoard;

    @BeforeEach
    void setUp() {
        runner = new BattleRunner();
        board = mock(Board.class);
    }

    @Test
    void simpleBattleWithOneMinionHasCorrectResult() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion())
        );
        BattleResult result = getBattleResultFromBoard();
        assertEquals(BattleResult.Outcome.WINFORA, result.getOutcome());
    }

    @Test
    void test5050WinOrDrawBoard() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion("23Murloc", 2, 3, 1)),
                entry(Position.A2, new Minion("11Murloc", 1, 1, 1)),
                entry(Position.B1, new Minion("24Demon", 2, 4, 1))
        );
        BattleResult result = getBattleResultFromBoard();
        assertNotEquals(BattleResult.Outcome.WINFORB, result.getOutcome());
    }

    @Test
    void testPoisonousWorksProperly() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion(MinionBase.DEADLY_SPORE)),
                entry(Position.B1, new Minion("Big Minion", 10, 10, 6))
        );
        BattleResult result = getBattleResultFromBoard();
        assertEquals(BattleResult.Outcome.DRAW, result.getOutcome());
    }

    @Test
    void moduleShouldWinOverVulgarHomunculus() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion(MinionBase.ANNOY_O_MODULE)),
                entry(Position.B1, new Minion(MinionBase.VULGAR_HOMUNCULUS))
        );
        BattleResult result = getBattleResultFromBoard();
        assertEquals(BattleResult.Outcome.WINFORA, result.getOutcome());
    }

    @Test
    void moduleShouldDrawAHangryDragon() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion(MinionBase.ANNOY_O_MODULE)),
                entry(Position.B1, new Minion(MinionBase.HANGRY_DRAGON))
        );
        BattleResult result = getBattleResultFromBoard();
        assertEquals(BattleResult.Outcome.DRAW, result.getOutcome());
    }

    @Test
    void sporeShouldLoseToModule() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion(MinionBase.DEADLY_SPORE)),
                entry(Position.B1, new Minion(MinionBase.ANNOY_O_MODULE))
        );
        BattleResult result = getBattleResultFromBoard();
        assertEquals(BattleResult.Outcome.WINFORB, result.getOutcome());
    }

    @Test
    void twoSporesShouldDrawAgainstAModule() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion(MinionBase.DEADLY_SPORE)),
                entry(Position.A2, new Minion(MinionBase.DEADLY_SPORE)),
                entry(Position.B1, new Minion(MinionBase.ANNOY_O_MODULE))
        );
        BattleResult result = getBattleResultFromBoard();
        assertEquals(BattleResult.Outcome.DRAW, result.getOutcome());
    }

    private BattleResult getBattleResultFromBoard() {
        when(board.getMinions()).thenReturn(sampleBoard);
        runner.initializeQueuesFromBoard(board);

        return runner.battlePhase();
    }


}
