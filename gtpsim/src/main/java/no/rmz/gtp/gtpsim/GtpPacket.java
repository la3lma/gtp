package no.rmz.gtp.gtpsim;

import com.google.common.base.Preconditions;

/**
 *
 * Bit position in packet: 0-2 Version 3 Protocol Type 4 Reserved 5
 * Extension header flag 6 Sequence number flag 7 N-PDU number flag 8-15
 * Message type 16-31 Message length 32-63 TEID 16 bit optional sequence
 * number 8 bit Optional N-PDU field 8 bit Optional next extension header
 * type
 */
public final class GtpPacket {
    final byte[] packet;

    public GtpPacket(final byte[] packet) {
        this.packet = Preconditions.checkNotNull(packet);
        // XXX Sanity checks missing
    }

    public int getVersion() {
        byte b;
        b = (byte) (((byte) (packet[0] & (byte) 0b11100000)) >> 5);
        return (int) b;
    }
}
