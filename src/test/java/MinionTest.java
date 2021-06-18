import model.Minion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinionTest {

    @Test
    void testConstructor() {
        Minion minion = new Minion("23Dragon", 2, 3, 1);
        assertEquals("23Dragon", minion.getName());
        assertEquals(2, minion.getAttack());
        assertEquals(3, minion.getHealth());
        assertEquals(1, minion.getTier());
    }

    @Test
    void getName() {
        
    }

    @Test
    void getAttack() {
    }

    @Test
    void getHealth() {
    }

    @Test
    void getTier() {
    }

    @Test
    void loseHP() {
    }

    @Test
    void isAlive() {
    }
}