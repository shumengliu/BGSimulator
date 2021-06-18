package model;

public class Simulator {

    private final Board board;

    private BattleRunner runner;

    public Simulator() {
        board = new Board();
    }

    public void addMinionToPosition(Minion minion, Position position) {
        board.addMinionToPosition(minion, position);
    }

    /**
     * Simulate the combat once.
     * Position (minions) is printed at the start of the combat.
     * Result, including winner and damage, is printed
     * after the combat.
     * Minions are modified after this type of combat.
     */
    public void simulateOnce() {
        board.printBoard();
        runner.battlePhase();
        runner.printBattleResult();
    }

    /**
     * Simulate the combat for a given number of times.
     * Print the win rate.
     *
     * @param numberOfSims Number of combat simulations.
     */
    public void simulate(int numberOfSims) {
        board.printBoard();
        double winA = 0;
        double winB = 0;
        double draw = 0;
//        for (int i = 0; i < numberOfSims; i++) {
//            int result = board.combat();
//            if (result == 1) {
//                winA++;
//            } else if (result == -1) {
//                winB++;
//            } else if (result == 0) {
//                draw++;
//            }
//        }
        // Print win rates.
        System.out.println("Combat was simulated " + numberOfSims + " times.");
        System.out.println("PlayerB win rate: " + (winB / numberOfSims * 100) + "%");
        System.out.println("Draw rate: " + (draw / numberOfSims * 100) + "%");
        System.out.println("PlayerA win rate: " + (winA / numberOfSims * 100) + "%");
    }


}
