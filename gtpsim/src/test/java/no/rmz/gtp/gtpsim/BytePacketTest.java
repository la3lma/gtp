package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BytePacketTest {

    private final static byte THREE_SET_SHIFT_2 = 0b00011100;
    private final static byte RIGHTMOST_SET = 0b1;
    private final static byte LEFTMOST_SET = (byte)  (0b1 << 6);

    @Test
    public void getRightmostBit() {
        final BytePacket bp
                = new BytePacket(new byte[]{RIGHTMOST_SET});
        assertEquals(1, bp.getBits(0, RIGHTMOST_SET));
    }

    @Test
    public void getLeftmostBit() {
        final BytePacket bp = new BytePacket(new byte[]{LEFTMOST_SET});
        assertEquals(1, bp.getBits(6, 1));
    }

    @Test
    public void getThreeBitsShiftedtwoLeft() {
        final BytePacket bp
                = new BytePacket(new byte[]{THREE_SET_SHIFT_2});
        // XXX So this is screwed up.
        assertEquals(0b111, bp.getBits(2, 3));
    }

    @Test
    public void getSingleBitShiftedRound() {
        for (int i = 0; i < 7; i++) {
            final byte b = (byte) (0b1 << i);
            final BytePacket bp
                    = new BytePacket(new byte[]{b});
            assertOnlyBitISet(i, bp);
        }
    }

    @Test
    public void setSingleBitShiftedRound() {
        for (int i = 0; i < 7; i++) {
            final BytePacket bp
                    = new BytePacket(new byte[]{0});
            bp.setBits(i, 1, 1);
            assertOnlyBitISet(i, bp);
        }
    }

    private void assertOnlyBitISet(int i, final BytePacket bp) {
        for (int j = 0; j < 7; j++) {
            final String msg
                    = String.format("j = %d, i = %d", j, i);
            if (i == j) {
                assertEquals("expect 1: " + msg, 0b1, bp.getBits(i, 1));
            } else {
                assertEquals("expect 0: " + msg, 0, bp.getBits(i, 1));
            }
        }
    }
}
