package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BytePacketTest {

    // XXX Are we getting to the eight bit?

    private final static int MAX_SHIFT = 7;
    private final static byte THREE_SET_SHIFT_2 = 0b00011100;
    private final static byte RIGHTMOST_SET = 0b1;
    private final static byte LEFTMOST_SET = (byte) (0b1 << (MAX_SHIFT - 1));

    @Test
    public void getRightmostBit() {
        final OctetPacket bp
                = new OctetPacket(new byte[]{RIGHTMOST_SET});
        assertEquals(1, bp.getBits(0, RIGHTMOST_SET));
    }

    @Test
    public void getLeftmostBit() {
        final OctetPacket bp = new OctetPacket(new byte[]{LEFTMOST_SET});
        assertEquals(1, bp.getBits(6, 1));
    }

    @Test
    public void getThreeBitsShiftedtwoLeft() {
        final OctetPacket bp
                = new OctetPacket(new byte[]{THREE_SET_SHIFT_2});
        // XXX So this is screwed up.
        assertEquals(0b111, bp.getBits(2, 3));
    }

    @Test
    public void getSingleBitShiftedRound() {
        for (int i = 0; i < MAX_SHIFT; i++) {
            final byte b = (byte) (0b1 << i);
            final OctetPacket bp
                    = new OctetPacket(new byte[]{b});
            assertOnlyBitISet(i, bp);
        }
    }

    @Test
    public void setSingleBitShiftedRound() {
        for (int i = 0; i < MAX_SHIFT; i++) {
            final OctetPacket bp
                    = new OctetPacket(new byte[]{0});
            bp.setBits(i, 1, 1);
            assertOnlyBitISet(i, bp);
        }
    }

    private void assertOnlyBitISet(final int i, final OctetPacket bp) {
        for (int j = 0; j < MAX_SHIFT; j++) {
            final String msg
                    = String.format("j = %d, i = %d, p = %s", j, i, bp);
            final int bitj = bp.getBits(j, 1);
            if (i == j) {
                assertEquals("expect 1: " + msg, 0b1, bitj);
            } else {
                assertEquals("expect 0: " + msg, 0b0, bitj);
            }
        }
    }
}
