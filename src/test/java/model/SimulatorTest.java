package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimulatorTest {
    private Simulator simulator;
    private Board board;
    private Map<Position, Minion> sampleBoard;

    @BeforeEach
    void setUp() {
        simulator = new Simulator();
        board = mock(Board.class);
        simulator.setBoard(board);
    }

    @Test
    void simulateOnceShouldWork() {
        simulator.simulateOnce();
    }

    @Test
    void simulateMultipleTimesShouldWork() {
        simulator.simulate(1000);
    }

    @Test
    void simulate1000TimesAndGetTheResult() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion())
        );
        when(board.getMinions()).thenReturn(sampleBoard);

        SimulationResult result = simulator.simulate(1000);
        assertEquals(1, result.getWinRateForA());
        assertEquals(0, result.getWinRateForB());
        assertEquals(0, result.getDrawRate());
    }

    @Test
    void simulate1000TimesForAFiftyWinRateBoard() {
        fail();
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion())
        );
        when(board.getMinions()).thenReturn(sampleBoard);

        SimulationResult result = simulator.simulate(1000);
        assertEquals(1, result.getWinRateForA());
        assertEquals(0, result.getWinRateForB());
        assertEquals(0, result.getDrawRate());
    }
}
