package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// XXX Signs are messing things up!
public class BytePacketTest {

    private final static byte THREE_SET_SHIFT_2 = 0b00011100;
    private final static byte RIGHTMOST_SET = 0b00000001;
    private final static byte LEFTMOST_SET =  (byte) (0xff & (0b1 << 7));

    @Test
    public void getRightmostBit() {
        final BytePacket bp
                = new BytePacket(new byte[]{RIGHTMOST_SET});
        assertEquals(1, bp.getBits(0, RIGHTMOST_SET));
    }

    @Test
    public void getLeftmostBit() {
        final BytePacket bp = new BytePacket(new byte[]{LEFTMOST_SET});
        assertEquals(1, bp.getBits(7, LEFTMOST_SET));
    }

    @Test
    public void getThreeBitsShiftedtwoLeft() {
        final BytePacket bp
                = new BytePacket(new byte[]{THREE_SET_SHIFT_2});

        // XXX So this is screwed up.
        assertEquals(0b111, bp.getBits(2, THREE_SET_SHIFT_2));
    }

}
