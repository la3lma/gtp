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
    // XXX Todo:   Make getbits/setbits work on the actual byte array
    //             directly (address bytes directly in the packet by their
    //             bitwise index).  Set up roundtrip read/write unit tests,
    //             then get a real world captured packet, read it, and
    //             how it goes, then move on to greater things.
    private int getBits(final int offset, final byte mask) {

        final byte unmasked = packet[0];
        final byte masked = (byte) (unmasked & mask);
        final byte shifted = (byte) (masked >> offset);
        return shifted;
    }

    private void setBits(final int value, final int offset, final byte mask) {
        final byte bv = (byte) value;
        final byte storedBits = (byte) (bv << offset);
        packet[0] = (byte) (storedBits | (byte) (packet[0] & mask));
    }

    /**
     * The first header field in a GTP' packet is the 3-bit version field. For
     * GTP' v2, this has a value of 2 (hence the name GTP' v2).
     *
     * @return
     */
    public int getVersion() {
        return getBits(5, (byte) 0b11100000);
    }

    

    // XXX Calculate the bit patttern based on the length of the field
    //     in bits.
    public void setVersion(final int version) {
        setBits(version, 5, (byte) 0b11100000);
    }

    /**
     * a 1-bit value that differentiates GTP' (value 0) from GTP (value 1).
     *
     * @return
     */
    public int getProtocolType() {
        return getBits(4, (byte) 0b00010000);
    }

    public void setProtocolType(int value) {
        setBits(value,  4, (byte) 0b00010000);
    }

    /**
     * A 3-bit reserved field (must be 1's).
     *
     * @return
     */
    public int getReserved() {
        return getBits(1, (byte) 0b00001110);
    }

    public void setReserved() {
        int value = 0b111;
        setBits(value, 4, (byte) 0b00001110);
    }

    /**
     * A 1-bit value that for GTP' version 0 indicates if using a 20 byte header
     * (value 0) (as per GTP) or this 6 byte header. This bit must be unset
     * (value 0) for subsequent GTP' versions and in these does not indicate the
     * header length as this must always be 6 bytes.
     *
     * @return
     */
    public int getHdrLen() {
        return getBits(0, (byte) 0b00000001);
    }

    public void setHdrLen(int value) {
        setBits(value, 1, (byte) 0b00000001);
    }
}
