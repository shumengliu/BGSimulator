package model;

public class Simulator {

    private Board board;

    private BattleRunner runner;

    public Simulator() {
        board = new Board();
        runner = new BattleRunner();
    }

    public void addMinionToBoard(Minion minion, Position position) {
        board.setMinionInPosition(minion, position);
    }

    public Board getBoard() {
        return board;
    }

    public void simulateOnce() {
        System.out.println(board.toString());
        runner.initializeQueuesFromBoard(board);
        runner.battlePhase();
        runner.printBattleResult();
    }

    public SimulationResult simulate(int numberOfSims) {
        System.out.println(board.toString());
        SimulationResult simResult = new SimulationResult();
        for (int i = 0; i < numberOfSims; i++) {
            runner.initializeQueuesFromBoard(board);
            BattleResult battleResult = runner.battlePhase();
            simResult.parseNextBattleResult(battleResult);
        }
        // Print win rates.
        System.out.println("Combat was simulated " + numberOfSims + " times.");
        System.out.println("PlayerA win rate: " + simResult.getWinRateForA() * 100 + "%");
        System.out.println("Draw rate: " + simResult.getDrawRate() * 100 + "%");
        System.out.println("PlayerB win rate: " + simResult.getWinRateForB() * 100 + "%");
        return simResult;
    }

    // setters for testing

    public void setBoard(Board board) {
        this.board = board;
    }
}
