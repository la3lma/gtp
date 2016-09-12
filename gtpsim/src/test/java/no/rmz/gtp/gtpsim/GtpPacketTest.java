package no.rmz.gtp.gtpsim;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class GtpPacketTest {

    @Test
    public void testFirstOctetRoundtrip() {
        final int expectedVersion = 3;
        
        final byte[] packet = new byte[]{0};
        final GtpPacket gtpPacket = new GtpPacket(packet);
        gtpPacket.setVersion(expectedVersion);


        final int version = gtpPacket.getVersion();
        assertEquals(expectedVersion, version);
    }
}
