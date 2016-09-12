package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GtpPacketTest {

    private GtpPacket newBytePacket(final byte b) {
        return new GtpPacket(new byte[]{b});
    }

    private GtpPacket newBytePacket(final int b) {
        return newBytePacket((byte) b);
    }

    @Test
    public void testGetVersion() {
        final GtpPacket bp = newBytePacket(0b01000000);
        assertEquals(0b010, bp.getVersion());
    }

    @Test
    public void testSetVersion() {
        final GtpPacket bp = newBytePacket(0b00000000);
        assertEquals(0b000, bp.getVersion());
        bp.setVersion(0b010);
        assertEquals(0b010, bp.getVersion());
    }

    @Test
    public void testGetProtocolType() {
    }

    @Test
    public void testSetProtocolType() {
    }

    @Test
    public void testGetReserved() {
    }

    @Test
    public void testSetReserved() {
    }

    @Test
    public void testGetHdrLen() {
    }

    @Test
    public void testSetHdrLen() {
    }

 
}
