package coins.state;

import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    private Coins state1 = new Coins(7, 3); // the original initial state

    private Coins state2; // the goal state
    {
        BitSet bs = new BitSet(7);
        bs.set(0, 7);
        state2 = new Coins(7, 3, bs);
    }

    @Test
    void testCanFlipFalse() {
        Coins coin = state1.clone();
        BitSet bs = new BitSet(coin.getN() + 1);
        bs.set(0, coin.getM() - 1);
        bs.set(coin.getN());
        assertFalse(coin.canFlip(bs));

        Coins coin2 = state1.clone();
        BitSet bs2 = new BitSet(coin2.getN() + 1);
        bs2.set(coin2.getN());
        assertFalse(coin2.canFlip(bs2));

        Coins coin3 = state1.clone();
        BitSet bs3 = new BitSet(coin3.getN());
        assertFalse(coin3.canFlip(bs3));
    }

    @Test
    void testCanFlipTrue() {
        Coins coin = state1.clone();
        BitSet bs = new BitSet(coin.getN());
        bs.set(0, coin.getM());
        assertTrue(coin.canFlip(bs));
    }

    @Test
    void testGenerateFlipsThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(7, 0));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(0, 3));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(3, 7));
    }

    @Test
    void testCheckArguments() {
        BitSet bs1 = new BitSet(2);
        bs1.set(1);
        assertThrows(IllegalArgumentException.class, () -> new Coins(1, 1, bs1));
    }

    @Test
    void testFlip() {
        Coins coin = state1.clone();
        BitSet bs = new BitSet(coin.getN());
        bs.set(0, coin.getN());
        coin.flip(bs);
        assertEquals(bs, coin.getCoins());
    }

    @Test
    void testIsGoalTrue() {
        assertTrue(state2.isGoal());
    }

    @Test
    void testIsGoalFalse() {
        assertFalse(state1.isGoal());
    }

    @Test
    void testGetFlipsTrue() {
        assertTrue(state1.getFlips().size() != 0);
    }

    @Test
    void testGetFlipsFalse() {
        assertFalse(state1.getFlips().size() == 0);
    }

    @Test
    void testEqualsFalse() {
        BitSet bs1 = new BitSet(3);
        bs1.set(0, 3);
        Coins coin1 = new Coins(3, 1, bs1);
        Coins coin2 = new Coins(4, 1, bs1);
        assertFalse(coin1.equals(coin2));
    }

    @Test
    void testEqualsTrue() {
        BitSet bs1 = new BitSet(3);
        bs1.set(0, 3);
        Coins coin1 = new Coins(3, 1, bs1);
        assertTrue(coin1.equals(coin1));
    }

    @Test
    void testHashCodeTrue() {
        assertTrue(state1.hashCode() == Objects.hash(state1.getN(), state1.getM(), state1.getCoins()));
    }

    @Test
    void testHashCodeFalse() {
        assertFalse(state1.hashCode() == state2.hashCode());
    }

    @Test
    void testToStringNoneFlipped() {
        assertEquals(state1.toString(), "O|O|O|O|O|O|O");
        assertEquals(state2.toString(), "1|1|1|1|1|1|1");
    }


}