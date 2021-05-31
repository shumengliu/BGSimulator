public class Simulator {

    private Board board;

    public Simulator() {
        board = new Board();
    }

    public void addMinion(Minion minion, Side side) {
        board.addMinion(minion, side);
    }

    /**
     * Simulate the combat once.
     * Position (minions) is printed at the start of the combat.
     * Result, including winner and damage, is printed
     * after the combat.
     * Minions are modified after this type of combat.
     */
    public void simulateOnce() {
        board.printStartingPosition();
        board.battlePhase();
        board.printBattleResult();
    }

    /**
     * Simulate the combat for a given number of times.
     * Print the win rate.
     *
     * @param numberOfSims Number of combat simulations.
     */
    public void simulate(int numberOfSims) {
        board.printStartingPosition();
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
        System.out.println("PlayerB win rate: " + (double) (winB / numberOfSims * 100) + "%");
        System.out.println("Draw rate: " + (double) (draw / numberOfSims * 100) + "%");
        System.out.println("PlayerA win rate: " + (double) (winA / numberOfSims * 100) + "%");
    }


}
