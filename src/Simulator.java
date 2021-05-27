public class Simulator {

    private Battleground ground;

    public Simulator() {
        ground = new Battleground();
    }

    public void addMinion(Minion minion, Player player) {
        ground.addMinion(minion, player);
    }

    /**
     * Simulate the combat once.
     * Combat process will be printed.
     * Minions are modified after this type of combat.
     */
    public void SimulateOnce() {
        ground.detailedCombat();
    }

    /**
     * Simulate the combat for a given number of times.
     * Print the win rate.
     *
     * @param numberOfSims Number of combat simulations.
     */
    public void Simulate(int numberOfSims) {
        ground.printStartingPosition();
        double winA = 0;
        double winB = 0;
        double draw = 0;
        for (int i = 0; i < numberOfSims; i++) {
            int result = ground.combat();
            if (result == 1) {
                winA++;
            } else if (result == -1) {
                winB++;
            } else if (result == 0) {
                draw++;
            }
        }
        // Print win rates.
        System.out.println("Combat was simulated " + numberOfSims + " times.");
        System.out.println("PlayerB win rate: " + (double) (winB / numberOfSims * 100) + "%");
        System.out.println("Draw rate: " + (double) (draw / numberOfSims * 100) + "%");
        System.out.println("PlayerA win rate: " + (double) (winA / numberOfSims * 100) + "%");
    }
}
