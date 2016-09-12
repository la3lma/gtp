package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class GtpPacketTest {

    @Test
    public void testGetVersion() {
        final int expectedVersion = 3;
        final byte bv = (byte) expectedVersion;
        final byte stored = (byte) (bv << 5);

        final byte[] packet = new byte[]{stored};
        final GtpPacket gtpPacket = new GtpPacket(packet);

        final int version = gtpPacket.getVersion();
        assertEquals(expectedVersion, version);
    }
}
