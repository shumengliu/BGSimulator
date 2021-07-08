package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void simulate1000TimesForA50Win50DrawBoard() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion("23Murloc", 2, 3, 1)),
                entry(Position.A2, new Minion("11Murloc", 1, 1, 1)),
                entry(Position.B1, new Minion("24Demon", 2, 4, 1))
        );
        when(board.getMinions()).thenReturn(sampleBoard);

        SimulationResult result = simulator.simulate(1000);
        isWithinPointOne(0.5, result.getWinRateForA());
        isWithinPointOne(0.5, result.getDrawRate());
    }

    @Test
    void simulate1000TimesForA33Win66LoseBoard() {
        sampleBoard = Map.ofEntries(
                entry(Position.A1, new Minion("91Big", 9, 1, 1)),
                entry(Position.A2, new Minion("22Medium", 2, 2, 1)),
                entry(Position.A3, new Minion("22Medium", 2, 2, 1)),
                entry(Position.A4, new Minion("22Medium", 2, 2, 1)),
                entry(Position.B1, new Minion("99Big", 9, 9, 1)),
                entry(Position.B2, new Minion("11Small", 1, 1, 1)),
                entry(Position.B3, new Minion("11Small", 1, 1, 1))
        );
        when(board.getMinions()).thenReturn(sampleBoard);

        SimulationResult result = simulator.simulate(1000);
        isWithinPointOne(0.33, result.getWinRateForA());
        isWithinPointOne(0.67, result.getWinRateForB());
        assertEquals(0, result.getDrawRate());
    }

    private void isWithinPointOne(double expected, double actual) {
        assertTrue(actual > expected - 0.1);
        assertTrue(actual < expected + 0.1);
    }
}
