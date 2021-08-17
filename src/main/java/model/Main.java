package model;

public class Main {
    private static final Simulator simulator = new Simulator();

    private static final Board board = new Board();

    public static void main(String[] args) {
        simulator.setBoard(board);

        loadBoard1();

        simulator.simulateOnce();
//        simulator.Simulate(1000);
    }

    private static void loadBoard1() {
        board.setMinionInPosition(new MinionOnBoard("A1", 3, 3, 2), Position.A1);
        board.setMinionInPosition(new MinionOnBoard("A2", 2, 2, 2), Position.A2);
        board.setMinionInPosition(new MinionOnBoard("A3", 1, 1, 1), Position.A3);
        board.setMinionInPosition(new MinionOnBoard("B1", 2, 2, 2), Position.B1);
        board.setMinionInPosition(new MinionOnBoard("B2", 2, 4, 2), Position.B2);
        board.setMinionInPosition(new MinionOnBoard("B3", 3, 2, 1), Position.B3);
        board.setMinionInPosition(new MinionOnBoard("B4", 2, 3, 1), Position.B4);
        board.setMinionInPosition(new MinionOnBoard("B5", 2, 2, 2), Position.B5);
    }

//    private static void loadBoard2() {
//        simulator.addMinionFromBoardForm(new Minion("����", 2, 2, 2), Side.B);
//        simulator.addMinionFromBoardForm(new Minion("�г���B1", 3, 2, 1), Side.B);
//        simulator.addMinionFromBoardForm(new Minion("�г���B2", 2, 1, 1), Side.B);
//        simulator.addMinionFromBoardForm(new Minion("С����", 1, 1, 1), Side.B);
//        simulator.addMinionFromBoardForm(new Minion("������", 3, 4, 1), Side.B);
//        simulator.addMinionFromBoardForm(new Minion("����Ա", 3, 3, 2), Side.B);
//        simulator.addMinionFromBoardForm(new Minion("����", 4, 6, 2), Side.A);
//        simulator.addMinionFromBoardForm(new Minion("��е��", 4, 3, 2), Side.A);
//        simulator.addMinionFromBoardForm(new Minion("����", 2, 2, 1), Side.A);
//        simulator.addMinionFromBoardForm(new Minion("����", 3, 2, 2), Side.A);
//    }

}
