package model;

public class Main {
    private static final Simulator simulator = new Simulator();

    public static void main(String[] args) {
        loadBoard1();

        simulator.simulateOnce();
//        simulator.Simulate(1000);
    }

    private static void loadBoard1() {
        simulator.addMinionToPosition(new Minion("B1", 3, 3, 2), Position.A1);
        simulator.addMinionToPosition(new Minion("B2", 2, 2, 2), Position.A2);
        simulator.addMinionToPosition(new Minion("B3", 1, 1, 1), Position.A3);
        simulator.addMinionToPosition(new Minion("A1", 2, 2, 2), Position.B1);
        simulator.addMinionToPosition(new Minion("A2", 2, 4, 2), Position.B2);
        simulator.addMinionToPosition(new Minion("A3", 3, 2, 1), Position.B3);
        simulator.addMinionToPosition(new Minion("A4", 2, 3, 1), Position.B4);
        simulator.addMinionToPosition(new Minion("A5", 2, 2, 2), Position.B5);
    }

//    private static void loadBoard2() {
//        simulator.addMinion(new Minion("����", 2, 2, 2), Side.B);
//        simulator.addMinion(new Minion("�г���B1", 3, 2, 1), Side.B);
//        simulator.addMinion(new Minion("�г���B2", 2, 1, 1), Side.B);
//        simulator.addMinion(new Minion("С����", 1, 1, 1), Side.B);
//        simulator.addMinion(new Minion("������", 3, 4, 1), Side.B);
//        simulator.addMinion(new Minion("����Ա", 3, 3, 2), Side.B);
//        simulator.addMinion(new Minion("����", 4, 6, 2), Side.A);
//        simulator.addMinion(new Minion("��е��", 4, 3, 2), Side.A);
//        simulator.addMinion(new Minion("����", 2, 2, 1), Side.A);
//        simulator.addMinion(new Minion("����", 3, 2, 2), Side.A);
//    }

}
