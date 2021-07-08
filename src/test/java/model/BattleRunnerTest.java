package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(board.getMinions()).thenReturn(sampleBoard);
        runner.initializeQueuesFromBoard(board);

        BattleResult result = runner.battlePhase();
        assertEquals(BattleResult.Outcome.WINFORA, result.getOutcome());
    }

    @Test
    void test5050WinOrDrawBoard() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion("23Murloc", 2, 3, 1)),
                entry(Position.A2, new Minion("11Murloc", 1, 1, 1)),
                entry(Position.B1, new Minion("24Demon", 2, 4, 1))
        );
        when(board.getMinions()).thenReturn(sampleBoard);
        runner.initializeQueuesFromBoard(board);

        BattleResult result = runner.battlePhase();
        assertEquals(BattleResult.Outcome.WINFORA, result.getOutcome());
    }
}
