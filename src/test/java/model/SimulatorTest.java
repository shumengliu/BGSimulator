package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimulatorTest {
    private Simulator simulator;

    @BeforeEach
    void setUp() {
        simulator = new Simulator();
    }

    @Test
    void simulateOnceShouldWork() {
        simulator.simulateOnce();
    }

    @Test
    void simulateMultipleTimesShouldWork() {
        simulator.simulate(1000);
    }
}
