package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GtpPacketTest {

    private GtpPacket newBytePacket(final byte b) {
        return new GtpPacket(new byte[]{b});
    }

    private GtpPacket newBytePacket(final int... bytes) {
        final byte[] byteArray = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            byteArray[i] = (byte) bytes[i];
        }
        return new GtpPacket(byteArray);
    }


    @Test
    public void testGetVersion() {
        final GtpPacket bp = newBytePacket(0b00000010);
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
        final GtpPacket bp = newBytePacket(0b00001000);
        assertEquals(0b1, bp.getProtocolType());
    }

    @Test
    public void testSetProtocolType() {
        final GtpPacket bp = newBytePacket(0b00000000);
        bp.setProtocolType(1);
        assertEquals(0b1, bp.getProtocolType());
    }


    @Test
    public void testGetReserved() {
        final GtpPacket bp = newBytePacket(0b00010000);
        assertEquals(0b1, bp.getReserved());
    }

    @Test
    public void testSetReserved() {
        final GtpPacket bp = newBytePacket(0b0000000);
        bp.setReserved();
        assertEquals(0b1, bp.getReserved());
    }

    @Test
    public void testGetHdrLen() {
        final GtpPacket bp = newBytePacket(0b00100000);
        assertEquals(0b1, bp.getExtensionHeaderFlag());
    }

    @Test
    public void testSetHdrLen() {
        final GtpPacket bp = newBytePacket(0b00000000);
        bp.setExtensionHeaderFlag(1);
        assertEquals(0b1, bp.getExtensionHeaderFlag());
    }

    @Test
    public void testGetSeqNoFlag() {
        final GtpPacket bp = newBytePacket(0b01000000);
        assertEquals(0b1, bp.getSeqNoFlag());
    }

    @Test
    public void testSetSeqNoFlag() {
        final GtpPacket bp = newBytePacket(0b00000000);
        bp.setSeqNoFlag(1);
        assertEquals(0b1, bp.getSeqNoFlag());
    }

    @Test
    public void testGetGtpMessageType() {
        final GtpPacket bp = newBytePacket(0b01000000, 152);
        assertEquals(152, bp.getMessageType());
    }

    @Test
    public void testSetGtpMessageType() {
        final GtpPacket bp = newBytePacket(0, 0);
        bp.setMessageType(152);
        assertEquals(152, bp.getMessageType());
    }

}
