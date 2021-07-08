package model;

public class SimulationResult {
    private int numberOfBattles;
    private int winForACount;
    private int winForBCount;
    private int drawCount;
    private int totalDamageDealtByA;
    private int totalDamageDealtByB;

    public SimulationResult() {
        resetSimulation();
    }

    public void parseNextBattleResult(BattleResult battleResult) {
        numberOfBattles++;
        switch (battleResult.getOutcome()) {
            case WINFORA -> {
                winForACount++;
                totalDamageDealtByA += battleResult.getDamage();
            }
            case WINFORB -> {
                winForBCount++;
                totalDamageDealtByB += battleResult.getDamage();
            }
            case DRAW -> {
                drawCount++;
            }
        }
    }

    public void resetSimulation() {
        numberOfBattles = 0;
        winForACount = 0;
        winForBCount = 0;
        drawCount = 0;
        totalDamageDealtByA = 0;
        totalDamageDealtByB = 0;
    }

    public double getWinRateForA() {
        return (double) winForACount / numberOfBattles;
    }

    public double getWinRateForB() {
        return (double) winForBCount / numberOfBattles;
    }

    public double getDrawRate() {
        return (double) drawCount / numberOfBattles;
    }
}
