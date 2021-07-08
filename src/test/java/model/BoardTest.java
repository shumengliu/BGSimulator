package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    private Board board;

    @BeforeEach
    void instantiateBoard() {
        board = new Board();
    }

    @Test
    void addOneMinionToA1() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        board.setMinionInPosition(minion, Position.A1);
        Map<Position, Minion> expectedMap = Map.of(Position.A1, minion);
        assertEquals(minion, board.getMinionByPosition(Position.A1));
        assertEquals(expectedMap, board.getMinions());
    }

    @Test
    void replaceMinionOnA1() {
        Minion oldMinion = new Minion("23Dragon", 2, 3, 1);
        Minion newMinion = new Minion("21Murloc", 2, 1, 1);
        board.setMinionInPosition(oldMinion, Position.A1);
        board.setMinionInPosition(newMinion, Position.A1);
        Map<Position, Minion> expectedMap = Map.of(Position.A1, newMinion);
        assertEquals(newMinion, board.getMinionByPosition(Position.A1));
        assertEquals(expectedMap, board.getMinions());
    }

    @Test
    void addThreeMinionsToEachSide() {
        Minion minionA1 = new Minion("minionA1", 2, 1, 1);
        Minion minionA2 = new Minion("minionA2", 2, 2, 1);
        Minion minionA3 = new Minion("minionA3", 2, 3, 1);
        Minion minionB1 = new Minion("21Murloc", 3, 1, 1);
        Minion minionB2 = new Minion("23Dragon", 3, 2, 1);
        Minion minionB3 = new Minion("21Murloc", 3, 3, 1);
        board.setMinionInPosition(minionA1, Position.A1);
        board.setMinionInPosition(minionA2, Position.A2);
        board.setMinionInPosition(minionA3, Position.A3);
        board.setMinionInPosition(minionB1, Position.B1);
        board.setMinionInPosition(minionB2, Position.B2);
        board.setMinionInPosition(minionB3, Position.B3);
        Map<Position, Minion> expectedMap = Map.of(Position.A1, minionA1,
                Position.A2, minionA2,
                Position.A3, minionA3,
                Position.B1, minionB1,
                Position.B2, minionB2,
                Position.B3, minionB3);
        assertEquals(minionA3, board.getMinionByPosition(Position.A3));
        assertEquals(minionB2, board.getMinionByPosition(Position.B2));
        assertEquals(expectedMap, board.getMinions());
    }

    @Test
    void toStringWithNoMinions() {
        String expected = "Starting Position\n" +
                "PlayerA: \n" +
                "PlayerB: ";
        assertEquals(expected, board.toString());
    }

    @Test
    void toStringWithThreeMinionsOnEachSide() {
        Minion minionA1 = new Minion("minionA1", 2, 1, 1);
        Minion minionA2 = new Minion("minionA2", 2, 2, 1);
        Minion minionA3 = new Minion("minionA3", 2, 3, 1);
        Minion minionB1 = new Minion("21Murloc", 3, 1, 1);
        Minion minionB2 = new Minion("23Dragon", 3, 2, 1);
        Minion minionB3 = new Minion("21Murloc", 3, 3, 1);
        board.setMinionInPosition(minionA1, Position.A1);
        board.setMinionInPosition(minionA2, Position.A2);
        board.setMinionInPosition(minionA3, Position.A3);
        board.setMinionInPosition(minionB1, Position.B1);
        board.setMinionInPosition(minionB2, Position.B2);
        board.setMinionInPosition(minionB3, Position.B3);
        String expected = """
                Starting Position
                PlayerA: 2-1  2-2  2-3 \s
                PlayerB: 3-1  3-2  3-3 \s""";
        assertEquals(expected, board.toString());
    }
}