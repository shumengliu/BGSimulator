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
        MinionOnBoard minion = new MinionOnBoard("23Dragon", 2, 3, 1);
        board.setMinionToPosition(minion, Position.A1);
        Map<Position, MinionOnBoard> expectedMap = Map.of(Position.A1, minion);
        assertEquals(minion, board.getMinionByPosition(Position.A1));
        assertEquals(expectedMap, board.getMinions());
    }

    @Test
    void replaceMinionOnA1() {
        MinionOnBoard oldMinion = new MinionOnBoard("23Dragon", 2, 3, 1);
        MinionOnBoard newMinion = new MinionOnBoard("21Murloc", 2, 1, 1);
        board.setMinionToPosition(oldMinion, Position.A1);
        board.setMinionToPosition(newMinion, Position.A1);
        Map<Position, MinionOnBoard> expectedMap = Map.of(Position.A1, newMinion);
        assertEquals(newMinion, board.getMinionByPosition(Position.A1));
        assertEquals(expectedMap, board.getMinions());
    }

    @Test
    void addThreeMinionsToEachSide() {
        MinionOnBoard minionA1 = new MinionOnBoard("minionA1", 2, 1, 1);
        MinionOnBoard minionA2 = new MinionOnBoard("minionA2", 2, 2, 1);
        MinionOnBoard minionA3 = new MinionOnBoard("minionA3", 2, 3, 1);
        MinionOnBoard minionB1 = new MinionOnBoard("21Murloc", 3, 1, 1);
        MinionOnBoard minionB2 = new MinionOnBoard("23Dragon", 3, 2, 1);
        MinionOnBoard minionB3 = new MinionOnBoard("21Murloc", 3, 3, 1);
        board.setMinionToPosition(minionA1, Position.A1);
        board.setMinionToPosition(minionA2, Position.A2);
        board.setMinionToPosition(minionA3, Position.A3);
        board.setMinionToPosition(minionB1, Position.B1);
        board.setMinionToPosition(minionB2, Position.B2);
        board.setMinionToPosition(minionB3, Position.B3);
        Map<Position, MinionOnBoard> expectedMap = Map.of(Position.A1, minionA1,
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
        String expected = """
                Starting Position
                PlayerA:\s
                PlayerB:\s""";
        assertEquals(expected, board.toString());
    }

    @Test
    void toStringWithThreeMinionsOnEachSide() {
        MinionOnBoard minionA1 = new MinionOnBoard("minionA1", 2, 1, 1);
        MinionOnBoard minionA2 = new MinionOnBoard("minionA2", 2, 2, 1);
        MinionOnBoard minionA3 = new MinionOnBoard("minionA3", 2, 3, 1);
        MinionOnBoard minionB1 = new MinionOnBoard("21Murloc", 3, 1, 1);
        MinionOnBoard minionB2 = new MinionOnBoard("23Dragon", 3, 2, 1);
        MinionOnBoard minionB3 = new MinionOnBoard("21Murloc", 3, 3, 1);
        board.setMinionToPosition(minionA1, Position.A1);
        board.setMinionToPosition(minionA2, Position.A2);
        board.setMinionToPosition(minionA3, Position.A3);
        board.setMinionToPosition(minionB1, Position.B1);
        board.setMinionToPosition(minionB2, Position.B2);
        board.setMinionToPosition(minionB3, Position.B3);
        String expected = """
                Starting Position
                PlayerA: 2-1  2-2  2-3 \s
                PlayerB: 3-1  3-2  3-3 \s""";
        assertEquals(expected, board.toString());
    }
}